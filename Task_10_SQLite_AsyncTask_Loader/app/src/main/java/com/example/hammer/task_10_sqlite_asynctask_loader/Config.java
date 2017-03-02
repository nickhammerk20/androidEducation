package com.example.hammer.task_10_sqlite_asynctask_loader;

/**
 * Created by hammer on 02.03.2017.
 */

public class Config {
    public static final String DB_NAME = "Person Data Base";            // Имя базы

    public static final String TABLE_PERSON = "Person";                 // Имя таблицы

    //Перечисление столбцов, присвоение имен
    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_SURNAME = "Surname";
    public static final String KEY_PHONE = "Phone";
    public static final String KEY_MAIL = "Mail";
    public static final String KEY_SKYPE = "Skype";

    // массив столбцов
    public static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_SURNAME, KEY_PHONE, KEY_MAIL, KEY_SKYPE};

    // Формирование строки команды SQL для создания тьаблицы
    public static final String COMMAND_CREATE = "CREATE TABLE "
            + TABLE_PERSON + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT, "
            + KEY_SURNAME + " TEXT, "
            + KEY_PHONE + " TEXT, "
            + KEY_MAIL + " TEXT, "
            + KEY_SKYPE + " TEXT "
            + ");";

    // формирование строки команды  SQL для  удаления таблицы
    public static final String COMMAND_DELETE = "DROP TABLE IF EXIST " + TABLE_PERSON;

    // выбор таблицы
    public static final String COMMAND_SELECT = "SELECT * FROM " + TABLE_PERSON;

    // версия базы. Обязательная ИНТОВАЯ переменная
    public static final int DB_VERSION = 2032017;



}
