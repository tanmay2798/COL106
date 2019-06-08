package com.example.android.HealthM;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Tanmay on 01-07-2017.
 */

public class SampleSQLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "sample_database";
  //  public static final String PERSON_COLUMN_GENDER = "gender";

    public SampleSQLiteDBHelper(FragmentActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       final String TABLE =  "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
               FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_HEIGHT + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_TEMPERATURE + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_LUNG + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_BP + " TEXT,"
               + FeedReaderContract.FeedEntry.COLUMN_NAME_HEART + " TEXT)";

        sqLiteDatabase.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  FeedReaderContract.FeedEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
