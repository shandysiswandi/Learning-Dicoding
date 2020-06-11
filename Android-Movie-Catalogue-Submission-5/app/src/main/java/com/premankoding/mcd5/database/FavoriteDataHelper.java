package com.premankoding.mcd5.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.premankoding.mcd5.BuildConfig;

public class FavoriteDataHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;

    public FavoriteDataHelper(Context context) {
        super(context, BuildConfig.DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ MovieContract.MovieEntry.Table_Name+"("+
                MovieContract.MovieEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                MovieContract.MovieEntry.Column_Id+" INTEGER ,"+
                MovieContract.MovieEntry.Column_Title+" TEXT ,"+
                MovieContract.MovieEntry.Column_Poster+" TEXT ,"+
                MovieContract.MovieEntry.Column_Overview+" TEXT ,"+
                MovieContract.MovieEntry.Column_Release+" TEXT ,"+
                MovieContract.MovieEntry.Column_Rating+" DOUBLE ,"+
                "UNIQUE ("+MovieContract.MovieEntry.Column_Title+") ON CONFLICT REPLACE);";

        String query2 = "CREATE TABLE "+ TvContract.TvEntry.Table_Name+"("+
                TvContract.TvEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TvContract.TvEntry.Column_Id+" INTEGER ,"+
                TvContract.TvEntry.Column_Title+" TEXT ,"+
                TvContract.TvEntry.Column_Poster+" TEXT ,"+
                TvContract.TvEntry.Column_Overview+" TEXT ,"+
                TvContract.TvEntry.Column_Release+" TEXT ,"+
                TvContract.TvEntry.Column_Rating+" DOUBLE ,"+
                "UNIQUE ("+TvContract.TvEntry.Column_Title+") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MovieContract.MovieEntry.Table_Name);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TvContract.TvEntry.Table_Name);
    }
}
