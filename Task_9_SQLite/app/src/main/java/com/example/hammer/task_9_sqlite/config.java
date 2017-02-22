package com.example.hammer.task_9_sqlite;

/**
 * Created by hammer on 22.02.2017.
 */

public class config {
    public static final String DB_NAME = "Person Data Base";

    public static final String TABLE_PERSON = "Person";

    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_SURENAME = "Surename";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_MAIL = "Mail";
    public static final String KEY_SKYPE = "Skype";

    public static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_SURENAME, KEY_PHONE, KEY_MAIL, KEY_SKYPE};

    public static final String COMMAND_CREATE = "create table"
            + TABLE_PERSON + " ("
            + KEY_ID + "INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT "
            + KEY_SURENAME + " TEXT "
            + KEY_PHONE + " TEXT "
            + KEY_MAIL + " TEXT "
            + KEY_SKYPE + " TEXT "
            + ");";

    public static final String COMMAND_DELETE = "DROP TABLE IF EXIST " + TABLE_PERSON;
    public static final String COMMAND_SELECT = "SELECT * FROM " + TABLE_PERSON;
    public static final int DB_VERSION = 210217;
}
