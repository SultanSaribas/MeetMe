package com.example.meetme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.TextView;
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
    String[] listCategories = {"Science and Technology", "Sport", "Art and Culture", "Entertainment", "Education", "History",
    "Med", "Game", "Business", "Psychology"};
    private ArrayAdapter<String> categoriesAdapter;

    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://meetme-2ff9d-default-rtdb.firebaseio.com/");

    DatabaseReference userReference = mFirebaseDatabase.getReference("Users")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    DatabaseReference categoriesReference = mFirebaseDatabase.getReference("Categories");
    SharedPreferences sharedPref;

    Context context;

    String selectedCategoriy;


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

        categoriesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, listCategories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(categoriesAdapter);

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
               // Toast.makeText(getBaseContext(), ""+categories.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                selectedCategoriy = categories.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent();
            }
        });

        navigationbar();
    }

    public void addEvent(){
        if (eventName.getText().toString().equalsIgnoreCase("")
                || eventTime.getText().toString().equalsIgnoreCase("")
                || eventLink.getText().toString().equalsIgnoreCase("")
                || eventDescription.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(CreateEvent.this, "Please fill all blank places", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            DatabaseReference categoriesReference = mFirebaseDatabase.getReference("Categories");
            DatabaseReference eventsReference = mFirebaseDatabase.getReference("Events");

            if (selectedCategoriy.equals("Science and Technology")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("ScienceandTech").child(key).child("eventId").setValue(key);
                categoriesReference.child("ScienceandTech").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("ScienceandTech").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("ScienceandTech").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("ScienceandTech").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("ScienceandTech").child(key).child("eventCategory").setValue("Science and Technology");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Science and Technology");
            }

            else if (selectedCategoriy.equals("Sport")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("Sport").child(key).child("eventId").setValue(key);
                categoriesReference.child("Sport").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("Sport").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("Sport").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("Sport").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("Sport").child(key).child("eventCategory").setValue("Sport");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Sport");
            }

            else if (selectedCategoriy.equals("Art and Culture")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("ArtandCulture").child(key).child("eventId").setValue(key);
                categoriesReference.child("ArtandCulture").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("ArtandCulture").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("ArtandCulture").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("ArtandCulture").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("ArtandCulture").child(key).child("eventCategory").setValue("Art and Culture");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Art and Culture");
            }

            else if (selectedCategoriy.equals("Entertainment")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("Entertainment").child(key).child("eventId").setValue(key);
                categoriesReference.child("Entertainment").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("Entertainment").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("Entertainment").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("Entertainment").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("Entertainment").child(key).child("eventCategory").setValue("Entertainment");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Entertainment");
            }

            else if (selectedCategoriy.equals("Education")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("Education").child(key).child("eventId").setValue(key);
                categoriesReference.child("Education").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("Education").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("Education").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("Education").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("Education").child(key).child("eventCategory").setValue("Education");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Education");
            }

            else if (selectedCategoriy.equals("History")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("History").child(key).child("eventId").setValue(key);
                categoriesReference.child("History").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("History").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("History").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("History").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("History").child(key).child("eventCategory").setValue("History");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("History");
            }

            else if (selectedCategoriy.equals("Med")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("Med").child(key).child("eventId").setValue(key);
                categoriesReference.child("Med").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("Med").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("Med").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("Med").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("Med").child(key).child("eventCategory").setValue("Med");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Med");
            }

            else if (selectedCategoriy.equals("Game")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("Game").child(key).child("eventId").setValue(key);
                categoriesReference.child("Game").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("Game").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("Game").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("Game").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("Game").child(key).child("eventCategory").setValue("Game");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Game");
            }

            else if (selectedCategoriy.equals("Business")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("Business").child(key).child("eventId").setValue(key);
                categoriesReference.child("Business").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("Business").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("Business").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("Business").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("Business").child(key).child("eventCategory").setValue("Business");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Business");
            }

            else if (selectedCategoriy.equals("Psychology")){
                String key = categoriesReference.push().getKey();
                categoriesReference.child("Psychology").child(key).child("eventId").setValue(key);
                categoriesReference.child("Psychology").child(key).child("eventName").setValue(eventName.getText().toString());
                categoriesReference.child("Psychology").child(key).child("eventTime").setValue(eventTime.getText().toString());
                categoriesReference.child("Psychology").child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                categoriesReference.child("Psychology").child(key).child("eventLink").setValue(eventLink.getText().toString());
                categoriesReference.child("Psychology").child(key).child("eventCategory").setValue("Psychology");

                eventsReference.child(key).child("eventId").setValue(key);
                eventsReference.child(key).child("eventName").setValue(eventName.getText().toString());
                eventsReference.child(key).child("eventTime").setValue(eventTime.getText().toString());
                eventsReference.child(key).child("eventDescription").setValue(eventDescription.getText().toString());
                eventsReference.child(key).child("eventLink").setValue(eventLink.getText().toString());
                eventsReference.child(key).child("eventCategory").setValue("Psychology");
            }

            Intent intent = new Intent(CreateEvent.this, ProfileActivity.class);
            startActivity(intent);
        }
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