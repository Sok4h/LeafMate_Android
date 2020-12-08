package com.sokah.leafmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PlantLibraryActivity extends AppCompatActivity {

    TextView titlePlantLibrary,waterFrequencyPlantLibrary,typePlantLibrary,descriptionPlantLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_library);

        titlePlantLibrary.findViewById(R.id.titlePlantLibrary);
        waterFrequencyPlantLibrary.findViewById(R.id.waterFrequencyPlantLibrary);
        typePlantLibrary.findViewById(R.id.typePlantLibrary);
        descriptionPlantLibrary.findViewById(R.id.descriptionPlantLibrary);


    }
}