package com.example.hammer.task_9_sqlite.database;

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
}
