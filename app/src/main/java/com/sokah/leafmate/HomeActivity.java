package com.sokah.leafmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    Button btn_newPlant;
    ListView listMyPlants;
    TextView msgEmptyGarden;
    MyPlantsAdapter adapterMyPlants;
    FirebaseDatabase db;
    FirebaseAuth auth;
    String userId;
    ConstraintLayout navBar_goLibrary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_newPlant = findViewById(R.id.btn_NewPlant);
        listMyPlants = findViewById(R.id.myPlantsList);
        msgEmptyGarden = findViewById(R.id.msgMyPlantEmpty);

        navBar_goLibrary = findViewById(R.id.navBar_goLibrary);

        adapterMyPlants = new MyPlantsAdapter();

        db =  FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        listMyPlants.setAdapter(adapterMyPlants);

        msgEmptyGarden.setVisibility(View.INVISIBLE);

        loadPlants();

        navBar_goLibrary.setOnClickListener(
                (v)->{
                    Intent l = new Intent(this, LibraryActivity.class);
                    startActivity(l);
                    finish();
                }
        );

        btn_newPlant.setOnClickListener(
                (v)->{
                    Intent i = new Intent(this, AddNewPlantActivity.class);
                    startActivity(i);
                    finish();

                }
        );



    }

    public void loadPlants() {
        if(auth.getCurrentUser() != null){
            String id = auth.getCurrentUser().getUid();

            db.getReference().child("GardenPlants").child(id).addValueEventListener(
                    new ValueEventListener() {

                        public void onDataChange( DataSnapshot snapshot) {
                            adapterMyPlants.clear();
                            if(snapshot.exists()){
                                msgEmptyGarden.setVisibility(View.INVISIBLE);

                                for(DataSnapshot child: snapshot.getChildren()){
                                    MyPlant myplant = child.getValue(MyPlant.class);
                                    adapterMyPlants.addNewMyPlant(myplant);

                                }

                            }else{
                                msgEmptyGarden.setVisibility(View.VISIBLE);
                            }
                           
                        }
                        @Override
                        public void onCancelled( DatabaseError error) {

                        }
                    }
            );

        }


    }

}