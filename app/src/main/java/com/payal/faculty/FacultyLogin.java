package com.payal.faculty;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FacultyLogin extends AppCompatActivity {
    Button btnLoginfac;
    EditText etxtUserIdfac,etxtPassfac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_login);

        etxtUserIdfac=(EditText)findViewById(R.id.etUseridfac);
        etxtPassfac=(EditText)findViewById(R.id.etpassfac);

        btnLoginfac=(Button)findViewById(R.id.buttonLoginfac);

        btnLoginfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserId=etxtUserIdfac.getText().toString();
                String strPass=etxtPassfac.getText().toString();

                DatabaseHelper db=new DatabaseHelper(getApplicationContext());
                Cursor c=db.getAllData();
                while (c.moveToNext()) {
                    String uid= String.valueOf(c.getInt(0));
                    String pass=c.getString(1);
                    int idu=Integer.parseInt(uid);
                    if (strUserId.equals(uid)&&strPass.equals(pass)) {

                        Intent in = new Intent(FacultyLogin.this, FacultyNavigation.class);
                        in.putExtra("USER_ID",idu);
                        startActivity(in);
                        etxtUserIdfac.setText("");
                        etxtPassfac.setText("");
                    }
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
//                    }
                }
               // finish();
            }
        });

    }
}
