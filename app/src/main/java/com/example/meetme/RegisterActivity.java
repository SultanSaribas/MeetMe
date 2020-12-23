package com.example.meetme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    Button btn_register_with_email_activity_register;
    EditText et_namesurname_register, et_password_register, et_email_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register_with_email_activity_register=findViewById(R.id.btn_register_with_email_activity_register);
        et_namesurname_register=findViewById(R.id.et_namesurname_register);
        et_password_register=findViewById(R.id.et_password_register);
        et_email_register=findViewById(R.id.et_email_register);

        btn_register_with_email_activity_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}