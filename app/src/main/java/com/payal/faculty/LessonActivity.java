package com.payal.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LessonActivity extends AppCompatActivity {
    Spinner spTime;
    String ArrTime[] = {"Select Time","8:30 AM to 9:30 AM","9:30 AM to 10:30 AM", "10:40 AM to 11:40 AM","11:40 AM to 12:40 PM","1:20 PM to 2:20 PM","2:20 PM to 3:20 PM"};
    String strTime="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        final EditText etDate=(EditText)findViewById(R.id.etEntrDate);
        spTime=(Spinner) findViewById(R.id.aspTimeadd);
        Button btnGetPlan=(Button)findViewById(R.id.btnGetPlan);

        ArrayAdapter adapterDept = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ArrTime);
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

        final Intent in=getIntent();
        final int user_id=in.getIntExtra("U_ID",0);

        btnGetPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDate=etDate.getText().toString();
                etDate.setText("");
                spTime.setSelection(0);

                Intent in1=new Intent(LessonActivity.this,LessonplanDetails.class);
                in1.putExtra("U_ID1",user_id);
                in1.putExtra("DATE",strDate);
                in1.putExtra("TIME",strTime);
                startActivity(in1);
            }
        });
    }
}
