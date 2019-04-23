package com.example.tanmaygoyal.lab_exp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void login(View v){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        SharedPreferences details = getApplicationContext().getSharedPreferences("details", MODE_PRIVATE);
        String user = details.getString("username","");
        String pass = details.getString("password","");
        if(user.equals(username.getText().toString()) && pass.equals(password.getText().toString())){
            Intent in = new Intent(this,home.class);
            startActivity(in);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "invalid credentials", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(this,MainActivity.class);
            startActivity(in);
            finish();
        }

    }

    public void signup(View v){
        Intent in = new Intent(this,Sign_up.class);
        startActivity(in);
        finish();
    }
}
