package com.project.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Maria on 14-May-17.
 */
public class Show_Products extends Activity  {

    GridView gridView = null ;
    ArrayList<Product> list = null ;
    Product_List_Adapter adapter = null ;
    private Switch mySwitch;
    private DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        gridView = (GridView) findViewById(R.id.gv) ;
        list = new ArrayList<>();
        //adapter = new Product_List_Adapter(this, R.layout.activity_product_items, list);
        //gridView.setAdapter(adapter);

        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF
        db = new DBHelper(getApplicationContext()) ;

       Cursor cursor = db.get_products() ;
       list.clear();

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(db.PRODUCT_ID));
                String name = cursor.getString(cursor.getColumnIndex(db.PRODUCT_NAME));
                String price = cursor.getString(cursor.getColumnIndex(db.PRODUCT_PRICE));
                String discount = cursor.getString(cursor.getColumnIndex(db.DISCOUNT));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(db.PRODUCT_IMAGE));

                list.add(new Product(id, name, price, discount, image));
            }

        adapter = new Product_List_Adapter(this, R.layout.activity_product_items, list);
        gridView.setAdapter(adapter);

        // back to the home page
       mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Show_Products.this, Buy.class);
                    Show_Products.this.startActivity(intent);
                    finish();
                        }

                }
        });
    }
}
