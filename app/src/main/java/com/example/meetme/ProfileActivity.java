package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meetme.Models.Users;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Type;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    Button create_event_profile;
    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://meetme-2ff9d-default-rtdb.firebaseio.com/");

    String currentuser = FirebaseAuth.getInstance().getUid();
    Users user;
    TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.user_profile_name);
        DatabaseReference userReference = mFirebaseDatabase.getReference("Users")
                .child(currentuser);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(Users.class);
                userName.setText(user.name);
                Log.v("username", user.name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        create_event_profile=findViewById(R.id.create_event_profile);
        navigationbar();
        create_event_profile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, CreateEvent.class);
                startActivity(intent);
            }
        });

    }

    public void navigationbar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        intent = new Intent(ProfileActivity.this, DiscoverActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_profile:
                        break;
                }
                return false;
            }
        });
    }
}