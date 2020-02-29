package com.service.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText uname,uemail,upassword,uphone;
    Button signup;

    private FirebaseAuth uAuth;
    private DatabaseReference uDatabaseReference;
    private ProgressDialog uProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uAuth=FirebaseAuth.getInstance();
        uDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        uProgress=new ProgressDialog(this);


        uname=findViewById(R.id.name);
        uemail=findViewById(R.id.uemail);
        upassword=findViewById(R.id.upassword);
        uphone=findViewById(R.id.phone);
        signup=findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });

    }
    private void startRegister(){
        final String name=uname.getText().toString().trim();
        String email=uemail.getText().toString().trim();
        String password=upassword.getText().toString().trim();
        final String phone=uphone.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(phone)){
            uProgress.setMessage("Signing Up...");
            uProgress.show();
            uAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){


                         String user_id=uAuth.getCurrentUser().getUid();
                         DatabaseReference current_user_dp= uDatabaseReference.child(user_id);

                         current_user_dp.child("name").setValue(name);
                         current_user_dp.child("Phone").setValue(phone);

                         uProgress.dismiss();
                         Intent main=new Intent(RegisterActivity.this,MainActivity.class);
                         startActivity(main);
                     }
                }
            });

        }


    }
}
