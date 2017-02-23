package com.example.hammer.task_9_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hammer.task_9_sqlite.Person;
import com.example.hammer.task_9_sqlite.config;

import java.util.ArrayList;

/**
 * Created by hammer on 22.02.2017.
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
        Cursor cursor = db.rawQuery(config.COMMAND_SELECT, null);
        Person person;
        if(cursor.moveToFirst())
        {
            do{
                person = new Person();
                person.setmId(Integer.parseInt(cursor.getString(0)));
                person.setmName(cursor.getString(1));
                person.setmSurename(cursor.getString(2));
                person.setmPhoneNumber(cursor.getString(3));
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
        Cursor cursor = db.query(config.TABLE_PERSON,   // имя таблицы
                null,                                   // имя колонки
                config.KEY_ID + " = " + id,             // отбор по ИДишнику
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
        person.setmSurename(cursor.getString(2));
        person.setmPhoneNumber(cursor.getString(3));
        person.setmMail(cursor.getString(4));
        person.setmSkype(cursor.getString(5));
        persons.add(person);
        return persons;
    }

    public void addPerson(Person person)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(config.KEY_NAME, person.getmName());
        values.put(config.KEY_SURENAME, person.getmSurename());
        values.put(config.KEY_PHONE, person.getmPhoneNumber());
        values.put(config.KEY_SKYPE, person.getmSkype());
        values.put(config.KEY_MAIL, person.getmMail());
        db.insert(config.TABLE_PERSON, null, values);
        db.close();
    }

    public void deleteAllPerson()
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        db.delete(config.TABLE_PERSON, null, null);
        db.close();
    }
    public void deletePerson(int id)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        db.delete(config.TABLE_PERSON, config.KEY_ID + " = " + id, null);
        db.close();
    }

    public void updatePerson(int id, String name, String surename, String phone, String skype, String mail)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(config.KEY_NAME, name);
        values.put(config.KEY_SURENAME, surename);
        values.put(config.KEY_PHONE, phone);
        values.put(config.KEY_SKYPE, skype);
        values.put(config.KEY_MAIL, mail);

        db.update(config.TABLE_PERSON, values, config.KEY_ID + " = " + id, null);
        db.close();
    }
}
