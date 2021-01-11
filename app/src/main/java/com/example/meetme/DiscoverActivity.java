package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.meetme.Adapters.AdapterforDiscover;
import com.example.meetme.Models.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscoverActivity extends AppCompatActivity {

    //FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://meetme-2ff9d-default-rtdb.firebaseio.com/");

    List<Event> eventList;

    DatabaseReference eventReference;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        eventList = new ArrayList<>();

        eventReference = FirebaseDatabase.getInstance("https://meetme-2ff9d-default-rtdb.firebaseio.com/").getReference("Events");

        navigationbar();
        //recyclerView();
        //readEvents();

        metot();

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

    public void readEvents(){
        eventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Event> td = (HashMap<String,Event>) dataSnapshot.getValue();


                Log.v("senos", String.valueOf(td.values()));
                //Log.v("senos", String.valueOf(dataSnapshot.child()));


                for(int i=0; i<td.size(); i++){
                    Object firstKey = td.keySet().toArray()[i];
                    DatabaseReference newref =  eventReference.child(firstKey.toString());
                    // valueForFirstKey = new Event();
                    Log.v("senos", String.valueOf(newref.child("eventName")));
                    //Event valueForFirstKey = new Event(newref.ge,  td.get(firstKey).getEventName(),  td.get(firstKey).getEventTime(),  td.get(firstKey).getEventLink(),  td.get(firstKey).getEventDescription(),  td.get(firstKey).getEventCategory());
                    //Log.v("senos", String.valueOf(valueForFirstKey));
                   // eventList.add(i, valueForFirstKey);
                }

               // Map<String, Event> values = (Map<String, Event>) td.values();

              //  List<Event> cc = (List<Event>) td.values();

              //  Log.v("senos", cc.get(0).getEventName());

                //notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
                        Intent intent = new Intent(DiscoverActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        break;
                    case R.id.nav_profile:
                        intent = new Intent(DiscoverActivity.this, ProfileActivity.class);
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