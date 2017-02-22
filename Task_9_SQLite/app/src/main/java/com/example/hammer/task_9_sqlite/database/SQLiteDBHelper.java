package com.example.hammer.task_9_sqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hammer.task_9_sqlite.config;


/**
 * Created by hammer on 22.02.2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper{

    public SQLiteDBHelper(Context context) {
        super(context, config.DB_NAME, null, config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(config.COMMAND_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(config.COMMAND_DELETE);
        this.onCreate(db);
    }
}
