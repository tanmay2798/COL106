package com.example.tanmaygoyal.quikr_sms;

import android.provider.BaseColumns;

/**
 * Created by Tanmay on 10-07-2017.
 */

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_SENDER = "sender";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_HI = "hi";
        public static final String COLUMN_NAME_MONTH = "month";


    }
}
