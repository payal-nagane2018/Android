package com.payal.faculty.uifac.ui.facultymodifylessonplan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.payal.faculty.DatabaseHelper;
import com.payal.faculty.DatabaseHelperF;
import com.payal.faculty.R;
import com.payal.faculty.Utility;
import com.payal.faculty.ui.adminviewlessonplan.AdminViewLessonPlanFragment;
import com.payal.faculty.uifac.ui.facultyviewlessonplan.FacultyViewLessonPlanFragment;

import java.io.InputStream;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FacultyModifyLessonPlanFragment extends Fragment {

    View view;
    String strDate,strTime,strPract="",strLect="",strPractNo="",strPractCheck="",strNotes="",strPointCov="",strAssignment="";
    EditText etDate,etPractNo,etPointsCov,etID;
    RadioGroup rgPract,rgLect,rgPractCheck,rgNotes,rgAssQue;
    RadioButton rbPractCheckYes,rbPractCheckNo,rbNotesYes,rbNotesNo,rbAssQueYes,rbAssQueNo;
    String ArrTime[] = {"Select Time","8:30 AM to 9:30 AM","9:30 AM to 10:30 AM", "10:40 AM to 11:40 AM","11:40 AM to 12:40 PM","1:20 PM to 2:20 PM","2:20 PM to 3:20 PM"};
    Spinner spTime;
    TableRow tableRow24,tableRow25,tableRow27,tableRow28,tableRow29;
    TextView txtPractNo,txtPractCheck,txtNotes,txtPointsCov,txtAssQue;
    boolean bFlag=false, bFlag1=false;
    DatabaseHelper myDB;
    public int uid;

    public FacultyModifyLessonPlanFragment(int id)
    {
        uid=id;
        //   Toast.makeText(getActivity(),""+uid,Toast.LENGTH_SHORT).show();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.faculty_fragment_modifylessonplan,container,false);

        etDate=(EditText) view.findViewById(R.id.etDatemodF);
        spTime=(Spinner) view.findViewById(R.id.spTimemod);
        rgPract=(RadioGroup) view.findViewById(R.id.rgPractmod);
        rgLect=(RadioGroup) view.findViewById(R.id.rgLecmod);
        etID=(EditText)view.findViewById(R.id.etidmodF);

        tableRow24=(TableRow) view.findViewById(R.id.row24);
        tableRow25=(TableRow) view.findViewById(R.id.row25);
        tableRow27=(TableRow) view.findViewById(R.id.row27);
        tableRow28=(TableRow) view.findViewById(R.id.row28);
        tableRow29=(TableRow) view.findViewById(R.id.row29);

        myDB=new DatabaseHelper(getContext());

        ArrayAdapter adapterDept = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, ArrTime);
        adapterDept.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTime.setAdapter(adapterDept);

        rgPract.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbPractYesmod:
                        strPract="Yes";
                        bFlag=true;
                        addRadioGroup();
                        break;
                    case R.id.rbPractNomod:
                        strPract="No";
                        bFlag=false;
                        removeRadioGroup();
                        break;
                }
            }
        });

        rgLect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbLecYesmod:
                        strLect="Yes";
                        bFlag1=true;
                        addRadioGroup1();
                        break;
                    case R.id.rbLecNomod:
                        strLect="No";
                        bFlag1=false;
                        removeRadioGroup1();
                        break;
                }
            }

            @SuppressLint("ResourceType")
            private void addRadioGroup1() {
                txtNotes = new TextView(getContext());
                txtNotes.setText("Notes:");
                txtNotes.setTextSize(18);
                tableRow27.addView(txtNotes);

                rgNotes=new RadioGroup(getContext());
                rgNotes.setId(24);
                tableRow27.addView(rgNotes);

                rbNotesYes=new RadioButton(getContext());
                rbNotesYes.setText("Yes");
                rbNotesYes.setId(25);
                rgNotes.addView(rbNotesYes);

                rbNotesNo=new RadioButton(getContext());
                rbNotesNo.setText("No");
                rbNotesNo.setId(26);
                rgNotes.addView(rbNotesNo);

                txtPointsCov = new TextView(getContext());
                txtPointsCov.setText("Points Covered:");
                txtPointsCov.setTextSize(18);
                tableRow28.addView(txtPointsCov);

                etPointsCov=new EditText(getContext());
                //etPointsCov.setText("Hi");
                etPointsCov.setSingleLine(false);
                etPointsCov.setWidth(35);
                etPointsCov.setId(27);
                tableRow28.addView(etPointsCov);

                txtAssQue = new TextView(getContext());
                txtAssQue.setText("Assignment Questions:");
                txtAssQue.setTextSize(18);
                tableRow29.addView(txtAssQue);

                rgAssQue=new RadioGroup(getContext());
                rgAssQue.setId(28);
                tableRow29.addView(rgAssQue);

                rbAssQueYes=new RadioButton(getContext());
                rbAssQueYes.setText("Yes");
                rbAssQueYes.setId(29);
                rgAssQue.addView(rbAssQueYes);

                rbAssQueNo=new RadioButton(getContext());
                rbAssQueNo.setText("No");
                rbAssQueNo.setId(30);
                rgAssQue.addView(rbAssQueNo);
            }
            private void removeRadioGroup1() {

                tableRow27.removeView(txtNotes);
                tableRow27.removeView(rgNotes);
                tableRow28.removeView(txtPointsCov);
                tableRow28.removeView(etPointsCov);
                tableRow29.removeView(txtAssQue);
                tableRow29.removeView(rgAssQue);
            }
        });

        spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strTime=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button btnSubmit = view.findViewById(R.id.buttonmodifylesson);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strDate=etDate.getText().toString();

                if(bFlag) {
                    strPractNo = etPractNo.getText().toString();
                    RadioButton rbPC=(RadioButton) view.findViewById(rgPractCheck.getCheckedRadioButtonId());
                    strPractCheck=rbPC.getText().toString();
                }
                else
                {
                    strPractNo="";
                    strPractCheck="";
                }

                if(bFlag1) {
                    RadioButton rbN=(RadioButton) view.findViewById(rgNotes.getCheckedRadioButtonId());
                    strNotes=rbN.getText().toString();
                    strPointCov = etPointsCov.getText().toString();
                    RadioButton rbAQ=(RadioButton) view.findViewById(rgAssQue.getCheckedRadioButtonId());
                    strAssignment=rbAQ.getText().toString();

                }
                else
                {
                    strNotes="";
                    strPointCov="";
                    strAssignment="";
                }
                String id=etID.getText().toString();
              //  String strId= String.valueOf(uid);
                if (id.equals("")|strDate.equals("")|strTime.equals("")|strPract.equals("")|strLect.equals("")) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    try {

                        boolean isUpdate = myDB.updateDataF(id,strDate, strTime, strPract, strPractNo, strPractCheck, strLect, strNotes, strPointCov, strAssignment);
                        if (isUpdate == true) {
                            Toast.makeText(getContext(), "Data Update", Toast.LENGTH_LONG).show();

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerfac,
                                    new FacultyViewLessonPlanFragment(uid)).commit();

                        } else {
                            Toast.makeText(getContext(), "Data could not be Updated", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ioe) {
                        Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
                    }
                }



            }
        });

        Button btnDelete = view.findViewById(R.id.buttondeletelesson);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=etID.getText().toString();
                if(id.equals(""))
                {
                    Toast.makeText(getContext(), "Please Enter User ID", Toast.LENGTH_LONG).show();
                }
                else {
                    Integer deletedRows = myDB.deleteDataF(id);
                    if (deletedRows > 0) {
                        Toast.makeText(getContext(), "Data Deleted", Toast.LENGTH_LONG).show();

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerfac,
                                new FacultyViewLessonPlanFragment(uid)).commit();
                    } else {
                        Toast.makeText(getContext(), "Data could not be Deleted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }


    @SuppressLint("ResourceType")
    private void addRadioGroup() {

        txtPractNo = new TextView(getContext());
        txtPractNo.setText("Practical No:");
        txtPractNo.setTextSize(18);
        tableRow24.addView(txtPractNo);

        etPractNo=new EditText(getActivity());
        etPractNo.setText("");
        etPractNo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        etPractNo.setId(20);
        etPractNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        tableRow24.addView(etPractNo);

        txtPractCheck = new TextView(getContext());
        txtPractCheck.setText("Practical Checked:");
        txtPractCheck.setTextSize(18);
        tableRow25.addView(txtPractCheck);

        rgPractCheck=new RadioGroup(getContext());
        rgPractCheck.setId(21);
        tableRow25.addView(rgPractCheck);

        rbPractCheckYes=new RadioButton(getContext());
        rbPractCheckYes.setText("Yes");
        rbPractCheckYes.setId(22);
        rgPractCheck.addView(rbPractCheckYes);

        rbPractCheckNo=new RadioButton(getContext());
        rbPractCheckNo.setText("No");
        rbPractCheckNo.setId(23);
        rgPractCheck.addView(rbPractCheckNo);

    }
    private void removeRadioGroup() {

        tableRow24.removeView(txtPractNo);
        tableRow24.removeView(etPractNo);

        tableRow25.removeView(txtPractCheck);
        tableRow25.removeView(rgPractCheck);
    }

}
