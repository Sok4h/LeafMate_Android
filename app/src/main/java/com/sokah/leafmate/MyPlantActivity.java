package com.sokah.leafmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyPlantActivity extends AppCompatActivity {

    TextView title, age, type, tip, sunLight, waterFreq;
    ImageView imagePlant;
    Button water;
    Intent intent;
    ImageButton back,delete;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    float time;
    MyPlant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant);
        intent=getIntent();
        String idPlanta = intent.getStringExtra("infoMyPlant");
        title = findViewById(R.id.titleMyPlant);
        age = findViewById(R.id.ageMyPlant);
        type = findViewById(R.id.typeMyPlant);
        imagePlant = findViewById(R.id.imageMyPlant);
        water = findViewById(R.id.btnWaterMyPlant);
        waterFreq = findViewById(R.id.waterFrequencyMyPlant);
        sunLight = findViewById(R.id.sunLightMyPlant);
        back = findViewById(R.id.btnBackMyPlant);
        delete=findViewById(R.id.btnDelete);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        String userId = firebaseAuth.getCurrentUser().getUid();


        Log.e("TAG", firebaseAuth.getCurrentUser().getUid());

        firebaseDatabase.getReference().child("GardenPlants").child(userId).child(idPlanta).addValueEventListener(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                           plant= snapshot.getValue(MyPlant.class);
                           LoadInfo();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );


        delete.setOnClickListener(

                (v)->{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete this plant?")
                            .setNegativeButton("No",(dialog,id)->{
                                dialog.dismiss();
                            })
                            .setPositiveButton("Yes" ,(dialog,id)->{
                                firebaseDatabase.getReference().child("GardenPlants").child(firebaseAuth.getCurrentUser().getUid()).child(plant.getId()).setValue(null);
                                finish();
                            });
                    builder.show();
                }
        );

        water.setOnClickListener(

                (v) -> {
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    String actualDate = formatter.format(calendar.getTime());
                    firebaseDatabase.getReference().child("GardenPlants").child(plant.getUserId()).child(plant.getId()).child("bornTime").setValue(actualDate);

                    if(time<=0){

                        water.setText("Need water");
                        water.setTextColor(Color.WHITE);
                        water.setBackground(ContextCompat.getDrawable(this,R.drawable.containerstateplant));

                    }else{
                        water.setText("Already water");
                        water.setTextColor(ContextCompat.getColor(this,R.color.coral));
                        water.setBackground(ContextCompat.getDrawable(this,R.drawable.container_state_ready));

                    }

                }
        );

        back.setOnClickListener(
                (v) -> {
                    finish();
                }
        );

    }

    public void LoadInfo(){
        if(plant!=null) {
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

            String waterFrequen = calculateWaterFreq(plant.getBornTime(), plant.getNextWatter());
            waterFreq.setText(waterFrequen);

            String infoWater[] = waterFrequen.split(" ");
            time = Float.parseFloat(infoWater[0]);

            if (time <= 0) {

                water.setText("Need water");
                water.setTextColor(Color.WHITE);
                water.setBackground(ContextCompat.getDrawable(this, R.drawable.containerstateplant));

            } else {
                water.setText("Already water");
                water.setTextColor(ContextCompat.getColor(this, R.color.coral));
                water.setBackground(ContextCompat.getDrawable(this, R.drawable.container_state_ready));

            }

        }

        else {

            finish();
        }
    }
    private String calculateWaterFreq(String bornTime, String nextWater) {
        String timeToWater = "0";

        SimpleDateFormat formatterTim = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        String actualTime = formatterTim.format(calendar.getTime());

        try {
            int hours = 60 * 1000;
            Date t1 = formatterTim.parse(actualTime);

            Date tborn= formatterTim.parse(bornTime);

            String infoWater[] = nextWater.split(" ");
            long nHours = Long.parseLong(infoWater[0]);



            Date t2 = new Date(tborn.getTime()+nHours*hours);

            long difference_Time = t2.getTime() - t1.getTime();

            long difference_Hours = TimeUnit.MILLISECONDS.toHours(difference_Time);
            long difference_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_Time);

            if(difference_Hours>1){
                timeToWater = difference_Hours + " hours";

            }else{
                timeToWater = difference_Minutes + " minutes";
            }

        }catch (ParseException e){
            e.printStackTrace();
        }


        return timeToWater;
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
