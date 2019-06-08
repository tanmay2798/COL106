package com.example.android.HealthM;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bluetoothchat.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class first extends Activity {

    boolean copy=false;
    String name1="";
    String age1="";

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_layout);
        SharedPreferences one = getSharedPreferences("one", MODE_PRIVATE);
        SharedPreferences.Editor edit = one.edit();
        edit.clear();
        edit.commit();
        databaseReference = FirebaseDatabase.getInstance().getReference("name");

    }


            public void rsubmit(View v) {
                // Send a message using content of the edit text widget


                    EditText textView = (EditText) findViewById(R.id.redt);
                EditText textView1 = (EditText) findViewById(R.id.rage);
                    name1 = textView.getText().toString();
                    age1 = textView1.getText().toString();
                    MainActivity.main=false;
                //    Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                SharedPreferences one = getSharedPreferences("one", MODE_PRIVATE);
                SharedPreferences.Editor edit = one.edit();
                edit.clear();
                edit.putString("name", name1+","+age1);

               // Toast.makeText(getApplicationContext(),"hi1"+ name1, Toast.LENGTH_SHORT).show();
                edit.commit();

                    finish();
                    Intent in = new Intent(this,MainActivity.class);
                    startActivity(in);

            }



            public void nsubmit(View v) {
                // Send a message using content of the edit text widget

                    EditText textView = (EditText) findViewById(R.id.nedt);
                    name1 = textView.getText().toString();
                EditText textView1 = (EditText) findViewById(R.id.rage);
                age1 = textView.getText().toString();
                    //Toast.makeText(getApplicationContext(), name1, Toast.LENGTH_SHORT).show();

                databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                                user user = userSnapshot.getValue(user.class);
                      //          Toast.makeText(getApplicationContext(), user.getName(), Toast.LENGTH_SHORT).show();

                                if(name1.equals(user.getName())) {
                                   copy=true;
                             //       Toast.makeText(getApplicationContext(), "in", Toast.LENGTH_SHORT).show();

                                    break;
                                }

                            }
                            if(copy==true){
                           //     Toast.makeText(getApplicationContext(), "user id already exist", Toast.LENGTH_SHORT).show();
                                name1="";

                            }
                            if(copy==false){
                               MainActivity.main=false;
                             //   Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_SHORT).show();

                                SharedPreferences one = getSharedPreferences("one", MODE_PRIVATE);
                                SharedPreferences.Editor edit = one.edit();
                                edit.clear();
                                edit.putString("name", name1+","+age1);
                           //     Toast.makeText(getApplicationContext(),"hi"+ name1, Toast.LENGTH_SHORT).show();
                                edit.commit();
                                finish();
                                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(in);
                        }

                            copy=false;


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

            }


}
