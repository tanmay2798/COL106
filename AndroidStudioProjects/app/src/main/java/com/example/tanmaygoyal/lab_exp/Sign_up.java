package com.example.tanmaygoyal.lab_exp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    public void submit(View v){
        EditText name = findViewById(R.id.editText);
        EditText entrynumber = findViewById(R.id.editText2);
        EditText department = findViewById(R.id.editText3);
        EditText username = findViewById(R.id.editText6);
        EditText password = findViewById(R.id.editText4);
        EditText cnfpassword = findViewById(R.id.editText5);
        String[] str = new String[5];
        str[0]=name.getText().toString();
        str[1]=entrynumber.getText().toString();
        str[2]=department.getText().toString();
        str[3]=username.getText().toString();
        str[4]=password.getText().toString();


        if(password.getText().toString().equals(cnfpassword.getText().toString())){
            SharedPreferences details = getSharedPreferences("details", MODE_PRIVATE);
            SharedPreferences.Editor edit = details.edit();
            edit.clear();
            edit.putString("name",str[0]);
            edit.putString("entrynumber",str[1]);
            edit.putString("department",str[2]);
            edit.putString("username",str[3]);
            edit.putString("password",str[4]);
            edit.commit();
            Intent in = new Intent(this,MainActivity.class);
            startActivity(in);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "password different", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(this,Sign_up.class);
            startActivity(in);
            finish();
        }



    }
}
