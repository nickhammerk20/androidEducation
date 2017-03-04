package com.example.hammer.task_10_contentprovider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hammer.task_10_contentprovider.Config;

/**
 * Created by User on 22.01.2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context,  Config.DB_NAME, null, Config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL(Config.COMMAND_CREATE);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {db.beginTransaction();
        db.execSQL(Config.COMMAND_DELETE);
        onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
