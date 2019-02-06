package com.project.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class OnlineShopping extends Activity {

        private Button login ;
        private Button buy ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_online_shopping);
            login = (Button) findViewById(R.id.login) ;
            buy = (Button) findViewById(R.id.buy) ;

            // go to Login class
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OnlineShopping.this , Login.class) ;
                    OnlineShopping.this.startActivity(intent);
                    finish();
                }
            });

            // go to Buy class
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OnlineShopping.this , Buy.class) ;
                    OnlineShopping.this.startActivity(intent);
                    finish();
                }
            });
        }
}
