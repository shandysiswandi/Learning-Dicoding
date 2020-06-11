package com.premankoding.mcd5.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class TvContract {
    public static final String AUTHORITY = "com.premankoding.mcd5";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PART_TASKS = "tv_favorite";

    public static final class TvEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI
                .buildUpon()
                .appendPath(PART_TASKS)
                .build();

        public static final String Table_Name = "tv_favorite";
        public static final String Column_Id = "id";
        public static final String Column_Poster = "poster";
        public static final String Column_Title = "title";
        public static final String Column_Overview = "overview";
        public static final String Column_Release = "release";
        public static final String Column_Rating = "rating";
    }
}
