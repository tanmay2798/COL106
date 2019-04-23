package com.example.tanmaygoyal.quikr_sms;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Tanmay on 01-07-2017.
 */

public class SampleSQLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 12;
    public static final String DATABASE_NAME = "sample_database";
  //  public static final String PERSON_COLUMN_GENDER = "gender";

    public SampleSQLiteDBHelper(FragmentActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       final String TABLE =  "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
               FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_SENDER + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_BODY + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_PRICE + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_COMPANY + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_TYPE + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_DATE + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_HI + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_MONTH + " TEXT)";


        sqLiteDatabase.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  FeedReaderContract.FeedEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
