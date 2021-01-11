package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EventDetailActivity extends AppCompatActivity {

    TextView name, desc, time, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        name = findViewById(R.id.event_detail_name);
        desc = findViewById(R.id.detail_desc);
        time = findViewById(R.id.time_event_detail);
        link = findViewById(R.id.link_detail);

        if (getIntent().hasExtra("event_name")){
            String namexx = getIntent().getStringExtra("event_name");
            name.setText(namexx);
            String ddd = getIntent().getStringExtra("event_description");
            desc.setText(ddd);
            time.setText(getIntent().getStringExtra("event_time"));
            link.setText(getIntent().getStringExtra("event_link"));

        }


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
                        Intent intent = new Intent(EventDetailActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        intent = new Intent(EventDetailActivity.this, DiscoverActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_profile:
                        intent = new Intent(EventDetailActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }
}