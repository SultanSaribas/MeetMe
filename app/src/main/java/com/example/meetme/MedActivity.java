package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.meetme.Adapters.AdapterforDiscover;
import com.example.meetme.Models.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedActivity extends AppCompatActivity {


    List<Event> eventList;

    DatabaseReference eventReference;
    Context context = this;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sci_tech);
        txt = findViewById(R.id.scitech_txtview);
        txt.setText("Med Meetings");


        eventList = new ArrayList<>();

        eventReference = FirebaseDatabase.getInstance("https://meetme-2ff9d-default-rtdb.firebaseio.com/").getReference("Categories").child("Med");

        metot();

        navigationbar();

    }
    public void metot(){
        eventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventList.clear();

                for(DataSnapshot eventsDatasanp : dataSnapshot.getChildren()){

                    Event event = eventsDatasanp.getValue(Event.class);
                    eventList.add(event);
                }
                recyclerView();

                Log.v("sondeneme", String.valueOf(eventList.get(0).getEventName()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void navigationbar(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(MedActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        intent = new Intent(MedActivity.this, DiscoverActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_profile:
                        intent = new Intent(MedActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    public void recyclerView(){
        final RecyclerView recyclerView = findViewById(R.id.rv_discover);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterforDiscover adapter = new AdapterforDiscover(context, eventList);
        recyclerView.setAdapter(adapter);
    }
}