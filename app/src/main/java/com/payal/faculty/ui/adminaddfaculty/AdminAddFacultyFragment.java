package com.payal.faculty.ui.adminaddfaculty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.payal.faculty.DatabaseHelper;
import com.payal.faculty.R;
import com.payal.faculty.Utility;
import com.payal.faculty.ui.adminviewlessonplan.AdminViewLessonPlanFragment;

import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class AdminAddFacultyFragment extends Fragment{

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "AdminAddFacultyFragment";
    private Uri selectedImageUri;

    View view;
    DatabaseHelper myDb;
    EditText editTextId, editPass,editPassc,editFname,etSubject;
    String ArrDept[] = {"Select Department","CM", "ME", "E&TC", "EE", "CE", "AE"};
    String ArrYear[] = {"Select Year","FY", "SY", "TY"};
    String strDept="",strYear="";
    Spinner spDept,spYear;
    ImageView imgPhoto;
    String uid,pass,passc,fname,subject;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.admin_fragment_addfaculty, container, false);

        spDept = view.findViewById(R.id.spDeptadd);
        spYear = view.findViewById(R.id.spYearadd);
        imgPhoto = view.findViewById(R.id.imgFPhotoAdd);

        Button btnSubmit = view.findViewById(R.id.btnSubmitadd);

        editTextId= view.findViewById(R.id.etUseridadd);
        editPass= view.findViewById(R.id.etpassadd);
        editPassc= view.findViewById(R.id.etpasscadd);
        editFname= view.findViewById(R.id.etFacultyNameadd);
        etSubject=view.findViewById(R.id.etSubjectadd);
        myDb = new DatabaseHelper(getContext());

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

        editPassc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                String strPass1 = editPass.getText().toString();
                String strPass2 = editPassc.getText().toString();

                if (!strPass1.equals(strPass2)) {
                    editPassc.setError("Password Not Match");
                } else {
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getContext(), strDept+":"+strYear, Toast.LENGTH_LONG).show();
                uid=editTextId.getText().toString();
                pass=editPass.getText().toString();
                passc=editPassc.getText().toString();
                fname=editFname.getText().toString();
                subject=etSubject.getText().toString();
                if (uid.equals("")|pass.equals("")|passc.equals("")|fname.equals("")|strDept.equals("")|strYear.equals("")|subject.equals("")) {
                    Toast.makeText(getContext(), "Please fill all field", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        InputStream iStream = getContext().getContentResolver().openInputStream(selectedImageUri);
                        byte[] inputData = Utility.getBytes(iStream);
                        boolean isInserted = myDb.insertData(uid, pass,passc, fname, inputData, strDept, strYear, subject);
                        if (isInserted == true) {

                            Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                            editTextId.setText("");
                            editPass.setText("");
                            editPassc.setText("");
                            editFname.setText("");
                            etSubject.setText("");

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new AdminViewLessonPlanFragment()).commit();
                        } else {
                            Toast.makeText(getContext(), "Data could not be Inserted", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ioe) {
                        Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
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

