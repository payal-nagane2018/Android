package com.payal.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etxtUserId,etxtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etxtUserId=(EditText)findViewById(R.id.etUserid);
        etxtPass=(EditText)findViewById(R.id.etpass);

        btnLogin=(Button)findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserId=etxtUserId.getText().toString();
                String strPass=etxtPass.getText().toString();

                if(strUserId.equals("LessonPlan")&&strPass.equals("Plan@123")) {
                    Intent in = new Intent(LoginActivity.this, AdminNavigation.class);
                    startActivity(in);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Login",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
