package com.example.tanmaygoyal.quikr_sms;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Tanmay on 01-07-2017.
 */

public class SampleSQLiteDBHelperbills extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bill_database";
  //  public static final String PERSON_COLUMN_GENDER = "gender";

    public SampleSQLiteDBHelperbills(FragmentActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       final String TABLE =  "CREATE TABLE " + FeedReaderContractbills.FeedEntrybills.TABLE_NAMEBILLS + " (" +
               FeedReaderContractbills.FeedEntrybills._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
               + FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_SENDER + " TEXT,"
               + FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_BODY + " TEXT,"
               + FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_PRICE + " TEXT,"
               + FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_COMPANY + " TEXT,"
               + FeedReaderContractbills.FeedEntrybills.COLUMN_NAME_DUE+ " TEXT)";


        sqLiteDatabase.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  FeedReaderContractbills.FeedEntrybills.TABLE_NAMEBILLS);
        onCreate(sqLiteDatabase);
    }


}
