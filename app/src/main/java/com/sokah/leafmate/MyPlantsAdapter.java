package com.sokah.leafmate;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyPlantsAdapter extends BaseAdapter {

    ArrayList<MyPlant> myPlantData;
    FirebaseDatabase db;

    public MyPlantsAdapter(){
        myPlantData = new ArrayList<>();
        db = FirebaseDatabase.getInstance();
    }
    public void addNewMyPlant(MyPlant newMyPlant){
        myPlantData.add(newMyPlant);
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

        }

        cardMyPlant.setOnClickListener(

                (v) -> {
                    Intent i = new Intent(list.getContext(), MyPlantActivity.class);
                    i.putExtra("infoMyPlant", myPlantInfo);
                    list.getContext().startActivity(i);

                }


        );



        return cardMyPlant;
    }
}
