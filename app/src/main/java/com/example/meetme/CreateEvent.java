package com.example.meetme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateEvent extends AppCompatActivity {

    Button createEventButton;
    EditText eventName, eventTime, eventDescription, eventLink;

    Spinner categories;
    String[] listCategories = {"one", "two", "ghjkl", "5"};
    private ArrayAdapter<String> categoriesAdapter;

    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://meetme-2ff9d-default-rtdb.firebaseio.com/");

    DatabaseReference userReference = mFirebaseDatabase.getReference("Users")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    DatabaseReference categoriesReference = mFirebaseDatabase.getReference("Categories");
    SharedPreferences sharedPref;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        context = this;

        createEventButton = findViewById(R.id.btn_create_event);
        eventName = findViewById(R.id.et_Activity_name);
        eventTime = findViewById(R.id.time_create);
        eventDescription =  findViewById(R.id.description_create);
        eventLink =  findViewById(R.id.link_create);
        categories = findViewById(R.id.spinner_categories);

        categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listCategories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(categoriesAdapter);

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Seçilen il ve ilçeyi ekranda gösteriyoruz.
                Toast.makeText(getBaseContext(), ""+categories.getSelectedItem().toString()+"n"+parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });







     /*   btn_kaydet_activity_danisman_bilgileri.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //TODO: The Event will be saved to Database
            }
        });*/

        navigationbar();
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
                        Intent intent = new Intent(CreateEvent.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        intent = new Intent(CreateEvent.this, DiscoverActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_profile:
                        intent = new Intent(CreateEvent.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

}