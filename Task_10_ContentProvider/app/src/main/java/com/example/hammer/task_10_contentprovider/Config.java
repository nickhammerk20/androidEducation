package com.example.hammer.task_10_contentprovider;

import android.net.Uri;

import com.example.hammer.task_10_contentprovider.db.PersonContract;

/**
 * Created by User on 22.01.2017.
 */

public class Config {

    public static Uri PERSONS_URI = Uri.parse("content://com.kalashnyk.denys.testcontentprovider/");

    public static final String DB_NAME = "Person Data Base";

    public static final String TABLE_PERSON = "person";

    public static final String COMMAND_CREATE = "create table IF NOT EXISTS "
        + TABLE_PERSON + " ("
        + PersonContract.KEY_ID + " INTEGER PRIMARY KEY, "
        + PersonContract.KEY_NAME + " TEXT, "
        + PersonContract.KEY_SURNAME + " TEXT, "
        + PersonContract.KEY_PHONE + " TEXT, "
        + PersonContract.KEY_MAIL + " TEXT, "
        + PersonContract.KEY_SKYPE + " TEXT"
        +");";

    public static final String COMMAND_DELETE = "DROP TABLE IF EXISTS " + TABLE_PERSON;
    public static final int DB_VERSION = 2201017;
}
