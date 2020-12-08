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

public class LoginActivity extends AppCompatActivity {

    Button btn_Login;
    EditText eT_Password, eT_Email;
    FirebaseDatabase db;
    FirebaseAuth auth;
    TextView textRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        btn_Login = findViewById(R.id.btn_Login);
        eT_Password = findViewById(R.id.inputPasswordLogin);
        eT_Email = findViewById(R.id.inputEmailLogin);
        textRegister = findViewById(R.id.textRegister);

        if(auth.getCurrentUser()!=null){


            Intent intent= new Intent(this,HomeActivity.class);
            startActivity(intent);
        }

        btn_Login.setOnClickListener(
                (v) -> {
                    String email = eT_Email.getText().toString();
                    String password = eT_Password.getText().toString().trim();

                    boolean inputVer = email.isEmpty() || password.isEmpty();

                    if(inputVer){
                        Toast.makeText(this, "Complete all fields", Toast.LENGTH_LONG).show();
                    }else{
                        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                                task ->{
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(this, HomeActivity.class);
                                        startActivity(i);
                                        finish();
                                    }else{
                                        Toast.makeText(this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                        );
                    }

                }
        );

        textRegister.setOnClickListener(
                (v)->{

                    eT_Email.setText("");
                    eT_Password.setText("");
                    Intent i = new Intent(this, RegisterActivity.class);
                    startActivity(i);
                }
        );
    }


}