package com.project.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Maria on 04-May-17.
 */
public class Add_Owner extends Activity{

    private Switch mySwitch;
    private Button add_owner ;
    private EditText oname , oid , gender , address , pass ;
    private DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        add_owner = (Button) findViewById(R.id.add_owner) ;
        oname = (EditText) findViewById(R.id.ownername) ;
        oid = (EditText) findViewById(R.id.idd) ;
        gender = (EditText) findViewById(R.id.gender) ;
        address = (EditText) findViewById(R.id.address) ;
        pass = (EditText) findViewById(R.id.password) ;
        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF
        db = new DBHelper(getApplicationContext()) ;

        // back to the home page
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Add_Owner.this, Admin.class);
                    Add_Owner.this.startActivity(intent);
                    finish();
                }
            }
        });

        add_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oname.getText().toString().compareTo("") != 0
                        && oid.getText().toString().length() == 4
                        && pass.getText().toString().compareTo("") != 0
                        && integer_oid(oid.getText().toString()) == 1
                        && string_oname(oname.getText().toString()) == 1
                        && like_admin_id(oid.getText().toString()) == 1
                        && check_gender(gender.getText().toString()) == 1) {
                    int result1 = db.check_owner_validity(oid.getText().toString());
                    if (result1 == 1) { // owner not exist
                        db.add_owner(oname.getText().toString(), oid.getText().toString(),
                                gender.getText().toString(), address.getText().toString(), pass.getText().toString());
                        db.add_has_owner(oid.getText().toString());
                        Toast.makeText(getApplicationContext(), "Done Successfully", Toast.LENGTH_SHORT).show();
                        oname.setText("");
                        oid.setText("");
                        gender.setText("");
                        address.setText("");
                        pass.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "OWNER exists ! , try again ...", Toast.LENGTH_SHORT).show();
                        oname.setText("");
                        oid.setText("");
                        gender.setText("");
                        address.setText("");
                        pass.setText("");
                    }

                }else{
                    if(oid.getText().toString().length() != 4) {
                        Toast.makeText(getApplicationContext(), "Owner's ID must be 4 digits ONLY !", Toast.LENGTH_SHORT).show();
                        oname.setText("");
                        oid.setText("");
                        gender.setText("");
                        address.setText("");
                        pass.setText("");
                    }
                        if (integer_oid(oid.getText().toString()) != 1){
                            Toast.makeText(getApplicationContext(), "Owner's ID must be Integer !", Toast.LENGTH_SHORT).show();
                            oname.setText("");
                            oid.setText("");
                            gender.setText("");
                            address.setText("");
                            pass.setText("");
                        }

                            if (string_oname(oname.getText().toString()) != 1){
                                Toast.makeText(getApplicationContext(), "Owner's name must be String!", Toast.LENGTH_SHORT).show();
                                oname.setText("");
                                oid.setText("");
                                gender.setText("");
                                address.setText("");
                                pass.setText("");
                            }
                            if(oname.getText().toString().compareTo("") == 0
                                    || oid.getText().toString().length() == 0
                                    || pass.getText().toString().compareTo("") == 0) {
                                Toast.makeText(getApplicationContext(), "Important field(s) empty ! , try again ...", Toast.LENGTH_SHORT).show();
                                oname.setText("");
                                oid.setText("");
                                gender.setText("");
                                address.setText("");
                                pass.setText("");
                            }
                    if (like_admin_id(oid.getText().toString()) == 0){
                        Toast.makeText(getApplicationContext(), "WRONG ID ! , try again ...", Toast.LENGTH_SHORT).show();
                        oname.setText("");
                        oid.setText("");
                        gender.setText("");
                        address.setText("");
                        pass.setText("");
                    }
                    if (check_gender(gender.getText().toString()) == 0){
                        Toast.makeText(getApplicationContext(), "Invalid gender ! , try again ...", Toast.LENGTH_SHORT).show();
                        oname.setText("");
                        oid.setText("");
                        gender.setText("");
                        address.setText("");
                        pass.setText("");
                    }
                }
            }
        });


    }

    private int check_gender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))
            return 1 ;
        else
            return 0 ;
    }

    private int like_admin_id(String id) {
        if(id.compareTo("0000") == 0)
            return 0 ;
        else
            if (id.compareTo("1111") == 0)
                return 0 ;
        else
                if (id.compareTo("2222") == 0)
                    return 0 ;
        else
                if(id.compareTo("3333") == 0)
                    return 0 ;
        else
                    if (id.compareTo("4444") == 0)
                        return 0 ;
        else
                        if (id.compareTo("5555") == 0)
                            return 0 ;
        else
                            if (id.compareTo("6666") == 0)
                                return 0 ;
        else
                                if (id.compareTo("7777") == 0)
                                    return 0 ;
        else
                                    if (id.compareTo("8888") == 0)
                                        return 0 ;
        else
                                        if (id.compareTo("9999") == 0)
                                            return 0 ;
        return 1 ;
    }

    private int string_oname(String name) {
        int flag = 0 ;
        char [] c = name.toCharArray() ;
        for (int i = 0 ; i < c.length ; i++){
            if (Character.isLetter(c[i]))
                flag = 1 ;
            else
                return 0 ;
        }
        return 1 ;
    }

    private int integer_oid(String id) {
        int flag = 0 ;
        char [] c = id.toCharArray() ;
        for (int i = 0 ; i < c.length ; i++){
            if (Character.isDigit(c[i]))
                flag = 1 ;
            else
                return 0 ;
        }
        return 1 ;
    }
}