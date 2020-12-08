package com.sokah.leafmate;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyPlantsAdapter extends BaseAdapter {

    ArrayList<MyPlant> myPlantData;
    FirebaseDatabase db;

    public MyPlantsAdapter(){
        myPlantData = new ArrayList<>();
        db = FirebaseDatabase.getInstance();
    }
    public void addNewMyPlant(MyPlant newMyPlant){
        myPlantData.add(newMyPlant);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return myPlantData.size();
    }

    @Override
    public Object getItem(int position) {
        return myPlantData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear(){
        myPlantData.clear();
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View row, ViewGroup list) {

        LayoutInflater inflater = LayoutInflater.from(list.getContext());
        View cardMyPlant = inflater.inflate(R.layout.myplant_card, null);

        MyPlant myPlantInfo = myPlantData.get(position);

        ImageView imgMyPlant = cardMyPlant.findViewById(R.id.imgMyPlant);
        TextView timerMyPlant = cardMyPlant.findViewById(R.id.textTimeWater);
        TextView titleMyPlant = cardMyPlant.findViewById(R.id.myPlantTitle);
        TextView statePlant = cardMyPlant.findViewById(R.id.textStatePlant);
        ConstraintLayout containerState = cardMyPlant.findViewById(R.id.containerStatePlant);

        titleMyPlant.setText(myPlantInfo.getUserName());

        switch (myPlantInfo.getName()) {

            case "Blueberry":

                imgMyPlant.setImageResource(R.drawable.circleblueberry);

                break;
            case "Kumquat":

                imgMyPlant.setImageResource(R.drawable.circlekumquat);

                break;
            case "Lime":

                imgMyPlant.setImageResource(R.drawable.circlelime);

                break;
            case "Strawberry":

                imgMyPlant.setImageResource(R.drawable.circlestrawberry);

                break;


            case "Cilantro":

                imgMyPlant.setImageResource(R.drawable.circlecilantro);

                break;
            case "Mint":

                imgMyPlant.setImageResource(R.drawable.circlemint);

                break;
            case "Rosemary":

                imgMyPlant.setImageResource(R.drawable.circlerosemary);

                break;


            case "Cabbage":

                imgMyPlant.setImageResource(R.drawable.circlecabbage);

                break;


            case "Carrot":

                imgMyPlant.setImageResource(R.drawable.circlecarrot);

                break;
            case "Cauliflower":

                imgMyPlant.setImageResource(R.drawable.circlecauliflour);

                break;

            case "Red Bell Pepper":

                imgMyPlant.setImageResource(R.drawable.pimenton);

                break;



        }

        cardMyPlant.setOnClickListener(

                (v) -> {
                    Intent i = new Intent(list.getContext(), MyPlantActivity.class);
                    i.putExtra("infoMyPlant", myPlantInfo);
                    list.getContext().startActivity(i);

                }


        );




        String waterFreq = calculateWaterFreq(myPlantInfo.getBornTime(), myPlantInfo.getNextWatter());
        timerMyPlant.setText(waterFreq);

        String infoWater[] = waterFreq.split(" ");
        float time = Float.parseFloat(infoWater[0]);

        if(time<=0){
            statePlant.setText("Need water");
            statePlant.setTextColor(Color.WHITE);
            containerState.setBackground(ContextCompat.getDrawable(cardMyPlant.getContext(),R.drawable.containerstateplant));

        }else{
            statePlant.setText("Already water");
            statePlant.setTextColor(ContextCompat.getColor(cardMyPlant.getContext(),R.color.coral));
            containerState.setBackground(ContextCompat.getDrawable(cardMyPlant.getContext(),R.drawable.container_state_ready));

        }

        return cardMyPlant;
    }

    private String calculateWaterFreq(String bornTime, String nextWater) {
        String timeToWater = "0";

        SimpleDateFormat formatterTim = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        String actualTime = formatterTim.format(calendar.getTime());

        try {
            int hours = 3600 * 1000;
            Date t1 = formatterTim.parse(actualTime);

            Date tborn= formatterTim.parse(bornTime);

            String infoWater[] = nextWater.split(" ");
            int nHours = Integer.parseInt(infoWater[0]);



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

}
