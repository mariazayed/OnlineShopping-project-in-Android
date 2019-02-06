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
public class Delete_Owner extends Activity {

    private Switch mySwitch;
    private Button delete ;
    private EditText uid ;
    private DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        db = new DBHelper(getApplicationContext()) ;
        uid = (EditText) findViewById(R.id.id) ;
        delete = (Button) findViewById(R.id.delete) ;
        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF

        // back to the home page
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Delete_Owner.this, Admin.class);
                    Delete_Owner.this.startActivity(intent);
                    finish();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result1 = uid.getText().toString().length() ;
                int result2  = is_int(uid.getText().toString()) ;
                int result3 = not_like_admin_id(uid.getText().toString()) ;
                int result4 =  db.check_owner_validity(uid.getText().toString()) ;
                if(result1 == 4 && result2 == 1 && result3 == 1 && result4 == 0) {
                    db.delete_owner(uid.getText().toString());
                    Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    uid.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong ID ! , Try again ...", Toast.LENGTH_SHORT).show();
                    uid.setText("");
                }
            }
        });

    }

    private int is_int(String id) {
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

    private int not_like_admin_id(String id) {
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

}
