package com.sokah.leafmate;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlantLibraryActivity extends AppCompatActivity {

    TextView titlePlantLibrary,waterFrequencyPlantLibrary,typePlantLibrary,descriptionPlantLibrary;
    ImageView imgLibPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_library);

        LibraryPlant plantLib = (LibraryPlant) getIntent().getSerializableExtra("InfoLibPlant");

        imgLibPlant = findViewById(R.id.imagePlantLibrary);

        titlePlantLibrary=findViewById(R.id.titlePlantLibrary);
        waterFrequencyPlantLibrary=findViewById(R.id.waterFrequencyPlantLibrary);
        typePlantLibrary=findViewById(R.id.typePlantLibrary);
        descriptionPlantLibrary=findViewById(R.id.descriptionPlantLibrary);

        titlePlantLibrary.setText(plantLib.getName());
        waterFrequencyPlantLibrary.setText(plantLib.getWatering());
        typePlantLibrary.setText(plantLib.getType());
        descriptionPlantLibrary.setText(plantLib.getDescription());


        switch (plantLib.getName()) {

            case "Blueberry":

                imgLibPlant.setImageResource(R.drawable.blueberry);

                break;
            case "Kumquat":

                imgLibPlant.setImageResource(R.drawable.kumquat);

                break;
            case "Lime":

                imgLibPlant.setImageResource(R.drawable.lime);

                break;
            case "Strawberry":

                imgLibPlant.setImageResource(R.drawable.strawberry);

                break;


            case "Cilantro":

                imgLibPlant.setImageResource(R.drawable.cilantro);

                break;
            case "Mint":

                imgLibPlant.setImageResource(R.drawable.mint);

                break;
            case "Rosemary":

                imgLibPlant.setImageResource(R.drawable.rosemary);

                break;


            case "Cabbage":

                imgLibPlant.setImageResource(R.drawable.cabbage);

                break;


            case "Carrot":

                imgLibPlant.setImageResource(R.drawable.carrot);

                break;
            case "Cauliflower":

                imgLibPlant.setImageResource(R.drawable.cauliflour);

                break;

            /*case "Red Bell Pepper":

                imgLibPlant.setImageResource(R.drawable.be);

                break;
    */


        }



    }
}