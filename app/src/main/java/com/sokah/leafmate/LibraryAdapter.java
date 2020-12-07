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

public class LibraryAdapter extends BaseAdapter {
    ArrayList<LibraryPlant> plantsData;
    FirebaseDatabase db;

    public LibraryAdapter(){
        plantsData = new ArrayList<>();
        db = FirebaseDatabase.getInstance();
    }

    public void addNewPlant(LibraryPlant newPlant){
        plantsData.add(newPlant);
        notifyDataSetChanged();
    }

    public void clear(){
        plantsData.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return plantsData.size();
    }

    @Override
    public Object getItem(int position) {
        return plantsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View row, ViewGroup list) {

        LayoutInflater inflater = LayoutInflater.from(list.getContext());
        View cardPlantView = inflater.inflate(R.layout.plantlibrary_card, null);

        LibraryPlant infoPlant = plantsData.get(position);

        ImageView imgLibPlant = cardPlantView.findViewById(R.id.imgLibPlant);
        TextView descriptionPlant = cardPlantView.findViewById(R.id.descriptionLibPlant);
        TextView titlePlant = cardPlantView.findViewById(R.id.titlePlantLib);

        titlePlant.setText(infoPlant.getName());
        descriptionPlant.setText(infoPlant.getDescription());


        switch (infoPlant.getName()) {

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

            }

            cardPlantView.setOnClickListener(
                    (v) -> {
                        Intent i = new Intent(list.getContext(), PlantLibraryActivity.class);
                        i.putExtra("infoLibPlant", infoPlant);
                        list.getContext().startActivity(i);
                    }
            );


            return cardPlantView;

        }

}
