package com.example.orderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.CartItem:
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new Model("Boot", getString(R.string.boot), R.drawable.boot ));
        modelList.add(new Model("Safety", getString(R.string.safety), R.drawable.safety));
        modelList.add(new Model("Crozier Sandel", getString(R.string.crozier_sandel), R.drawable.crozier_sandel));
        modelList.add(new Model("Bata", getString(R.string.bata), R.drawable.bata));
        modelList.add(new Model("Heel Sandals", getString(R.string.heel_sandals), R.drawable.heel_sandals));
        modelList.add(new Model("Louis", getString(R.string.louis), R.drawable.louis));
        modelList.add(new Model("Converse", getString(R.string.converse), R.drawable.converse));
        modelList.add(new Model("Sports", getString(R.string.sports), R.drawable.sports));
        modelList.add(new Model("Sneaker", getString(R.string.sneakers), R.drawable.sneakers));

        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);




    }
}