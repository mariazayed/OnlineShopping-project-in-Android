package com.project.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Maria on 24-Apr-17.
 */
public class Admin extends Activity{

    private Switch mySwitch ;
    private Button add_owner , show_owners , delete_owner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        add_owner = (Button) findViewById(R.id.add_user) ;
        show_owners = (Button) findViewById(R.id.show_users) ;
        delete_owner = (Button) findViewById(R.id.delete_user) ;
        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF

        // back to the login page
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Admin.this, Login.class);
                    Admin.this.startActivity(intent);
                    finish();
                }
            }
        });

        add_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Admin.this , Add_Owner.class) ;
                Admin.this.startActivity(intent);
                finish();
            }
        });

        show_owners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Admin.this , Show_Owners.class) ;
                Admin.this.startActivity(intent);
                finish();
            }
        });

        delete_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Admin.this , Delete_Owner.class) ;
                Admin.this.startActivity(intent);
                finish();
            }
        });

    }
}
