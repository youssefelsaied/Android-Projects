package com.service.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Main3Activity extends AppCompatActivity {
    TextView NumOfLiters,NumOfKm;
    TextView L100KM,KmL;
    Button Calc,Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        handle();
        run();
    }

    public void handle(){
        NumOfKm=findViewById(R.id.NumOfKm);
        NumOfLiters=findViewById(R.id.NumOfLiters);
        L100KM=findViewById(R.id.L100km);
        KmL=findViewById(R.id.Kml);
        Calc=findViewById(R.id.Calculate);
        Back=findViewById(R.id.BackFcalcTMain);
    }

    public void run(){
        Calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double con= Double.valueOf((100*Float.parseFloat(NumOfLiters.getText().toString()))/(Float.parseFloat(NumOfKm.getText().toString())));
                DecimalFormat f = new DecimalFormat("##.00");
                L100KM.setText(Double.toString(Double.parseDouble(f.format(con))));
                Double conL=Double.valueOf((Float.parseFloat(NumOfKm.getText().toString()))/(Float.parseFloat(NumOfLiters.getText().toString())));
                KmL.setText(Double.toString(Double.parseDouble(f.format(conL))));
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main3Activity.this,Logged_In.class);
                startActivity(i);
            }
        });

    }

}
