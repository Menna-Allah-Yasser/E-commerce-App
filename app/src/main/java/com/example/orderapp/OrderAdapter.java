package com.example.orderapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderapp.MenuItems.CrozierSandel;
import com.example.orderapp.MenuItems.HeelSandals;
import com.example.orderapp.MenuItems.Boot;
import com.example.orderapp.MenuItems.Safety;
import com.example.orderapp.MenuItems.Sports;

import com.example.orderapp.MenuItems.Bata;
import com.example.orderapp.MenuItems.Sneaker;
import com.example.orderapp.MenuItems.Converse;
import com.example.orderapp.MenuItems.Louis;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
                this.context = context;
                this.modelList = modelList;
    }
//TO SHOW THE ITEMS IN THE RECYCLER VIEW
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);

    }
//To change data in each item of the recycler view
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our  Recyclerview
        // for each item in the list

        String nameofshoe = modelList.get(position).getmshoeName();
        String descriptionofshoe = modelList.get(position).getmshoeDetail();
        int images = modelList.get(position).getmshoePhoto();

        holder.mshoeName.setText(nameofshoe);
        holder.mshoeDescription.setText(descriptionofshoe);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        // to know how many times it will be shown
        return modelList.size();
        //return 3;
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView mshoeName, mshoeDescription;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mshoeName = itemView.findViewById(R.id.shoeName);
            mshoeDescription = itemView.findViewById(R.id.shoedescription);
            imageView = itemView.findViewById(R.id.shoeImage);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            // lets get the position of the view in list and then work on it

            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent = new Intent(context, Boot.class);
                context.startActivity(intent);
            }

            if (position == 1) {
                Intent intent2 = new Intent(context, Safety.class);
                context.startActivity(intent2);
            }
            if (position == 2) {
                Intent intent3 = new Intent(context, CrozierSandel.class);
                context.startActivity(intent3);
            }
            if (position == 3) {
                Intent intent4 = new Intent(context, Bata.class);
                context.startActivity(intent4);
            }
            if (position == 4) {
                Intent intent5 = new Intent(context, HeelSandals.class);
                context.startActivity(intent5);
            }
            if (position == 5) {
                Intent intent6 = new Intent(context, Louis.class);
                context.startActivity(intent6);
            }
            if (position == 6) {
                Intent intent7 = new Intent(context, Converse.class);
                context.startActivity(intent7);
            }
            if (position == 7) {
                Intent intent8 = new Intent(context, Sports.class);
                context.startActivity(intent8);
            }
            if (position == 8) {
                Intent intent9 = new Intent(context, Sneaker.class);
                context.startActivity(intent9);
            }
        }
    }
}
