package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    Button btn_login,btn_register_inlogin;
    EditText et_email_login, et_password_login;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mFirebaseAuth = FirebaseAuth.getInstance();

        btn_login=findViewById(R.id.btn_login);
        btn_register_inlogin=findViewById(R.id.btn_register_inlogin);
        et_email_login=findViewById(R.id.et_email_login);
        et_password_login=findViewById(R.id.et_password_login);

        btn_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (et_email_login.getText().toString().equalsIgnoreCase("") ||
                        et_password_login.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(SignInActivity.this, "Please fill all blank places", Toast.LENGTH_SHORT).show();
                    return;
                }

                login(et_email_login.getText().toString(), et_password_login.getText().toString());
            }
        });

        btn_register_inlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login(final String email, final String password) {
        final AlertDialog progressDialog = new AlertDialog.Builder(SignInActivity.this)
                .setTitle("Signing in")
                .setMessage("Please wait...")
                .setCancelable(false)
                .show();

        Task<AuthResult> task =
                mFirebaseAuth.signInWithEmailAndPassword(email, password);
        task.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // login is successfull
                   // mSessionManager.setLoginData(email, password);
                    //DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users")
                      //      .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    progressDialog.dismiss();

                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();



                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "Email or password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}