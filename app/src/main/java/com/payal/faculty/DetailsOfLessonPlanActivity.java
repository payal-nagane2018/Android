package com.payal.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsOfLessonPlanActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_lesson_plan);

        myDB=new DatabaseHelper(this);
        TextView txtIdi=(TextView)findViewById(R.id.textIDI);
        Intent in=getIntent();
        int idi=in.getIntExtra("IDI",0);
       // String strid=
        Cursor c=myDB.getDataF(""+idi);
        while (c.moveToNext()) {
            String str="\n\n ID: \t\t\t\t\t\t\t\t\t\t"+c.getInt(0);
            str+="\n\n User Id: \t\t\t\t\t\t\t\t"+c.getString(1);
            str+="\n\n Date: \t\t\t\t\t\t\t\t\t"+c.getString(2);
            str+="\n\n Time: \t\t\t\t\t\t\t\t"+c.getString(3);
            str+="\n\n Practical: \t\t\t\t\t\t\t"+c.getString(4);
            str+="\n\n Practical No: \t\t\t\t\t"+c.getString(5);
            str+="\n\n Practical Checked: \t\t\t"+c.getString(6);
            str+="\n\n Lecture: \t\t\t\t\t\t\t"+c.getString(7);
            str+="\n\n Notes: \t\t\t\t\t\t\t\t"+c.getString(8);
            str+="\n\n Points Covered: \t\t\t\t"+c.getString(9);
            str+="\n\n Assignment Questions:\t"+c.getString(10);
            str+="\n\n";
            txtIdi.setText("" + str);
        }

    }
}
