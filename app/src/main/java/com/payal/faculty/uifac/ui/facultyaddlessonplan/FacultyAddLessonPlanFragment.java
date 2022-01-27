package com.payal.faculty.uifac.ui.facultyaddlessonplan;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class FacultyAddLessonPlanFragment extends Fragment {

   // DatabaseHelperF myDb;
    //String str;
    View view;
    TableRow tableRow14,tableRow15,tableRow17,tableRow18,tableRow19;
    RadioGroup rgPract,rgLec;
    TextView txtPractNo,txtPractCheck,txtNotes,txtPointsCov,txtAssQue;
    EditText etPractNo,etPointsCov,etDate;
    RadioGroup rgPractCheck,rgNotes,rgAssQue;
    RadioButton rbPractCheckYes,rbPractCheckNo,rbNotesYes,rbNotesNo,rbAssQueYes,rbAssQueNo;
    boolean bFlag=false, bFlag1=false;
    RadioButton rbPractCheckYeso,rbPractCheckNoo,rbNotesYeso,rbNotesNoo,rbAssQueYeso,rbAssQueNoo;

    String ArrTime[] = {"Select Time","8:30 AM to 9:30 AM","9:30 AM to 10:30 AM", "10:40 AM to 11:40 AM","11:40 AM to 12:40 PM","1:20 PM to 2:20 PM","2:20 PM to 3:20 PM"};
    String strDate="",strTime="",strPract="",strPractNo="",strPractCheck="",strlecture="",strNotes="",strPointCov="",strAssignment="";
    Spinner spTime;
    DatabaseHelper myDb;
    public int uid;

    public FacultyAddLessonPlanFragment(int id)
    {
        uid=id;
      //  Toast.makeText(getActivity(),""+uid,Toast.LENGTH_SHORT).show();
    }


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faculty_fragment_addlessonplan, container, false);

        etDate=view.findViewById(R.id.etDateaddF);

        rgPract=view.findViewById(R.id.rgPractadd);
        rgLec=view.findViewById(R.id.rgLecadd);
        spTime=view.findViewById(R.id.spTimeadd);

//        Intent in=getActivity().getIntent();
//        final int user_id=in.getIntExtra("USER_ID",0);

        tableRow14=(TableRow) view.findViewById(R.id.row14);
        tableRow15=(TableRow) view.findViewById(R.id.row15);
        tableRow17=(TableRow) view.findViewById(R.id.row17);
        tableRow18=(TableRow) view.findViewById(R.id.row18);
        tableRow19=(TableRow) view.findViewById(R.id.row19);
        Button btnSubmit = view.findViewById(R.id.buttonaddlesson);
        myDb = new DatabaseHelper(getContext());

        ArrayAdapter adapterDept = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, ArrTime);
        adapterDept.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTime.setAdapter(adapterDept);

        spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strTime=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        rgPract.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.rbPractYesadd:
                        strPract="Yes";
                        bFlag=true;
                        addRadioGroup();
                        break;
                    case R.id.rbPractNoadd:
                        strPract="No";
                        bFlag=false;
                        removeRadioGroup();
                        break;
                }
            }
        });

        rgLec.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.rbLecYesadd:
                        strlecture="Yes";
                        bFlag1=true;
                        addRadioGroup1();
                        break;
                    case R.id.rbLecNoadd:
                        strlecture="No";
                        bFlag1=false;
                        removeRadioGroup1();
                        break;
                }
            }
        });


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

//                String strMsg="User ID:"+1+"\nDate: "+strDate+"\n Time: "+strTime+"\n Practical:"+strPract+"\n Practical No:"+strPractNo+
//                        "\n Practical Checked:"+strPractCheck+"\n Lecture:"+strlecture+"\n Notes:"+strNotes+"\n Point Covered:"+
//                        strPointCov+"\n Assignment Question:"+strAssignment;
              //  Toast.makeText(getActivity(),""+uid,Toast.LENGTH_LONG).show();
                String strId= String.valueOf(uid);
                if (strDate.equals("")|strTime.equals("")|strPract.equals("")|strlecture.equals("")) {
                    Toast.makeText(getContext(), "Please fill all field", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        boolean isInserted = myDb.insertDataF(strId,strDate, strTime,strPract, strPractNo, strPractCheck, strlecture, strNotes, strPointCov,strAssignment);
                        if (isInserted == true) {

                            Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerfac,
                                    new FacultyViewLessonPlanFragment(uid)).commit();
                        } else {
                            Toast.makeText(getContext(), "Data could not be Inserted", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ioe) {
                        Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
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
        tableRow14.addView(txtPractNo);


        etPractNo=new EditText(getActivity());
        etPractNo.setText("");
        etPractNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        etPractNo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        etPractNo.setId(14);
        tableRow14.addView(etPractNo);

        txtPractCheck = new TextView(getContext());
        txtPractCheck.setText("Practical Checked:");
        txtPractCheck.setTextSize(18);
        tableRow15.addView(txtPractCheck);

        rgPractCheck=new RadioGroup(getContext());
        rgPractCheck.setId(15);
        tableRow15.addView(rgPractCheck);

        rbPractCheckYes=new RadioButton(getContext());
        rbPractCheckYes.setText("Yes");
        rbPractCheckYes.setId(16);
        rgPractCheck.addView(rbPractCheckYes);

        rbPractCheckNo=new RadioButton(getContext());
        rbPractCheckNo.setText("No");
        rbPractCheckNo.setId(17);
        rgPractCheck.addView(rbPractCheckNo);

    }
    private void removeRadioGroup() {

        tableRow14.removeView(txtPractNo);
        tableRow14.removeView(etPractNo);

        tableRow15.removeView(txtPractCheck);
        tableRow15.removeView(rgPractCheck);
    }

    @SuppressLint("ResourceType")
    private void addRadioGroup1() {
        txtNotes = new TextView(getContext());
        txtNotes.setText("Notes:");
        txtNotes.setTextSize(18);
        tableRow17.addView(txtNotes);

        rgNotes=new RadioGroup(getContext());
        rgNotes.setId(18);
        tableRow17.addView(rgNotes);

        rbNotesYes=new RadioButton(getContext());
        rbNotesYes.setText("Yes");
        rbNotesYes.setId(19);
        rgNotes.addView(rbNotesYes);

        rbNotesNo=new RadioButton(getContext());
        rbNotesNo.setText("No");
        rbNotesNo.setId(20);
        rgNotes.addView(rbNotesNo);

        txtPointsCov = new TextView(getContext());
        txtPointsCov.setText("Points Covered:");
        txtPointsCov.setTextSize(18);
        tableRow18.addView(txtPointsCov);

        etPointsCov=new EditText(getContext());
        etPointsCov.setSingleLine(false);
        etPointsCov.setWidth(35);
        etPointsCov.setId(21);
        tableRow18.addView(etPointsCov);

        txtAssQue = new TextView(getContext());
        txtAssQue.setText("Assignment Questions:");
        txtAssQue.setTextSize(18);
        tableRow19.addView(txtAssQue);

        rgAssQue=new RadioGroup(getContext());
        rgAssQue.setId(22);
        tableRow19.addView(rgAssQue);

        rbAssQueYes=new RadioButton(getContext());
        rbAssQueYes.setText("Yes");
        rbAssQueYes.setId(23);
        rgAssQue.addView(rbAssQueYes);

        rbAssQueNo=new RadioButton(getContext());
        rbAssQueNo.setText("No");
        rbAssQueNo.setId(13);
        rgAssQue.addView(rbAssQueNo);
    }

    private void removeRadioGroup1() {
        tableRow17.removeView(txtNotes);
        tableRow17.removeView(rgNotes);
        tableRow18.removeView(txtPointsCov);
        tableRow18.removeView(etPointsCov);
        tableRow19.removeView(txtAssQue);
        tableRow19.removeView(rgAssQue);
    }
}
