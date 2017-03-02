package com.example.hammer.task_10_sqlite_asynctask_loader.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hammer on 02.03.2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper
{
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
