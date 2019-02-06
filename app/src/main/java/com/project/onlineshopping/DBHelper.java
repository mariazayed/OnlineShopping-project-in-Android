package com.project.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.icu.util.DateInterval;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Maria on 25-Apr-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    // DataBase name
    public static final String DATABASE_NAME = "OnlineShopping.db";

    // Data Base version
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String SUPERMARKET_TABLE = "supermarket";
    public static final String OWNER_TABLE = "owner";
    public static final String ADMIN_TABLE = "admin";
    public static final String PRODUCT_TABLE = "product";
    public static final String HAS_OWNER = "has_owner";
    public static final String HAS_PRODUCT = "has_product";

    // Columns names
    // --> Supermarket table
    public static final String SUPERMARKET_ID = "supermarket_id";
    public static final String SUPERMARKET_NAME = "supermarket_name";
    public static final String SUPERMARKET_ADDRESS = "supermarket_address";
    // --> Owner table
    public static final String OWNER_ID = "owner_id";
    public static final String OWNER_NAME = "owner_name";
    public static final String OWNER_GENDER = "owner_gender";
    public static final String OWNER_PASSWORD = "owner_password";
    public static final String OWNER_ADDRESS = "owner_address";
    // --> Admin table
    public static final String ADMIN_ID = "admin_id";
    public static final String ADMIN_NAME = "admin_name";
    public static final String ADMIN_GENDER = "admin_gender";
    public static final String ADMIN_PASSWORD = "admin_password";
    public static final String ADMIN_ADDRESS = "admin_address";
    public static final String A_SUPERMARKET_ID = "a_supermarket_id";
    // --> Product table
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_IMAGE = "product_image";
    public static final String DISCOUNT = "discount";
    // --> Customer table
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_ADDRESS = "customer_address";
    // --> Has_owner table
    public static final String HOWNER_SUPERMARKET_ID = "howner_supermarket_id";
    public static final String HOWNER_OWNER_ID = "howner_owner_id";
    // --> Has_product table
    public static final String HP_SUPERMARKET_ID = "hp_supermarket_id";
    public static final String HP_PRODUCT_ID = "hp_product_id";

    // Create Table Statements
    private static final String CREATE_TABLE_SUPERMARKET = create_supermarket();
    private static final String CREATE_TABLE_OWNER = create_owner();
    private static final String CREATE_TABLE_ADMIN = create_admin();
    private static final String CREATE_TABLE_PRODUCT = create_product();
    private static final String CREATE_TABLE_HAS_OWNER = create_has_owner();
    private static final String CREATE_TABLE_HAS_PRODUCT = create_has_product();

    // --> insert data into supermarket table
    private static final String SUPERMARKET0 = "INSERT INTO " + SUPERMARKET_TABLE + "(" + SUPERMARKET_ID + "," + SUPERMARKET_NAME + ") VALUES"
            + "(000 , 'Tuskys Supermarket')";
    private static final String SUPERMARKET1 = "INSERT INTO " + SUPERMARKET_TABLE + "(" + SUPERMARKET_ID + "," + SUPERMARKET_NAME + "," + SUPERMARKET_ADDRESS + ") VALUES"
            + "(111 , 'DR. Pandas Supermarket' , 'Ramallah')";
    private static final String SUPERMARKET2 = "INSERT INTO " + SUPERMARKET_TABLE + "(" + SUPERMARKET_ID + "," + SUPERMARKET_NAME + "," + SUPERMARKET_ADDRESS + ") VALUES"
            + "(222 , 'Carnival Supermarket' , 'Al-Teereh')";
    private static final String SUPERMARKET3 = "INSERT INTO " + SUPERMARKET_TABLE + "(" + SUPERMARKET_ID + "," + SUPERMARKET_NAME + ") VALUES"
            + "(333 , 'Country Groceries Supermarket')";
    private static final String SUPERMARKET4 = "INSERT INTO " + SUPERMARKET_TABLE + "(" + SUPERMARKET_ID + "," + SUPERMARKET_NAME + "," + SUPERMARKET_ADDRESS + ") VALUES"
            + "(444 , 'Lees Supermarket' , 'Al-Irsal')";

    // --> insert data into admin table
    private static final String ADMIN0 = "INSERT INTO " + ADMIN_TABLE + "(" + ADMIN_ID + "," + ADMIN_NAME + "," + ADMIN_GENDER + "," + ADMIN_PASSWORD + ","
            + ADMIN_ADDRESS + "," + A_SUPERMARKET_ID + ") VALUES"
            + "(0000 , 'Rami' , 'Male' , '12345' , 'Ramallah' , 000)";
    private static final String ADMIN1 = "INSERT INTO " + ADMIN_TABLE + "(" + ADMIN_ID + "," + ADMIN_NAME + "," + ADMIN_GENDER + "," + ADMIN_PASSWORD + ","
            + ADMIN_ADDRESS + "," + A_SUPERMARKET_ID + ") VALUES"
            + "(1111 , 'Maria' , 'Female' , '12345' , 'Ramallah' , 111)";
    private static final String ADMIN2 = "INSERT INTO " + ADMIN_TABLE + "(" + ADMIN_ID + "," + ADMIN_NAME + "," + ADMIN_GENDER + "," + ADMIN_PASSWORD + ","
            + ADMIN_ADDRESS + "," + A_SUPERMARKET_ID + ") VALUES"
            + "(2222 , 'Massa' , 'Female' , '12345' , 'Ramallah' , 222)";
    private static final String ADMIN3 = "INSERT INTO " + ADMIN_TABLE + "(" + ADMIN_ID + "," + ADMIN_NAME + "," + ADMIN_GENDER + "," + ADMIN_PASSWORD + ","
            + ADMIN_ADDRESS + "," + A_SUPERMARKET_ID + ") VALUES"
            + "(3333 , 'Rawand' , 'Female' , '12345' , 'Al-Teereh' , 333)";
    private static final String ADMIN4 = "INSERT INTO " + ADMIN_TABLE + "(" + ADMIN_ID + "," + ADMIN_NAME + "," + ADMIN_GENDER + "," + ADMIN_PASSWORD + ","
            + ADMIN_ADDRESS + "," + A_SUPERMARKET_ID + ") VALUES"
            + "(4444 , 'Sami' , 'Male' , '12345' , 'Al-Irsal' , 444)";

    // --> insert data into owner table
    private static final String OWNER0 = "INSERT INTO " + OWNER_TABLE + "( " + OWNER_ID + "," + OWNER_NAME + "," + OWNER_GENDER + ","
            + OWNER_PASSWORD + ") VALUES"
            + "(0000 , 'Alice' , 'Female' , '54321')";
    private static final String OWNER1 = "INSERT INTO " + OWNER_TABLE + "( " + OWNER_ID + "," + OWNER_NAME + "," + OWNER_GENDER + ","
            + OWNER_PASSWORD + "," + OWNER_ADDRESS + ") VALUES"
            + "(1000 , 'Abram' , 'Male' , '54321' , 'Ramallah')";
    private static final String OWNER2 = "INSERT INTO " + OWNER_TABLE + "( " + OWNER_ID + "," + OWNER_NAME + "," + OWNER_GENDER + ","
            + OWNER_PASSWORD + "," + OWNER_ADDRESS + ") VALUES"
            + "(2000 , 'Adam' , 'Male' , '54321' , 'Ramallah')";
    private static final String OWNER3 = "INSERT INTO " + OWNER_TABLE + "( " + OWNER_ID + "," + OWNER_NAME + "," + OWNER_GENDER + ","
            + OWNER_PASSWORD + "," + OWNER_ADDRESS + ") VALUES"
            + "(3000 , 'Addy' , 'Female' , '54321' , 'Ramallah')";
    private static final String OWNER4 = "INSERT INTO " + OWNER_TABLE + "( " + OWNER_ID + "," + OWNER_NAME + "," + OWNER_GENDER + ","
            + OWNER_PASSWORD + "," + OWNER_ADDRESS + ") VALUES"
            + "(4000 , 'Adele' , 'Female' , '54321' , 'Ramallah')";

    // insert data into product table
    private static final String PRODUCT0 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(1 , 'Kinder Chocolate' , '0' , 3.0)";
    private static final String PRODUCT1 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(2 , 'Kinder Chocolate' , '0' , 3.0)";
    private static final String PRODUCT2 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(3 , 'Kinder Chocolate' , '0' , 3.0)";
    private static final String PRODUCT3 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(4 , 'Kinder Chocolate' , '0' , 3.0)";
    private static final String PRODUCT4 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(5 , 'Bread' , '0' , 3.0)";
    private static final String PRODUCT5 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(6 , 'Bread' , '0' , 3.0)";
    private static final String PRODUCT6 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(7 , 'Bread' , '0' , 3.0)";
    private static final String PRODUCT7 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(8 , 'Bread' , '0' , 3.0)";
    private static final String PRODUCT8 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(9 , 'Bread' , '0' , 3.0)";
    private static final String PRODUCT9 = "INSERT INTO " + PRODUCT_TABLE + "(" + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(10 , 'Bread' , '0' , 3.0)";
    private static final String PRODUCT10 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(11 , 'milk' , '0' , 5.0)" ;
    private static final String PRODUCT11 =  "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(12 , 'milk' , '0' , 5.0)" ;
    private static final String PRODUCT12 =  "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(13 , 'milk' , '0' , 5.0)" ;
    private static final String PRODUCT13 =  "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(14 , 'milk' , '0' , 5.0)" ;
    private static final String PRODUCT14 =  "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(15 , 'milk' , '0' , 5.0)" ;
    private static final String PRODUCT15 =  "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(16 , 'Rice' , '0' , 13.0)" ;
    private static final String PRODUCT16 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(17 , 'Rice' , '0' , 13.0)" ;
    private static final String PRODUCT17 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(18 , 'Rice' , '0' , 13.0)" ;
    private static final String PRODUCT18 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(19 , 'Rice' , '0' , 13.0)" ;
    private static final String PRODUCT19 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(20 , 'Rice' , '0' , 13.0)" ;
    private static final String PRODUCT20 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(21 , 'Chocolate cake' , '5' , 30.0)" ;
    private static final String PRODUCT21 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(22 , 'Chocolate cake' , '5' , 30.0)" ;
    private static final String PRODUCT22 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(23 , 'Chocolate cake' , '5' , 30.0)" ;
    private static final String PRODUCT23 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(24 , 'Chocolate cake' , '5' , 30.0)" ;
    private static final String PRODUCT24 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(25 , 'Chocolate cake' , '5' , 30.0)" ;
    private static final String PRODUCT25 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(26 , 'Sugar' , '0' , 4.0)" ;
    private static final String PRODUCT26 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(27 , 'Sugar' , '0' , 4.0)" ;
    private static final String PRODUCT27 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(28 , 'Sugar' , '0' , 4.0)" ;
    private static final String PRODUCT28 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(29 , 'Sugar' , '0' , 4.0)" ;
    private static final String PRODUCT29 = "INSERT INTO " + PRODUCT_TABLE + "("  + PRODUCT_ID + "," + PRODUCT_NAME
            + "," + DISCOUNT + " , " + PRODUCT_PRICE + ") VALUES"
            + "(30 , 'Sugar' , '0' , 4.0)" ;


    // --> insert data into has_owner table
    private static final String HAS_OWNER0 = "INSERT INTO " + HAS_OWNER + "(" + HOWNER_SUPERMARKET_ID + "," + HOWNER_OWNER_ID + ") VALUES" + "(000 , 0000)";
    private static final String HAS_OWNER1 = "INSERT INTO " + HAS_OWNER + "(" + HOWNER_SUPERMARKET_ID + "," + HOWNER_OWNER_ID + ") VALUES" + "(111 , 1000)";
    private static final String HAS_OWNER2 = "INSERT INTO " + HAS_OWNER + "(" + HOWNER_SUPERMARKET_ID + "," + HOWNER_OWNER_ID + ") VALUES" + "(222 , 2000)";
    private static final String HAS_OWNER3 = "INSERT INTO " + HAS_OWNER + "(" + HOWNER_SUPERMARKET_ID + "," + HOWNER_OWNER_ID + ") VALUES" + "(333 , 3000)";
    private static final String HAS_OWNER4 = "INSERT INTO " + HAS_OWNER + "(" + HOWNER_SUPERMARKET_ID + "," + HOWNER_OWNER_ID + ") VALUES" + "(444 , 4000)";

    // --> insert data into has_product table
    private static final String HAS_PRODUCT0 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(1 , 000)";
    private static final String HAS_PRODUCT1 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(2 , 111)";
    private static final String HAS_PRODUCT2 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(3 , 222)";
    private static final String HAS_PRODUCT3 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(4 , 333)";
    private static final String HAS_PRODUCT4 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(5 , 444)";
    private static final String HAS_PRODUCT5 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(6 , 000)";
    private static final String HAS_PRODUCT6 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(7 , 111)";
    private static final String HAS_PRODUCT7 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(8 , 222)";
    private static final String HAS_PRODUCT8 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(9 , 333)";
    private static final String HAS_PRODUCT9 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(10 , 444)";
    private static final String HAS_PRODUCT10 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(11 , 000)";
    private static final String HAS_PRODUCT11 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(12 , 111)";
    private static final String HAS_PRODUCT12 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(13 , 222)";
    private static final String HAS_PRODUCT13 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(14 , 333)";
    private static final String HAS_PRODUCT14 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(15 , 444)";
    private static final String HAS_PRODUCT15 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(16 , 000)";
    private static final String HAS_PRODUCT16 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(17 , 111)";
    private static final String HAS_PRODUCT17 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(18 , 222)";
    private static final String HAS_PRODUCT18 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(19 , 333)";
    private static final String HAS_PRODUCT19 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(20 , 444)";
    private static final String HAS_PRODUCT20 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(21 , 000)";
    private static final String HAS_PRODUCT21 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(22 , 111)";
    private static final String HAS_PRODUCT22 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(23 , 222)";
    private static final String HAS_PRODUCT23 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(24 , 333)";
    private static final String HAS_PRODUCT24 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(25 , 444)";
    private static final String HAS_PRODUCT25 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(26 , 000)";
    private static final String HAS_PRODUCT26 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(27 , 111)";
    private static final String HAS_PRODUCT27 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(28 , 222)";
    private static final String HAS_PRODUCT28 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(29 , 333)";
    private static final String HAS_PRODUCT29 = "INSERT INTO " + HAS_PRODUCT + "(" + HP_PRODUCT_ID + "," + HP_SUPERMARKET_ID + ") VALUES"
            + "(30 , 444)";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating ALL tables
        db.execSQL(CREATE_TABLE_SUPERMARKET);
        db.execSQL(CREATE_TABLE_OWNER);
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_HAS_OWNER);
        db.execSQL(CREATE_TABLE_HAS_PRODUCT);

        // Adding data to the tables
        db.execSQL(SUPERMARKET0);
        db.execSQL(SUPERMARKET1);
        db.execSQL(SUPERMARKET2);
        db.execSQL(SUPERMARKET3);
        db.execSQL(SUPERMARKET4);
        db.execSQL(ADMIN0);
        db.execSQL(ADMIN1);
        db.execSQL(ADMIN2);
        db.execSQL(ADMIN3);
        db.execSQL(ADMIN4);
        db.execSQL(OWNER0);
        db.execSQL(OWNER1);
        db.execSQL(OWNER2);
        db.execSQL(OWNER3);
        db.execSQL(OWNER4);
        db.execSQL(PRODUCT0);
        db.execSQL(PRODUCT1);
        db.execSQL(PRODUCT2);
        db.execSQL(PRODUCT3);
        db.execSQL(PRODUCT4);
        db.execSQL(PRODUCT5);
        db.execSQL(PRODUCT6);
        db.execSQL(PRODUCT7);
        db.execSQL(PRODUCT8);
        db.execSQL(PRODUCT9);
        db.execSQL(PRODUCT10);
        db.execSQL(PRODUCT11);
        db.execSQL(PRODUCT12);
        db.execSQL(PRODUCT13);
        db.execSQL(PRODUCT14);
        db.execSQL(PRODUCT15);
        db.execSQL(PRODUCT16);
        db.execSQL(PRODUCT17);
        db.execSQL(PRODUCT18);
        db.execSQL(PRODUCT19);
        db.execSQL(PRODUCT20);
        db.execSQL(PRODUCT21);
        db.execSQL(PRODUCT22);
        db.execSQL(PRODUCT23);
        db.execSQL(PRODUCT24);
        db.execSQL(PRODUCT25);
        db.execSQL(PRODUCT26);
        db.execSQL(PRODUCT27);
        db.execSQL(PRODUCT28);
        db.execSQL(PRODUCT29);
        db.execSQL(HAS_OWNER0);
        db.execSQL(HAS_OWNER1);
        db.execSQL(HAS_OWNER2);
        db.execSQL(HAS_OWNER3);
        db.execSQL(HAS_OWNER4);
        db.execSQL(HAS_PRODUCT0);
        db.execSQL(HAS_PRODUCT1);
        db.execSQL(HAS_PRODUCT2);
        db.execSQL(HAS_PRODUCT3);
        db.execSQL(HAS_PRODUCT4);
        db.execSQL(HAS_PRODUCT5);
        db.execSQL(HAS_PRODUCT6);
        db.execSQL(HAS_PRODUCT7);
        db.execSQL(HAS_PRODUCT8);
        db.execSQL(HAS_PRODUCT9);
        db.execSQL(HAS_PRODUCT10);
        db.execSQL(HAS_PRODUCT11);
        db.execSQL(HAS_PRODUCT12);
        db.execSQL(HAS_PRODUCT13);
        db.execSQL(HAS_PRODUCT14);
        db.execSQL(HAS_PRODUCT15);
        db.execSQL(HAS_PRODUCT16);
        db.execSQL(HAS_PRODUCT17);
        db.execSQL(HAS_PRODUCT18);
        db.execSQL(HAS_PRODUCT19);
        db.execSQL(HAS_PRODUCT20);
        db.execSQL(HAS_PRODUCT21);
        db.execSQL(HAS_PRODUCT22);
        db.execSQL(HAS_PRODUCT23);
        db.execSQL(HAS_PRODUCT24);
        db.execSQL(HAS_PRODUCT25);
        db.execSQL(HAS_PRODUCT26);
        db.execSQL(HAS_PRODUCT27);
        db.execSQL(HAS_PRODUCT28);
        db.execSQL(HAS_PRODUCT29);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SUPERMARKET);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_OWNER);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_HAS_OWNER);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_HAS_PRODUCT);

        // Create new tables
        onCreate(db);
    }

    private static String create_supermarket() {
        return "CREATE TABLE " + SUPERMARKET_TABLE + "("
                + SUPERMARKET_ID + " INTEGER PRIMARY KEY NOT NULL , "
                + SUPERMARKET_NAME + " TEXT NOT NULL , "
                + SUPERMARKET_ADDRESS + " TEXT " + ")";
    }

    private static String create_owner() {
        return "CREATE TABLE " + OWNER_TABLE + "("
                + OWNER_ID + " INTEGER PRIMARY KEY NOT NULL ,"
                + OWNER_NAME + " TEXT NOT NULL ,"
                + OWNER_GENDER + " TEXT ,"
                + OWNER_PASSWORD + " TEXT NOT NULL ,"
                + OWNER_ADDRESS + " TEXT " + ")";
    }

    private static String create_admin() {
        return "CREATE TABLE " + ADMIN_TABLE + "("
                + ADMIN_ID + " INTEGER PRIMARY KEY NOT NULL ,"
                + ADMIN_NAME + " TEXT NOT NULL ,"
                + ADMIN_GENDER + " TEXT , "
                + ADMIN_PASSWORD + " TEXT NOT NULL , "
                + ADMIN_ADDRESS + " TEXT , "
                + A_SUPERMARKET_ID + " INTEGER UNIQUE ,"
                + " FOREIGN KEY ( " + A_SUPERMARKET_ID + " ) REFERENCES " +
                SUPERMARKET_TABLE + "( " + SUPERMARKET_ID + " ) ON DELETE NO ACTION " + ")";
    }

    private static String create_product() {
        return "CREATE TABLE " + PRODUCT_TABLE + " ( "
                + PRODUCT_ID + " INTEGER PRIMARY KEY NOT NULL , "
                + PRODUCT_NAME + " TEXT NOT NULL , "
                + DISCOUNT + " TEXT , "
                + PRODUCT_PRICE + " DOUBLE NOT NULL , "
                + PRODUCT_IMAGE + " BLOB "
                + ")";
    }

    private static String create_has_owner() {
        return "CREATE TABLE " + HAS_OWNER + "("
                + HOWNER_SUPERMARKET_ID + " INTEGER , "
                + HOWNER_OWNER_ID + " INTEGER PRIMARY KEY NOT NULL ,"
                + " FOREIGN KEY ( " + HOWNER_SUPERMARKET_ID + " ) REFERENCES " +
                SUPERMARKET_TABLE + "( " + SUPERMARKET_ID + " ) ,"
                + " FOREIGN KEY ( " + HOWNER_OWNER_ID + " ) REFERENCES " +
                OWNER_TABLE + "( " + OWNER_ID + " ) ON DELETE CASCADE ON UPDATE CASCADE " + ")";
    }

    private static String create_has_product() {
        return "CREATE TABLE " + HAS_PRODUCT + "("
                + HP_PRODUCT_ID + " INTEGER REFERENCES " + PRODUCT_TABLE + " , "
                + HP_SUPERMARKET_ID + " INTEGER REFERENCES " + SUPERMARKET_TABLE + " , "
                + "CONSTRAINT P_S_ID PRIMARY KEY ( " + HP_PRODUCT_ID + " , " + HP_SUPERMARKET_ID + " ) " + ")";
    }

    public int check_owner(String oid, String sid, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery =
                "SELECT  * "
                        + "FROM " + SUPERMARKET_TABLE + " s , " + OWNER_TABLE + " o , " + HAS_OWNER + " h "
                        + "WHERE s." + SUPERMARKET_ID + " = h." + HOWNER_SUPERMARKET_ID
                        + " AND o." + OWNER_ID + " = h." + HOWNER_OWNER_ID
                        + " AND '" + oid + "' = o." + OWNER_ID
                        + " AND '" + pass + "' = o." + OWNER_PASSWORD
                        + " AND '" + sid + "' = s." + SUPERMARKET_ID ;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() == 1)
            return 1;
        else
            return 0;
    }

    public int check_admin(String aid, String sid, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery =
                "SELECT  * "
                        + "FROM " + SUPERMARKET_TABLE + " s ," + ADMIN_TABLE + " a "
                        + "WHERE s." + SUPERMARKET_ID + " = a." + A_SUPERMARKET_ID
                        + " AND '" + aid + "' = a." + ADMIN_ID
                        + " AND '" + pass + "' = a." + ADMIN_PASSWORD
                        + " AND '" + sid + "' = s." + SUPERMARKET_ID;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() == 1) {
            return 1;
        } else
            return 0;
    }

    public int check_owner_validity(String oid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery =
                "SELECT  * "
                        + "FROM " + OWNER_TABLE + " o , " + HAS_OWNER + " h , " + SUPERMARKET_TABLE + " s"
                        + " WHERE o." + OWNER_ID + " = h." + HOWNER_OWNER_ID
                        +" AND s." + SUPERMARKET_ID + " = h." + HOWNER_SUPERMARKET_ID
                        + " AND '" + oid + "' = o." + OWNER_ID
                        + " AND '" + Login.sid + "' = s." + SUPERMARKET_ID;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() == 0)
            return 1; // owner not exist
        else
            return 0; // owner exist
    }

    public void add_owner(String name, String id, String gender, String address, String pass) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(OWNER_NAME, name);
        contentValues1.put(OWNER_GENDER, gender);
        contentValues1.put(OWNER_PASSWORD, pass);
        contentValues1.put(OWNER_ADDRESS, address);
        contentValues1.put(OWNER_ID, id);
        db.insert(OWNER_TABLE, null, contentValues1);

        db.close(); // Closing database connection
    }

    public void add_has_owner(String id) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(HOWNER_SUPERMARKET_ID, Login.sid);
        contentValues2.put(HOWNER_OWNER_ID, id);
        db.insert(HAS_OWNER, null, contentValues2);

        db.close(); // Closing database connection
    }

    public Cursor get_all_owners() {
        SQLiteDatabase database = getReadableDatabase();

        String sql ="SELECT o.*"+
                " FROM " + OWNER_TABLE + " o " + " , " + SUPERMARKET_TABLE + " s " + " , " + HAS_OWNER + " h " +
                " WHERE " + "s."+SUPERMARKET_ID + " = '" + Login.sid + "' " +
                " AND o." + OWNER_ID + " = h." + HOWNER_OWNER_ID +
                " AND s." + SUPERMARKET_ID + " = h." + HOWNER_SUPERMARKET_ID ;
        Cursor c = database.rawQuery(sql, null);
        return c ;
    }

    public void delete_owner(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(OWNER_TABLE , OWNER_ID + " = ? ", new String[] { id });
        db.delete(HAS_OWNER , HOWNER_OWNER_ID + " = ? " , new String[]{ id }) ;
    }

    public void insert_product(String pid , String name, String discount , String price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues1 = new  ContentValues();
        contentValues1.put(PRODUCT_ID , pid);
        contentValues1.put(PRODUCT_NAME , name);
        contentValues1.put(PRODUCT_PRICE , price);
        contentValues1.put(DISCOUNT , discount);
        contentValues1.put(PRODUCT_IMAGE , image);
        database.insert(PRODUCT_TABLE, null, contentValues1 );

        ContentValues contentValues2 = new  ContentValues();
        contentValues2.put(HP_PRODUCT_ID , pid);
        contentValues2.put(HP_SUPERMARKET_ID , Login.sid);
        database.insert(HAS_PRODUCT, null, contentValues2 );
    }

    public Cursor get_products(){
        SQLiteDatabase database = getReadableDatabase();

        String sql ="SELECT p.*"+
                " FROM " + PRODUCT_TABLE + " p " +
                " INNER JOIN " + HAS_PRODUCT + " h ON h." +HP_PRODUCT_ID + " = p." + PRODUCT_ID +
                " INNER JOIN " + SUPERMARKET_TABLE + " s ON s." +SUPERMARKET_ID + " = h." + HP_SUPERMARKET_ID +
                " WHERE " + "s."+SUPERMARKET_ID + " = '" + Buy.choosen_supermarket + "' " ;
        Cursor c = database.rawQuery(sql, null);
        return c ;
    }
}