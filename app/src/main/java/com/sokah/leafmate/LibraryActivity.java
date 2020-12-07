package com.sokah.leafmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LibraryActivity extends AppCompatActivity {

    ListView plantLibList;
    FirebaseDatabase db;
    LibraryAdapter adapterLibPlants;
    ConstraintLayout navBar_goMyPlants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        plantLibList = findViewById(R.id.listLibraryPlants);
        navBar_goMyPlants = findViewById(R.id.navBar_goMyPlants);

        db= FirebaseDatabase.getInstance();

        adapterLibPlants = new LibraryAdapter();
        loadPlants();


        navBar_goMyPlants.setOnClickListener(
                (v)->{
                    Intent j = new Intent(this, HomeActivity.class);
                    startActivity(j);
                    finish();
                }
        );

    }

    private void loadPlants(){
        db.getReference().child("Library").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot snapshot) {
                        for(DataSnapshot child: snapshot.getChildren()){
                            for(DataSnapshot plant: child.getChildren()) {
                                LibraryPlant libraryPlant = plant.getValue(LibraryPlant.class);
                                adapterLibPlants.addNewPlant(libraryPlant);
                            }
                        }
                    }

                    @Override
                    public void onCancelled( DatabaseError error) {

                    }
                }
        );
    }
}