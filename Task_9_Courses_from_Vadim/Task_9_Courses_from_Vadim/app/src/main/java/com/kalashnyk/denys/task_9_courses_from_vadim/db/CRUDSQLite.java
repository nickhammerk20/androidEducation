package com.kalashnyk.denys.task_9_courses_from_vadim.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kalashnyk.denys.task_9_courses_from_vadim.Config;
import com.kalashnyk.denys.task_9_courses_from_vadim.model.Person;

import java.util.ArrayList;

/**
 * Created by User on 05.01.2017.
 */

public class CRUDSQLite {

    SQLiteDBHelper sqLiteDBHelper;

    public CRUDSQLite (Context context)
    {
        this.sqLiteDBHelper = new SQLiteDBHelper(context);
    }

    public ArrayList<Person> getAllPersons()
    {
        ArrayList<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Config.COMMAND_SELECT, null);//
        Person person;
        if (cursor.moveToFirst())
        {
            do{
                person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setmName(cursor.getString(1));
                person.setmSurname(cursor.getString(2));
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
        SQLiteDatabase db = sqLiteDBHelper.getReadableDatabase();
        Cursor cursor =
                db.query(Config.TABLE_PERSON, // table
                        null,  // column names
                        Config.KEY_ID + " = "+ id, // selections
                        null,  // selections args
                        null,  // group by
                        null,  // having
                        null,  // order by
                        null); // limit
        if (cursor != null)
            cursor.moveToFirst();

        Person person = new Person();
        person.setId(Integer.parseInt(cursor.getString(0)));
        person.setmName(cursor.getString(1));
        person.setmSurname(cursor.getString(2));
        person.setmPhoneNumber(cursor.getString(3));
        person.setmMail(cursor.getString(4));
        person.setmSkype(cursor.getString(5));
        persons.add(person);
        db.close();
        return persons ;
    }

    public  void addPerson(Person person)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(Config.KEY_ID, person.getId());
        values.put(Config.KEY_NAME, person.getmName());
        values.put(Config.KEY_SURNAME, person.getmSurname());
        values.put(Config.KEY_PHONE, person.getmPhoneNumber());
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
        db.delete(Config.TABLE_PERSON, Config.KEY_ID + " = "+ id, null);
        db.close();
    }
    public void updatePerson(int id, String name, String surname, String phone, String mail, String skype)
    {
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("Name", name);
//        values.put("Surname", surname);
//        values.put("Phone", phone);
//        values.put("Mail", mail);
//        values.put("Skype", skype);

        values.put(Config.KEY_NAME, name);
        values.put(Config.KEY_SURNAME, surname);
        values.put(Config.KEY_PHONE, phone);
        values.put(Config.KEY_MAIL, mail);
        values.put(Config.KEY_SKYPE, skype);
        db.update(Config.TABLE_PERSON, values, Config.KEY_ID + " = "+ id,null);
        db.close();
    }
}
