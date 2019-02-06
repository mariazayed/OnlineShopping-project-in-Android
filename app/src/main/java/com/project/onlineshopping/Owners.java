package com.project.onlineshopping;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Maria on 24-Apr-17.
 */
public class Owners extends Activity{

    private Switch mySwitch;
    private Button choose_image , add_product ;
    private EditText addName , addPrice , offer ;
    private String userChoosenTask ;
    ImageView imageView ;
    private DBHelper db ;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    int pid = 50 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        db = new DBHelper(getApplicationContext()) ;
        addName = (EditText) findViewById(R.id.addName) ;
        addPrice = (EditText) findViewById(R.id.addPrice) ;
        offer = (EditText) findViewById(R.id.discount) ;
        choose_image = (Button) findViewById(R.id.btnChoose) ;
        add_product = (Button) findViewById(R.id.btnAdd) ;
        imageView = (ImageView) findViewById(R.id.imageView);
        mySwitch = (Switch) findViewById(R.id.back);
        mySwitch.setChecked(false); //set the switch to OFF

        // back to the home page
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(Owners.this, Login.class);
                    Owners.this.startActivity(intent);
                    finish();
                }
            }
        });

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage() ;
            }
        });

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String name = addName.getText().toString().trim() ;
                    String discount = offer.getText().toString().trim() ;
                    String price = addPrice.getText().toString().trim() ;
                    byte [] image = imageViewToByte(imageView) ;

                    if (discount.compareTo("") == 0)
                        discount = "0" ;

                    if (name.compareTo("") == 0 || price.compareTo("") == 0){
                        Toast.makeText(getApplicationContext(), "Important fields empty !", Toast.LENGTH_SHORT).show();
                        addName.setText("");
                        addPrice.setText("");
                        imageView.setImageResource(R.mipmap.ic_launcher);
                        offer.setText("");
                    }else {
                        int result1 = check_name(name) ;
                        int result2 = check_price(price) ;
                        int result3 = check_offer(discount) ;
                        if (result1 == 0 || result2 == 0 || result3 == 0){
                            Toast.makeText(getApplicationContext(), "Invalid input(s) !", Toast.LENGTH_SHORT).show();
                            addName.setText("");
                            addPrice.setText("");
                            imageView.setImageResource(R.mipmap.ic_launcher);
                            offer.setText("");
                        }else {
                            db.insert_product(Integer.toString(pid), name, discount, price, image);
                            Toast.makeText(getApplicationContext(), "Added successfully !", Toast.LENGTH_SHORT).show();
                            addName.setText("");
                            addPrice.setText("");
                            imageView.setImageResource(R.mipmap.ic_launcher);
                            offer.setText("");
                            pid = pid + 1;
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error occurred !", Toast.LENGTH_SHORT).show();
                    addName.setText("");
                    addPrice.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    offer.setText("");
                }
            }
        });
    }

    private int check_offer(String discount) {
        char [] c = discount.toCharArray() ;
        for (int i = 0 ; i < c.length ; i++){
            if (Character.isLetter(c[i]))
                return 0 ;
        }
        if (Integer.parseInt(discount) >= 0 && Integer.parseInt(discount) <= 90)
            return 1 ;
        else
            return 0 ;
    }

    private int check_price(String price) {
        char [] c = price.toCharArray() ;
        for (int i = 0 ; i < c.length ; i++){
            if (Character.isLetter(c[i]))
                return 0 ;
        }
        if (Integer.parseInt(price) >= 1 && Integer.parseInt(price) <= 100)
            return 1 ;
        else
            return 0 ;
    }

    private int check_name(String name) {
        char [] c = name.toCharArray() ;
        for (int i = 0 ; i < c.length ; i++){
            if (Character.isDigit(c[i]))
                return 0 ;
        }
        return 1 ;
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Owners.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(Owners.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageView.setImageBitmap(bm);
    }

}
