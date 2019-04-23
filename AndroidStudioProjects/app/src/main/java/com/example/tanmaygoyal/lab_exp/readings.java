package com.example.tanmaygoyal.lab_exp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class readings extends AppCompatActivity {

    private String TAG;
    private DatabaseReference databaseReference;
    ArrayList<String> headings = new ArrayList<String>();
    public static readingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);

        headings.add("one");
        headings.add("two");
        headings.add("three");
        headings.add("four");
        headings.add("five");
        headings.add("six");
        databaseReference = FirebaseDatabase.getInstance().getReference("name");
        initRecyclerView2();
    }

    public void submit(View v){


        for(int i=0;i<adapter.getItemCount();i++){
            EditText edt = findViewById(R.id.reading1);
            edt.performClick();
        }
        ArrayList<String> h = adapter.make();
        Toast.makeText(getApplicationContext(), h.toString(), Toast.LENGTH_SHORT).show();
        user user = new user(h.get(0),h.get(1),h.get(2),h.get(3),h.get(4),h.get(5));
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(user);

        Intent in =  new Intent(this,home.class);
        startActivity(in);
        finish();

    }

    private void initRecyclerView2(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new readingsAdapter(this, headings);
        recyclerView.setAdapter(adapter);

    }
}
