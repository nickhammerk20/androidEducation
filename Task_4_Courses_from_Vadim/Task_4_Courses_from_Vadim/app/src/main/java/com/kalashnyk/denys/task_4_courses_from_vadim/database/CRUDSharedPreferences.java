package com.kalashnyk.denys.task_4_courses_from_vadim.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.kalashnyk.denys.task_4_courses_from_vadim.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 22.12.2016.
 */

public class CRUDSharedPreferences {

    public static final String PREFS_NAME = "CRUD_APP";
    public static final String PERSON_CONSTANT = "Person Constant";

    public CRUDSharedPreferences() {
        super();
    }

    public void savePerson(Context context, List<Person> persons) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        preferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = preferences.edit();

        Gson gson = new Gson();
        String jsonPerson = gson.toJson(persons);

        editor.putString(PERSON_CONSTANT, jsonPerson);

        editor.commit();
    }

    public void addPerson(Context context, Person person) {
        List<Person> personList = getPersons(context);
        if (personList == null) {
            personList = new ArrayList<Person>();
        }
        personList.add(person);
        savePerson(context, personList);
    }

    public void removePerson(Context context, Person person) {
        ArrayList<Person> personList = getPersons(context);
        if (personList != null) {
            personList.remove(person);
            savePerson(context, personList);
        }
    }

    public ArrayList<Person> getPersons(Context context) {
        SharedPreferences preferences;
        List<Person> personList;

        preferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (preferences.contains(PERSON_CONSTANT)) {
            String jsonPerson = preferences.getString(PERSON_CONSTANT, null);
            Gson gson = new Gson();
            Person[] personsItems = gson.fromJson(jsonPerson,
                    Person[].class);

            personList = Arrays.asList(personsItems);
            personList = new ArrayList<Person>(personList);
        } else
            return null;

        return (ArrayList<Person>) personList;
    }
}
