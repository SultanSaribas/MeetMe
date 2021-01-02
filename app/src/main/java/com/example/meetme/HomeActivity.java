package com.example.meetme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    }
}