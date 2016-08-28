package com.myapp.glenwin.sproouut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PlantsAnimals extends AppCompatActivity {

    ImageView btn_Animals, btn_Plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants_animals);

        btn_Animals = (ImageView) findViewById(R.id.buttonAnimals);
        btn_Plants = (ImageView) findViewById(R.id.buttonPlants);

        btn_Animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantsAnimals.this, ListWithPictures.class);
                startActivity(intent);


            }
        });

        btn_Plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantsAnimals.this, ListWithPicturesPlants.class);
                startActivity(intent);
            }
        });

    }
}
