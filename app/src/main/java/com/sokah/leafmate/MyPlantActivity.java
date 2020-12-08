package com.sokah.leafmate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyPlantActivity extends AppCompatActivity {

    TextView title, age, type, tip, sunLight;
    ImageView imagePlant;
    Button water;
    ImageButton back,delete;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant);

        MyPlant plant = (MyPlant) getIntent().getSerializableExtra("infoMyPlant");
        title = findViewById(R.id.titleMyPlant);
        age = findViewById(R.id.ageMyPlant);
        type = findViewById(R.id.typeMyPlant);
        imagePlant = findViewById(R.id.imageMyPlant);
        water = findViewById(R.id.btnWaterMyPlant);
        sunLight = findViewById(R.id.sunLightMyPlant);
        back = findViewById(R.id.btnBackMyPlant);
        delete=findViewById(R.id.btnDelete);
        firebaseDatabase=FirebaseDatabase.getInstance();


        delete.setOnClickListener(

                (v)->{

                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete this plant?")
                            .setNegativeButton("No",(dialog,id)->{
                                dialog.dismiss();
                            })
                            .setPositiveButton("Yes" ,(dialog,id)->{
                                firebaseDatabase.getReference().child("GardenPlants").child(plant.getUserId()).child(plant.getId()).setValue(null);
                                finish();
                            });
                    builder.show();

                }
        );

        water.setOnClickListener(
                (v) -> {

                    plant.Water();
                }
        );

        back.setOnClickListener(
                (v) -> {
                    finish();
                }
        );

        title.setText(plant.getUserName());
        type.setText(plant.getType());
        sunLight.setText(plant.getSunLight());



        switch (plant.getName()) {

            case "Blueberry":

                imagePlant.setImageResource(R.drawable.blueberry);

                break;
            case "Kumquat":

                imagePlant.setImageResource(R.drawable.kumquat);

                break;
            case "Lime":

                imagePlant.setImageResource(R.drawable.lime);

                break;
            case "Strawberry":

                imagePlant.setImageResource(R.drawable.strawberry);

                break;


            case "Cilantro":

                imagePlant.setImageResource(R.drawable.cilantro);

                break;
            case "Mint":

                imagePlant.setImageResource(R.drawable.mint);

                break;
            case "Rosemary":

                imagePlant.setImageResource(R.drawable.rosemary);

                break;


            case "Cabbage":

                imagePlant.setImageResource(R.drawable.cabbage);

                break;


            case "Carrot":

                imagePlant.setImageResource(R.drawable.carrot);

                break;
            case "Cauliflower":

                imagePlant.setImageResource(R.drawable.cauliflour);

                break;
            case "Red Bell Pepper":

                imagePlant.setImageResource(R.drawable.pimenton);

                break;

        }
        String resultAge = calculateAge(plant.getBornDate());
        age.setText(resultAge);


    }


    private String calculateAge(String bornDate) {

        String myPlantAge = "0 days";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String actualDate = formatter.format(calendar.getTime());

        try {
            Date d1 = formatter.parse(bornDate);
            Date d2 = formatter.parse(actualDate);


            long difference_Time = d2.getTime() - d1.getTime();
            long difference_Days = TimeUnit.MILLISECONDS.toDays(difference_Time);
            long difference_Mounths = difference_Days / 30;

            if(difference_Days<30){
                myPlantAge = difference_Days + " Days";
            }else{
                myPlantAge = difference_Mounths+" Months";
            }

        }catch (ParseException e){

            e.printStackTrace();
        }

        return myPlantAge;
    }
}
