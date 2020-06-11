package com.premankoding.mcd5.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.premankoding.mcd5.database.FavoriteDataHelper;
import com.premankoding.mcd5.database.MovieContract;
import com.premankoding.mcd5.database.TvContract;

import java.util.Objects;

public class FavoriteProvider extends ContentProvider {

    public static final int ALL_FILM = 100;
    public static final int FILM_WITH_ID = 101;

    public static final int ALL_TV = 200;
    public static final int TV_WITH_ID = 301;


    private FavoriteDataHelper favoriteDataHelper;
    public SQLiteDatabase db;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PART_TASKS, ALL_FILM);
        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PART_TASKS + "/#", FILM_WITH_ID);

        uriMatcher.addURI(TvContract.AUTHORITY, TvContract.PART_TASKS, ALL_TV);
        uriMatcher.addURI(TvContract.AUTHORITY, TvContract.PART_TASKS + "/#", TV_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        favoriteDataHelper = new FavoriteDataHelper(context);
        return false;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = favoriteDataHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (match){
            case ALL_FILM:
                retCursor = db.query(MovieContract.MovieEntry.Table_Name,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case ALL_TV:
                retCursor = db.query(TvContract.TvEntry.Table_Name,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        db = favoriteDataHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri; // URI to be returned

        long id;

        switch (match){
            case ALL_FILM:
                id = db.insert(MovieContract.MovieEntry.Table_Name, null, contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(MovieContract.MovieEntry.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            case ALL_TV:
                id = db.insert(TvContract.TvEntry.Table_Name, null, contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(TvContract.TvEntry.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        db = favoriteDataHelper.getWritableDatabase();
        int numRowsDeleted;

        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {
            case ALL_FILM:
                numRowsDeleted = favoriteDataHelper.getWritableDatabase().delete(
                        MovieContract.MovieEntry.Table_Name,
                        selection,
                        selectionArgs);
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + MovieContract.MovieEntry.Table_Name + "'");
                break;
            case FILM_WITH_ID:
                numRowsDeleted = db.delete(MovieContract.MovieEntry.Table_Name, MovieContract.MovieEntry.Column_Id + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + MovieContract.MovieEntry.Table_Name + "'");
                break;
            case ALL_TV:
                numRowsDeleted = favoriteDataHelper.getWritableDatabase().delete(
                        TvContract.TvEntry.Table_Name,
                        selection,
                        selectionArgs);
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TvContract.TvEntry.Table_Name + "'");
                break;
            case TV_WITH_ID:
                numRowsDeleted = db.delete(TvContract.TvEntry.Table_Name, TvContract.TvEntry.Column_Id + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TvContract.TvEntry.Table_Name + "'");
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        /* If we actually deleted any rows, notify that searchable change has occurred to this URI */
        if (numRowsDeleted != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
