package com.sokah.leafmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class AddNewPlantActivity extends AppCompatActivity {

    private EditText inputNameNewPlant;
    private Button btnPlantIt;
    private ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private  AdapterAddnewPlant adapterAddnewPlant;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_plant);
        inputNameNewPlant=findViewById(R.id.inputNameNewPlant);
        btnPlantIt=findViewById(R.id.btnPlantItNewPlant);
        listView =findViewById(R.id.listViewNewPlant);
        firebaseDatabase=FirebaseDatabase.getInstance();
        adapterAddnewPlant = new AdapterAddnewPlant();
        listView.setAdapter(adapterAddnewPlant);
        firebaseAuth=FirebaseAuth.getInstance();

        LoadDataBase();
        btnPlantIt.setOnClickListener(
                (v)->{

                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                    String bornDate = formatter.format(currentTime);

                    SimpleDateFormat formatterTim= new SimpleDateFormat("HH:mm:ss");
                    String bornTime = formatterTim.format(currentTime);

                    String userId =firebaseAuth.getCurrentUser().getUid();
                    String id = UUID.randomUUID().toString();
                    LibraryPlant infoPlant = adapterAddnewPlant.getPlantSelcted();

                    if(infoPlant==null||inputNameNewPlant.getText().toString().isEmpty()){

                        Toast.makeText(this, "Please select a plant and name it", Toast.LENGTH_SHORT).show();
                    }

                    else {

                        MyPlant myPlant = new MyPlant(id,userId,infoPlant.getName(),inputNameNewPlant.getText().toString(),infoPlant.getType(),infoPlant.getSunlight(), bornDate,infoPlant.getWatering(),bornTime);

                        firebaseDatabase.getReference().child("GardenPlants").child(userId).child(id).setValue(myPlant);

                        Intent intent = new Intent(this,HomeActivity.class);
                        startActivity(intent);

                    }

                }
        );

    }

    private void LoadDataBase() {

        firebaseDatabase.getReference().child("Library").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot child: snapshot.getChildren()) {

                            for (DataSnapshot plant: child.getChildren()) {

                                LibraryPlant libraryPlant = plant.getValue(LibraryPlant.class);
                                adapterAddnewPlant.AddNewPlant(libraryPlant);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

    }
}