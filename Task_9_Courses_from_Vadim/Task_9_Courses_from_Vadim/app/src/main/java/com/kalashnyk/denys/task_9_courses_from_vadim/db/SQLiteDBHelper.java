package com.kalashnyk.denys.task_9_courses_from_vadim.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kalashnyk.denys.task_9_courses_from_vadim.Config;

/**
 * Created by User on 05.01.2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {

    public SQLiteDBHelper(Context context) {
        super(context, Config.DB_NAME, null, Config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Config.COMMAND_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Config.COMMAND_DELETE);
        this.onCreate(sqLiteDatabase);
    }
}
