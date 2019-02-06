package com.project.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Owner;

/**
 * Created by Maria on 24-Apr-17.
 */

public class Login extends Activity {

    public static String sid ;
    private Switch mySwitch;
    private EditText userid;
    private EditText password;
    private Button login_button;
    private Intent intent;
    private DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = (Button) findViewById(R.id.login_button);
        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF
        db = new DBHelper(getApplicationContext()) ;

        // back to the home page
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Login.this, OnlineShopping.class);
                    Login.this.startActivity(intent);
                    finish();
                }
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = (EditText) findViewById(R.id.user_id);
                password = (EditText) findViewById(R.id.password);
                String id = userid.getText().toString();
                String pass = password.getText().toString();
               int result = check_admin_or_owner(id , pass) ;
                if (result == 1){
                    // admin
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    sid = id.substring(0,3) ;
                    intent = new Intent(Login.this, Admin.class);
                    Login.this.startActivity(intent);
                    finish();
                }else
                    if(result == 2){
                        // owner
                        Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                        sid = id.substring(0,3) ;
                        intent = new Intent(Login.this, Owners.class);
                        Login.this.startActivity(intent);
                        finish();
                    }else{
                        // error (back to the home page)
                        Toast.makeText(getApplicationContext(), "Wrong User ID or Password",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, OnlineShopping.class);
                        Login.this.startActivity(intent);
                        finish();
                    }
            }
        });
    }

    private int check_admin_or_owner(String id , String pass) {
        // valid id length is 8
        if (id.length() == 8 && pass.length() != 0) {
            // owner_id (supermarket_id + 0 + owner_id)
            if (Character.toString(id.charAt(3)).compareTo("0") == 0) {
                int valid_owner = db.check_owner(id.substring(4) , id.substring(0,3) , pass) ;
                if (valid_owner == 1)
                    return 2 ;
                else
                    return 0 ;
            } else
                // admin_id (supermarket_id + 1 + admin_id)
                if (Character.toString(id.charAt(3)).compareTo("1") == 0) {
                    int valid_admin = db.check_admin(id.substring(4) , id.substring(0,3) , pass) ;
                    if (valid_admin == 1)
                        return 1 ;
                    else
                        return 0 ;
                } else
                    return 0 ;
        } else
            return 0 ;

    }

}