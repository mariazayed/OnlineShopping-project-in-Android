package com.project.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Maria on 04-May-17.
 */
public class Show_Owners extends Activity {

    private Switch mySwitch ;
    private TextView info ;
    private Cursor cursor ;
    private DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        db = new DBHelper(getApplicationContext()) ;
        info = (TextView) findViewById(R.id.info) ;
        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF

        // back to the home page
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Show_Owners.this, Admin.class);
                    Show_Owners.this.startActivity(intent);
                    finish();
                }
            }
        });

        Cursor rs = db.get_all_owners() ;
        StringBuffer sb = new StringBuffer() ;
        if (rs.moveToFirst()) {
            do {
                String name = rs.getString(rs.getColumnIndex(db.OWNER_NAME));
                String gender = rs.getString(rs.getColumnIndex(db.OWNER_GENDER));
                String id = rs.getString(rs.getColumnIndex(db.OWNER_ID));
                String address = rs.getString(rs.getColumnIndex(db.OWNER_ADDRESS));
                sb.append("  Name : " + name + "\n  ID : " + id +
                        "  \n Gender : " + gender + "\n  Address : " + address + "\n\n" ) ;
                info.setText(sb);
            } while (rs.moveToNext());
        }
    }
}
