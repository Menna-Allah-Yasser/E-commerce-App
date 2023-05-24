package com.example.orderapp.MenuItems;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderapp.Database.OrderContract;
import com.example.orderapp.R;
import com.example.orderapp.SummaryActivity;

public class CrozierSandel extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    // first of all we will get the views that are  present in the layout of info

    ImageView imageView ;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, descdescription, Price , Items , shoeName;

    Button buyit;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity  = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        Price = findViewById(R.id.price);
        descdescription = findViewById(R.id.descriptioninfo);
        Items = findViewById(R.id.quantity2);
        shoeName = findViewById(R.id.shoeNameInfo);
        buyit = findViewById(R.id.buyit);

        // setting the name of drink

        shoeName.setText("Crozier Sandal");
        descdescription.setText(R.string.crozier_sandel);
        imageView.setImageResource(R.drawable.crozier_sandel);


        buyit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrozierSandel.this, SummaryActivity.class);
                startActivity(intent);
                // once this button is clicked we want to save our values in the database and send those values
                // right away to summary activity where we display the order info

                SaveCart();
            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // coffee price
                int basePrice = 100;
                quantity++;
                displayQuantity();
                int shoePrice = basePrice * quantity;
                String setnewPrice = String.valueOf(shoePrice);
                Price.setText("Price: "+setnewPrice+"$");


                // checkBoxes functionality


            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 100;
                // because we dont want the quantity go less than 0
                if (quantity == 0) {
                    Toast.makeText(CrozierSandel.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int shoePrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(shoePrice);
                    Price.setText("Price: "+setnewPrice+"$");

                    // checkBoxes functionality
                }
            }
        });



    }

    private boolean SaveCart() {

        // getting the values from our views
        String name = shoeName.getText().toString();
        String price = Price.getText().toString();
        String quantity = quantitynumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);


        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri==null) {
                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success  adding to Cart", Toast.LENGTH_SHORT).show();

            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }


    private void displayQuantity() {

        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY
        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);


            String nameofshoe = cursor.getString(name);
            String priceofshoe = cursor.getString(price);
            String quantityofshoe = cursor.getString(quantity);

            shoeName.setText(nameofshoe);
            Price.setText(priceofshoe);
            quantitynumber.setText(quantityofshoe);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


        shoeName.setText("");
         Price.setText("");
        quantitynumber.setText("");

    }
}