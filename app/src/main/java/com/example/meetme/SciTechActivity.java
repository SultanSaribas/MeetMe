package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.meetme.Adapters.AdapterforDiscover;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SciTechActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sci_tech);

        navigationbar();
        recyclerView();
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
                        Intent intent = new Intent(SciTechActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        intent = new Intent(SciTechActivity.this, DiscoverActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_profile:
                        intent = new Intent(SciTechActivity.this, ProfileActivity.class);
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
        AdapterforDiscover adapter = new AdapterforDiscover(getApplication());
        recyclerView.setAdapter(adapter);
    }
}