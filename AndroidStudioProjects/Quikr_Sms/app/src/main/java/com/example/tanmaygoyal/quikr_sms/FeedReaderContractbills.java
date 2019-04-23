package com.example.tanmaygoyal.quikr_sms;

import android.provider.BaseColumns;

/**
 * Created by Tanmay on 10-07-2017.
 */

public final class FeedReaderContractbills {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContractbills() {
    }

    /* Inner class that defines the table contents */
    public static class FeedEntrybills implements BaseColumns {
        public static final String TABLE_NAMEBILLS = "entry";
        public static final String COLUMN_NAME_SENDER = "sender";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_DUE = "due";


    }
}
