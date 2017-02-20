package com.kalashnyk.denys.task_9_courses_from_vadim.ui.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kalashnyk.denys.task_9_courses_from_vadim.R;
import com.kalashnyk.denys.task_9_courses_from_vadim.model.Person;

/**
 * Created by User on 17.01.2017.
 */

public class DetailsPersonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_details);

        TextView tvNameDetailsPerson = (TextView) findViewById(R.id.text_view_details_name);
        TextView tvSurnameDetailsPerson = (TextView) findViewById(R.id.text_view_details_surname);
        TextView tvPhoneDetailsNumberPerson = (TextView) findViewById(R.id.text_view_details_phone_number);
        TextView tvMailDetailsPerson = (TextView) findViewById(R.id.text_view_details_mail);
        TextView tvSkypeDetailsPerson = (TextView) findViewById(R.id.text_view_details_skype);
        if (Person.selectedPerson != null) {
            Person itemPerson = Person.selectedPerson;
            tvNameDetailsPerson.setText(itemPerson.getmName());
            tvSurnameDetailsPerson.setText(itemPerson.getmSurname());
            tvPhoneDetailsNumberPerson.setText(itemPerson.getmPhoneNumber());
            tvMailDetailsPerson.setText(itemPerson.getmMail());
            tvSkypeDetailsPerson.setText(itemPerson.getmSkype());
        }
    }
}
