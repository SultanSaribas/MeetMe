package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register;
    EditText et_namesurname_register, et_password_register, et_email_register;


    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register=findViewById(R.id.btn_register_with_email_activity_register);
        et_namesurname_register=findViewById(R.id.et_namesurname_register);
        et_password_register=findViewById(R.id.et_password_register);
        et_email_register=findViewById(R.id.et_email_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://meetme-2ff9d-default-rtdb.firebaseio.com/");



        btn_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                register();
              //  Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
             //   startActivity(intent);
            }
        });
    }

    private void register() {
        if (et_email_register.getText().toString().equalsIgnoreCase("")
                || et_password_register.getText().toString().equalsIgnoreCase("")
                || et_namesurname_register.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(RegisterActivity.this, "Please fill all blank places", Toast.LENGTH_SHORT).show();
            return;
        }



        final android.app.AlertDialog progressDialog = new android.app.AlertDialog.Builder(RegisterActivity.this)
                .setTitle("Recording...")
                .setMessage("Please wait...")
                .setCancelable(false)
                .show();

        Task<AuthResult> task =
                mFirebaseAuth.createUserWithEmailAndPassword(et_email_register.getText().toString(), et_password_register.getText().toString());
        task.addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Successful!")
                            .setMessage("You signed up successfully")
                            .setNeutralButton("Login", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                    DatabaseReference databaseReference = mFirebaseDatabase.getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReference.child("email").setValue(et_email_register.getText().toString());
                    databaseReference.child("name").setValue(et_namesurname_register.getText().toString());
                    databaseReference.child("id").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                } else {
                    //TO-DO: Exceptionlara göre hata logu yazdır
                    Log.v("hey", task.getException().getMessage());
                    Toast.makeText(RegisterActivity.this, "Error! Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}