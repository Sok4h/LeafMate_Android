package com.sokah.leafmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

        MyPlant plant = (MyPlant) getIntent().getSerializableExtra("infoMyPlant");
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

        title.setText(plant.getUserName());
        type.setText(plant.getType());
        sunLight.setText(plant.getSunLight());

        Log.e("TAG", plant.getName() );

        switch (plant.getName()) {

            case "Blueberry":

                imagePlant.setImageResource(R.drawable.circleblueberry);

                break;
            case "Kumquat":

                imagePlant.setImageResource(R.drawable.circlekumquat);

                break;
            case "Lime":

                imagePlant.setImageResource(R.drawable.circlelime);

                break;
            case "Strawberry":

                imagePlant.setImageResource(R.drawable.circlestrawberry);

                break;


            case "Cilantro":

                imagePlant.setImageResource(R.drawable.circlecilantro);

                break;
            case "Mint":

                imagePlant.setImageResource(R.drawable.circlemint);

                break;
            case "Rosemary":

                imagePlant.setImageResource(R.drawable.circlerosemary);

                break;


            case "Cabbage":

                imagePlant.setImageResource(R.drawable.circlecabbage);

                break;


            case "Carrot":

                imagePlant.setImageResource(R.drawable.circlecarrot);

                break;
            case "Cauliflower":

                imagePlant.setImageResource(R.drawable.circlecauliflour);

                break;

            /*case "Red Bell Pepper":

                imgLibPlant.setImageResource(R.drawable.be);

                break;
    */


        }


    }
}