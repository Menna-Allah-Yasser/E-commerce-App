package com.example.orderapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderapp.Database.*;

public class CartAdapter extends CursorAdapter {


    public CartAdapter(Context context, Cursor cursor) {

        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        // getting theviews

        TextView shoeName, price, quantity;
        final Button deleteOne;

        shoeName = view.findViewById(R.id.shoeNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        quantity = view.findViewById(R.id.quantityinOrderSummary);
        deleteOne = view.findViewById(R.id.btn_delete);


        // getting the values by first getting the position of their columns

        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofshoe = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofdrink = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
        final int id = cursor.getColumnIndex(OrderContract.OrderEntry._ID);


        String nameofdrink = cursor.getString(name);
        String pricesofshoe = cursor.getString(priceofshoe);
        String quantitysofdrink = cursor.getString(quantityofdrink);
        final String ID = cursor.getString(id);


// putting data in the cart
        shoeName.setText(nameofdrink);
        price.setText(pricesofshoe);
        quantity.setText(quantitysofdrink);

        deleteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "order "+ ID+" deleted", Toast.LENGTH_SHORT).show();
//                Log.i("TAG", "order = "+ ID+" deleted");
                context.getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, "_ID=?", new String[]{ID});
            }
        });

    }
}
