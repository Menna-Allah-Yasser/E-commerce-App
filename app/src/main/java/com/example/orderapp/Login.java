package com.example.orderapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderapp.Database.OrderHelper;

public class Login extends AppCompatActivity {
    EditText email , password;
    Button btnSubmit , btnSignup;
    OrderHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnSubmit = findViewById(R.id.Sign);
        btnSignup=findViewById(R.id.btnsignup);
        dbHelper = new OrderHelper(this);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailCheck = email.getText().toString();
                String passCheck = password.getText().toString();
                Cursor cursor = dbHelper.getData();
                if (emailCheck.equals("") || passCheck.equals("")) {
                    Toast.makeText(Login.this, "All fields Required", Toast.LENGTH_LONG).show();
                } else {
                    if (cursor.getCount() == 0) {
                        Toast.makeText(Login.this, "No entries Exists", Toast.LENGTH_LONG).show();
                    }
                    if (loginCheck(cursor, emailCheck, passCheck)) {
                        Intent intent = new Intent(Login.this, MainPageActivity.class);
                        intent.putExtra("email", emailCheck);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Incorrect Password ", Toast.LENGTH_LONG).show();
                        email.setText("");
                        password.setText("");
                    }
                    dbHelper.close();
                }
            }
        });


        final EditText  emailUp,passUp;
        OrderHelper orderHelper;
        emailUp=findViewById(R.id.username);
        passUp=findViewById(R.id.password);
        Button signUpAcc = findViewById(R.id.btnsignup);
        orderHelper = new OrderHelper(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email1 = emailUp.getText().toString();
                String pass1 = passUp.getText().toString();
                if(email1.equals("") || pass1.equals("")){
                    Toast.makeText(Login.this,"All fields Required",Toast.LENGTH_SHORT).show();
                }else {
                    boolean b = dbHelper.insetUserData(email1, pass1);
                    if (b) {
                        Toast.makeText(Login.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, MainPageActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(Login.this, "Failed To insert Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public static boolean loginCheck(Cursor cursor,String emailCheck,String passCheck) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(emailCheck)) {
                if (cursor.getString(2).equals(passCheck)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}