package com.service.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

   public EditText User,Password;
    Button Login,signup,button;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistner;
    private ProgressDialog nProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nProgress=new ProgressDialog(this);
        User=findViewById(R.id.username);
        Password=findViewById(R.id.pass);
        Login=findViewById(R.id.login);
        button=findViewById(R.id.button);
        signup=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Logged_In.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this,User.getText().toString(),Toast.LENGTH_SHORT).show();
                signin();
              //  startActivity(new Intent(MainActivity.this,Main4Activity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
        mAuthlistner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                  //  startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    Toast.makeText(MainActivity.this,"You are In",Toast.LENGTH_SHORT).show();
//User.setText("");
//Password.setText("");
                }
            }
        };


    }
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistner);
    }

    public void signin(){
        String Email = User.getText().toString();
        String pass = Password.getText().toString();

        if (Email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(MainActivity.this, "please! enter your Email And Password", Toast.LENGTH_LONG).show();
        }
        else  {
            nProgress.setMessage("Signing In...");
            nProgress.show();
            mAuth.signInWithEmailAndPassword(Email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        nProgress.dismiss();
                        Toast.makeText(MainActivity.this, "Incorrect UserName Or Password", Toast.LENGTH_LONG).show();
                        }
                    else {
                        nProgress.dismiss();
                        startActivity(new Intent(MainActivity.this,Logged_In.class));
                    }
                }
            });
       }
    }
    }

