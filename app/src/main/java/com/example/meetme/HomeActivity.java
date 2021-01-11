package com.example.meetme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    Button science_and_technology,sport_button,art_and_culture,entertainment,education,history,med,game, business,psychology;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        science_and_technology=findViewById(R.id.science_and_technology_button);
        sport_button=findViewById(R.id.sport_button);
        art_and_culture=findViewById(R.id.art_and_culture_button);
        entertainment=findViewById(R.id.entertainment_button);
        education=findViewById(R.id.education_button);
        history=findViewById(R.id.history_button);
        med=findViewById(R.id.med_button);
        game=findViewById(R.id.game_button);
        business=findViewById(R.id.business_button);
        psychology=findViewById(R.id.psychology_button);


            button_navigations();
            navigationbar();

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
                       break;
                   case R.id.nav_search:
                       Intent intent = new Intent(HomeActivity.this, DiscoverActivity.class);
                       startActivity(intent);
                       break;
                   case R.id.nav_profile:
                       intent = new Intent(HomeActivity.this, ProfileActivity.class);
                       startActivity(intent);
                       break;
               }
               return false;
           }
       });
   }

   public void button_navigations(){
       science_and_technology.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, SciTechActivity.class);
               startActivity(intent);
           }
       });

       sport_button.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, SportActivity.class);
               startActivity(intent);
           }
       });
       art_and_culture.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, ArtandCultureActivity.class);
               startActivity(intent);
           }
       });
       entertainment.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, EntertainmentActivity.class);
               startActivity(intent);
           }
       });
       education.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, EducationActivity.class);
               startActivity(intent);
           }
       });
       history.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
               startActivity(intent);
           }
       });
       med.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, MedActivity.class);
               startActivity(intent);
           }
       });
       game.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, GameActivity.class);
               startActivity(intent);
           }
       });
       business.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, BusinessActivity.class);
               startActivity(intent);
           }
       });
       psychology.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, PsychologyActivity.class);
               startActivity(intent);
           }
       });
   }

}