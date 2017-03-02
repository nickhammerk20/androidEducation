package com.example.hammer.task_10_sqlite_asynctask_loader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hammer.task_10_sqlite_asynctask_loader.Config;
import com.example.hammer.task_10_sqlite_asynctask_loader.model.Person;

import java.util.ArrayList;

/**
 * Created by hammer on 02.03.2017.
 */

public class CRUDSQLite {
    SQLiteDBHelper sqLiteDBHelper;

    public CRUDSQLite (Context context)
    {
        this.sqLiteDBHelper = new SQLiteDBHelper(context);
    }

    public ArrayList<Person> getAllPerson()
    {
        ArrayList<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Config.COMMAND_SELECT, null);
        Person person;
        if(cursor.moveToFirst())
        {
            do{
                person = new Person();
                person.setmId(Integer.parseInt(cursor.getString(0)));
                person.setmName(cursor.getString(1));
                person.setmSurname(cursor.getString(2));
                person.setmPhone(cursor.getString(3));
                person.setmMail(cursor.getString(4));
                person.setmSkype(cursor.getString(5));
                persons.add(person);
            }while (cursor.moveToNext());
        }
        return persons;
    }

    public ArrayList<Person> getPerson(int id)
    {
        ArrayList<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        Cursor cursor = db.query(Config.TABLE_PERSON,   // имя таблицы
                null,                                   // имя колонки
                Config.KEY_ID + " = " + id,             // отбор по ИДишнику
                null,                                   //
                null,                                   // группировки
                null,                                   //
                null,                                   //
                null);                                  // ограничения
        if (cursor != null )
            cursor.moveToNext();

        Person person = new Person();
        person.setmId(Integer.parseInt(cursor.getString(0)));
        person.setmName(cursor.getString(1));
        person.setmSurname(cursor.getString(2));
        person.setmPhone(cursor.getString(3));
        person.setmMail(cursor.getString(4));
        person.setmSkype(cursor.getString(5));
        persons.add(person);
        return persons;
    }

    public void addPerson(Person person)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Config.KEY_NAME, person.getmName());
        values.put(Config.KEY_SURNAME, person.getmSurname());
        values.put(Config.KEY_PHONE, person.getmPhone());
        values.put(Config.KEY_MAIL, person.getmMail());
        values.put(Config.KEY_SKYPE, person.getmSkype());
        db.insert(Config.TABLE_PERSON, null, values);
        db.close();
    }

    public void deleteAllPerson()
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        db.delete(Config.TABLE_PERSON, null, null);
        db.close();
    }
    public void deletePerson(int id)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        db.delete(Config.TABLE_PERSON, Config.KEY_ID + " = " + id, null);
        db.close();
    }

    public void updatePerson(int id, String name, String surename, String phone, String skype, String mail)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Config.KEY_NAME, name);
        values.put(Config.KEY_SURNAME, surename);
        values.put(Config.KEY_PHONE, phone);
        values.put(Config.KEY_SKYPE, skype);
        values.put(Config.KEY_MAIL, mail);

        db.update(Config.TABLE_PERSON, values, Config.KEY_ID + " = " + id, null);
        db.close();
    }
}
