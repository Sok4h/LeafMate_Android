package com.sokah.leafmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button btnResgister;
    EditText eT_Password, eT_Email, eT_ConPassword, eT_Name;
    TextView textLogin;
    FirebaseDatabase db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setContentView(R.layout.activity_register);
        eT_Password = findViewById(R.id.inputPasswordRegister);
        eT_Email = findViewById(R.id.inputEmailRegister);
        eT_ConPassword = findViewById(R.id.inputConfirmPassword);
        eT_Name = findViewById(R.id.inputNameRegister);
        btnResgister = findViewById(R.id.btnRegister);
        textLogin= findViewById(R.id.textLogin);
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();

        btnResgister.setOnClickListener(
                (v) -> {
                    //Input verification

                    String password = eT_Password.getText().toString();
                    String conPassword = eT_ConPassword.getText().toString();
                    String name = eT_Name.getText().toString();
                    String email = eT_Email.getText().toString().trim();

                    boolean passwordVer = password.equals(conPassword);
                    boolean inputVer = password.isEmpty() || conPassword.isEmpty() || name.isEmpty() || email.isEmpty();

                    if (inputVer) {
                        Toast.makeText(this, "Complete all fields", Toast.LENGTH_LONG).show();

                    } else {
                        if (passwordVer) {
                            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                                    task -> {
                                        if(task.isSuccessful()){
                                            String id = auth.getCurrentUser().getUid();
                                            User user = new User(
                                                    id,
                                                    name,
                                                    email,
                                                    password
                                            );
                                            db.getReference().child("Users").child(id).setValue(user).addOnCompleteListener(
                                                    taskdb->{
                                                        if(taskdb.isSuccessful()){
                                                            Intent i = new Intent(this, HomeActivity.class);
                                                            startActivity(i);
                                                            finish();
                                                        }else{

                                                        }
                                                    }
                                            );

                                        }else{
                                            Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                            );
                        } else {
                            Toast.makeText(this, "Passwords is not match", Toast.LENGTH_LONG).show();
                        }
                    }

                }

        );

        textLogin.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(this,  HomeActivity.class);
                    startActivity(intent);

                }
        );
    }
}