package com.example.android.HealthM;

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
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_TEMPERATURE = "temperature";
        public static final String COLUMN_NAME_LUNG = "lung";
        public static final String COLUMN_NAME_BP = "bp";
        public static final String COLUMN_NAME_HEART = "heart";

    }
}
