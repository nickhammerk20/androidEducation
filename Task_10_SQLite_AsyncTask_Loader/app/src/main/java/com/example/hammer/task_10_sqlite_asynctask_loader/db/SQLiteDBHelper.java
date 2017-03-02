package com.example.hammer.task_10_sqlite_asynctask_loader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hammer.task_10_sqlite_asynctask_loader.Config;

/**
 * Created by hammer on 02.03.2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper
{
    public SQLiteDBHelper(Context context) {
        super(context, Config.DB_NAME, null, Config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Config.COMMAND_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Config.COMMAND_DELETE);
        this.onCreate(db);
    }
}
