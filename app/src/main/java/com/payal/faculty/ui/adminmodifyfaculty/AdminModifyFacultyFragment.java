package com.payal.faculty.ui.adminmodifyfaculty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.payal.faculty.DatabaseHelper;
import com.payal.faculty.R;
import com.payal.faculty.Utility;
import com.payal.faculty.ui.adminviewlessonplan.AdminViewLessonPlanFragment;

import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class AdminModifyFacultyFragment extends Fragment {

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "AdminMFacultyFragment";
    private Uri selectedImageUri;


    View view;
    EditText editTextId, editPass,editFname,etSubject;
    DatabaseHelper myDb;
    String uid,pass,fname,subject;
    String ArrDept[] = {"Select Department","CM", "ME", "E&TC", "EE", "CE", "AE"};
    String ArrYear[] = {"Select Year","FY", "SY", "TY"};
    String strDept="",strYear="";
    Spinner spDept,spYear;
    ImageView imgPhoto;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.admin_fragment_modifyfaculty,container,false);

        spDept = view.findViewById(R.id.spDeptmod);
        spYear = view.findViewById(R.id.spYearmod);
        imgPhoto = view.findViewById(R.id.imgFPhotoMod);

        editTextId= view.findViewById(R.id.etUseridmod);
        editPass= view.findViewById(R.id.etpassmod);
        editFname= view.findViewById(R.id.etFacultyNamemod);
        etSubject= view.findViewById(R.id.etSubjectmod);
        myDb = new DatabaseHelper(getContext());

        Button btnDelete = view.findViewById(R.id.btnDeleteFmod);
        Button btnUpdate = view.findViewById(R.id.btnUpdateFmod);

        ArrayAdapter adapterDept = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, ArrDept);
        adapterDept.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spDept.setAdapter(adapterDept);

        ArrayAdapter adapterYear = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, ArrYear);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spYear.setAdapter(adapterYear);

        spDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDept=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strYear=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uid=editTextId.getText().toString();
                pass=editPass.getText().toString();
                fname=editFname.getText().toString();
                subject=etSubject.getText().toString();
                if (uid.equals("")|pass.equals("")|fname.equals("")|strDept.equals("")|strYear.equals("")|subject.equals("")) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        InputStream iStream = getContext().getContentResolver().openInputStream(selectedImageUri);
                        byte[] inputData = Utility.getBytes(iStream);

                        boolean isUpdate = myDb.updateData(uid, pass, null, fname, inputData, strDept, strYear,subject);
                        if (isUpdate == true) {
                            Toast.makeText(getContext(), "Data Update", Toast.LENGTH_LONG).show();
                            editTextId.setText("");
                            editPass.setText("");
                            editFname.setText("");
                            etSubject.setText("");
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new AdminViewLessonPlanFragment()).commit();

                        } else {
                            Toast.makeText(getContext(), "Data could not be Updated", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ioe) {
                        Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
                    }
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=editTextId.getText().toString();
                if(uid.equals(""))
                {
                    Toast.makeText(getContext(), "Please Enter User ID", Toast.LENGTH_LONG).show();
                }
                else {
                    Integer deletedRows = myDb.deleteData(uid);
                    if (deletedRows > 0) {
                        Toast.makeText(getContext(), "Data Deleted", Toast.LENGTH_LONG).show();

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new AdminViewLessonPlanFragment()).commit();
                    } else {
                        Toast.makeText(getContext(), "Data could not be Deleted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    openImageChooser();
                }
            }
        });
        return view;
    }

    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imgPhoto.setImageURI(selectedImageUri);
                }
            }
        }
    }
}