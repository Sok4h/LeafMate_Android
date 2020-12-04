package com.sokah.leafmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPlantActivity extends AppCompatActivity {

    TextView title,age,type,tip,sunLight;
    ImageView imagePlant;
    Button water;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant);

        Plant plant = (Plant) getIntent().getSerializableExtra("plant");
        title=findViewById(R.id.titleMyPlant);
        age=findViewById(R.id.ageMyPlant);
        type=findViewById(R.id.typeMyPlant);
        imagePlant=findViewById(R.id.imageMyPlant);
        water=findViewById(R.id.btnWaterMyPlant);
        sunLight=findViewById(R.id.sunLightMyPlant);
        back=findViewById(R.id.btnBackMyPlant);

        water.setOnClickListener(
                (v)->{

                    plant.Water();

                }
        );

        back.setOnClickListener(
                (v)->{

                    finish();
                }
        );

        title.setText(plant.getName());
        type.setText(plant.getType());
        sunLight.setText(plant.getSunLight());

        switch (plant.getName()){

            case "Cilantro":

                imagePlant.setBackgroundResource(R.drawable.cilantro);

                break;

        }


    }
}