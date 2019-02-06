package com.project.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Maria on 24-Apr-17.
 */

public class Buy extends Activity {

    private Switch mySwitch;
    private Button s0 , s1 , s2 , s3 , s4  ;
    public static int choosen_supermarket = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        s0 = (Button) findViewById(R.id.s0) ;
        s1 = (Button) findViewById(R.id.s1) ;
        s2 = (Button) findViewById(R.id.s2) ;
        s3 = (Button) findViewById(R.id.s3) ;
        s4 = (Button) findViewById(R.id.s4) ;

        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF

        // back to the home page
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Buy.this, OnlineShopping.class);
                    Buy.this.startActivity(intent);
                    finish();
                }
            }
        });

        s0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                choosen_supermarket = 000 ;
                Intent intent = new Intent(Buy.this , Show_Products.class) ;
                Buy.this.startActivity(intent);
                finish();
            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                choosen_supermarket = 111 ;
                Intent intent = new Intent(Buy.this , Show_Products.class) ;
                Buy.this.startActivity(intent);
                finish();
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                choosen_supermarket = 222 ;
                Intent intent = new Intent(Buy.this , Show_Products.class) ;
                Buy.this.startActivity(intent);
                finish();
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                choosen_supermarket = 333 ;
                Intent intent = new Intent(Buy.this , Show_Products.class) ;
                Buy.this.startActivity(intent);
                finish();
            }
        });

        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                choosen_supermarket = 444 ;
                Intent intent = new Intent(Buy.this , Show_Products.class) ;
                Buy.this.startActivity(intent);
                finish();
            }
        });

    }
}