package com.example.tanmaygoyal.lab_exp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private static ArrayList<String> arr = new ArrayList<>();
    private static ArrayList<String> arrcheck = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseReference = FirebaseDatabase.getInstance().getReference("name");
        arrcheck.add("2");
        arrcheck.add("1");
        arrcheck.add("3");
        arrcheck.add("5");
        arrcheck.add("4");
        arrcheck.add("7");
    }

    public void readings(View v){
        Intent in = new Intent(this,readings.class);
        startActivity(in);
    }

    public void checker(View v){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    user user = userSnapshot.getValue(user.class);
                    arr.clear();
                    arr.add(user.getOne());
                    arr.add(user.getTwo());
                    arr.add(user.getThree());
                    arr.add(user.getFour());
                    arr.add(user.getFive());
                    arr.add(user.getSix());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        TextView edt = findViewById(R.id.editText7);
        for(int i=0;i<arr.size();i++){
            if(!arr.get(i).equalsIgnoreCase(arrcheck.get(i))){
                edt.setText("INCORRECT");
                break;
            }else{
                edt.setText("CORRECT");
            }

        }
    }
}
