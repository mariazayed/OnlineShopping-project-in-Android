package com.project.onlineshopping;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maria on 14-May-17.
 */

public class Product_List_Adapter extends BaseAdapter{
    private Context context;
    private  int layout;
    private ArrayList<Product> productsList;

    public Product_List_Adapter(Context context, int layout, ArrayList<Product> productsList) {
        this.context = context;
        this.layout = layout;
        this.productsList = productsList;
    }
    @Override
    public int getCount() {
        return productsList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return productsList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    private class ViewHolder{
        ImageView iv ;
        TextView productName , price , offer , iddd ;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();

     //   LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    //    View row = inflater.inflate(R.layout.activity_product_items,viewGroup, false);
        View row = view;

     /*   holder.productName = (TextView) row.findViewById(R.id.txtName);
        holder.price = (TextView) row.findViewById(R.id.txtPrice) ;
        holder.offer = (TextView) row.findViewById(R.id.txtOffer) ;
        holder.iddd = (TextView) row.findViewById(R.id.txtID) ;
        holder.iv = (ImageView) row.findViewById(R.id.imageView2) ; */

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.productName = (TextView) row.findViewById(R.id.txtName);
            holder.price = (TextView) row.findViewById(R.id.txtPrice) ;
            holder.offer = (TextView) row.findViewById(R.id.txtOffer) ;
            holder.iddd = (TextView) row.findViewById(R.id.txtID) ;
            holder.iv = (ImageView) row.findViewById(R.id.imageView2) ;
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

     //   Food food = foodsList.get(position);
        Product product = productsList.get(position);

        holder.productName.setText(product.getPname());
        holder.iddd.setText("ID : " + Integer.toString(product.getPid()));
        holder.price.setText("Price : " + product.getPprice());
        if (product.getDiscount().compareTo("0") == 0)
            holder.offer.setText("NO discount");
        else
            holder.offer.setText("Discount : " + product.getDiscount() + "%");

      //  byte[] foodImage = food.getImage();

        byte[] im = product.getPimage();

        if (im != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(im, 0, im.length);
            holder.iv.setImageBitmap(bitmap);
        }
/*
        Product temp=(Product)productsList.get(position);
        productName.setText(temp.getPname());

        Product temp2=(Product)productsList.get(position);
        price.setText("Price : " + temp2.getPprice());

        Product temp3=(Product)productsList.get(position);
        if (temp3.getDiscount().compareTo("") == 0)
            offer.setText("NO discount");
        else
            offer.setText("Discount : " + temp3.getDiscount() + "%");

        Product temp4=(Product)productsList.get(position);
        id.setText("ID : " + Integer.toString(temp4.getPid()));

        Product temp5=(Product)productsList.get(position);
 //       Bitmap bitmap = BitmapFactory.decodeByteArray(temp5.getPimage(), 0, temp5.getPimage().length);
        if (temp5.getPimage() != null) {
            ByteArrayInputStream bitmap = new ByteArrayInputStream(temp5.getPimage());
            Bitmap theImage = BitmapFactory.decodeStream(bitmap);
            iv.setImageBitmap(theImage);
        }
        Product product = productsList.get(position);
*/
        return row;
    }
}
