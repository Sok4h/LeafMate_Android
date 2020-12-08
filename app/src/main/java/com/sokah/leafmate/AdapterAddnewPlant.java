package com.sokah.leafmate;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;



public class AdapterAddnewPlant extends BaseAdapter {


    private ArrayList<LibraryPlant> libraryPlantArrayList;
    private View renglon;
    private  LibraryPlant plantSelcted;

    public AdapterAddnewPlant() {
        libraryPlantArrayList = new ArrayList<>();
        plantSelcted=null;
    }

    @Override
    public int getCount() {
        return libraryPlantArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return libraryPlantArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        renglon = layoutInflater.inflate(R.layout.plantlibrary_card, null);
        TextView titlePlantLib = renglon.findViewById(R.id.titlePlantLib);
        TextView descriptionLibPlant = renglon.findViewById(R.id.descriptionLibPlant);
        ImageView imgLibPlant = renglon.findViewById(R.id.imgLibPlant);
        titlePlantLib.setText(libraryPlantArrayList.get(i).getName());
        descriptionLibPlant.setText(libraryPlantArrayList.get(i).getDescription());



        renglon.setOnClickListener(
                (v) -> {

                    for ( LibraryPlant plant :libraryPlantArrayList ) {

                        plant.setSelected(false);

                    }
                    libraryPlantArrayList.get(i).setSelected(true);
                    plantSelcted=libraryPlantArrayList.get(i);
                    notifyDataSetChanged();

                }
        );

        if(libraryPlantArrayList.get(i).getSelected()) {

            renglon.setBackgroundColor(ContextCompat.getColor(renglon.getContext(), R.color.darkGreen));
            titlePlantLib.setTextColor(Color.parseColor("#ffffff"));
            descriptionLibPlant.setTextColor(Color.parseColor("#ffffff"));
        }
        else{

            renglon.setBackgroundColor(Color.parseColor("#ffffff"));
            titlePlantLib.setTextColor(ContextCompat.getColor(renglon.getContext(), R.color.darkGreen));
            descriptionLibPlant.setTextColor(Color.parseColor("#000000"));
        }


        switch (libraryPlantArrayList.get(i).getName()) {

            case "Blueberry":

                imgLibPlant.setImageResource(R.drawable.circleblueberry);

                break;
            case "Kumquat":

                imgLibPlant.setImageResource(R.drawable.circlekumquat);

                break;
            case "Lime":

                imgLibPlant.setImageResource(R.drawable.circlelime);

                break;
            case "Strawberry":

                imgLibPlant.setImageResource(R.drawable.circlestrawberry);

                break;


            case "Cilantro":

                imgLibPlant.setImageResource(R.drawable.circlecilantro);

                break;
            case "Mint":

                imgLibPlant.setImageResource(R.drawable.circlemint);

                break;
            case "Rosemary":

                imgLibPlant.setImageResource(R.drawable.circlerosemary);

                break;


            case "Cabbage":

                imgLibPlant.setImageResource(R.drawable.circlecabbage);

                break;


            case "Carrot":

                imgLibPlant.setImageResource(R.drawable.circlecarrot);

                break;
            case "Cauliflower":

                imgLibPlant.setImageResource(R.drawable.circlecauliflour);

                break;

            case "Red Bell Pepper":

                imgLibPlant.setImageResource(R.drawable.circlepimenton);

                break;

        }

        return renglon;
    }


    public void AddNewPlant(LibraryPlant libraryPlant) {

        libraryPlantArrayList.add(libraryPlant);
        notifyDataSetChanged();

    }

    public void Clear() {
        libraryPlantArrayList.clear();
        notifyDataSetChanged();
    }

    public LibraryPlant getPlantSelcted() {
        return plantSelcted;
    }

    public void setPlantSelcted(LibraryPlant plantSelcted) {
        this.plantSelcted = plantSelcted;
    }
}
