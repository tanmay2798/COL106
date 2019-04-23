package com.example.tanmaygoyal.quikr_sms;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.tanmaygoyal.quikr_sms.FeedReaderContract.FeedEntry.TABLE_NAME;

public class Messages extends AppCompatActivity {


    int i=0;


     Cursor mCursor;

    SampleSQLiteDBHelper dbHelper;
    private SQLiteDatabase mdb;

    String selection;

    String date;
    String smsDate;
    String name1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        dbHelper = new SampleSQLiteDBHelper(this);
        mdb = dbHelper.getWritableDatabase();
        mdb = dbHelper.getReadableDatabase();
        //cursor = get();
        mCursor = get();



            ListView lv = (ListView)findViewById(R.id.smsList);
            if(fetch()!=null){
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fetch());
                lv.setAdapter(adapter);
            }

        }

    public ArrayList<String> fetch(){

        SharedPreferences one = getApplicationContext().getSharedPreferences("one", MODE_PRIVATE);
        name1 = one.getString("name","");

        ArrayList<String> sms = new ArrayList<String>();

    mCursor.moveToFirst();
        if (name1.equalsIgnoreCase(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)))) {
            String address = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER));
            String body = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY));
            String price = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE));
            sms.add("Address = " + address + "\n SMS = " + body+"\n price = "+price);
        }


        while (mCursor.moveToNext()){

            if (name1.equalsIgnoreCase(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)))) {
                String address = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER));
                String body = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY));
                String price = mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE));
                sms.add("Address = " + address + "\n SMS = " + body+"\n price = "+price);
            }
        }

            return sms;

    }



    public void viewc(View v){
        mCursor.moveToFirst();

        while(mCursor.moveToNext()){
            if(name1.equalsIgnoreCase(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY)))) {

                toastMessage(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER)));
                toastMessage(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_BODY)));
                toastMessage(mCursor.getString(mCursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE)));
            }
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    private Cursor get(){
        return mdb.query(
                TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                FeedReaderContract.FeedEntry._ID
        );
    }

}
