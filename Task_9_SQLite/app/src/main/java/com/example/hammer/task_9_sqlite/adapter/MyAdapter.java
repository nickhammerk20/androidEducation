package com.example.hammer.task_9_sqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hammer.task_9_sqlite.ui.DetailsPersonsActivity;
import com.example.hammer.task_9_sqlite.model.Person;
import com.example.hammer.task_9_sqlite.R;
import com.example.hammer.task_9_sqlite.ui.SecondActivity;
import com.example.hammer.task_9_sqlite.database.CRUDSQLite;

import java.util.ArrayList;

/**
 * Created by hammer on 24.02.2017.
 */

public class MyAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ArrayList<Person> persons;

    private CRUDSQLite crudsqLite;

    public MyAdapter(Context context, ArrayList<Person> persons)
    {
        this.mContext = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.persons = persons;
    }

    public MyAdapter(SecondActivity secondActivity, ArrayList<Person> persons) {
    }

    public void changePersonsList(ArrayList<Person> person) {
    }

    public void dropListPersons() {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            view = layoutInflater.inflate(R.layout.item_person, parent, false);
        }
        final Person person = persons.get(position);
        TextView tvNamePerson = (TextView) view.findViewById(R.id.text_view_item_name);
        TextView tvPhoneNumberPerson = (TextView) view.findViewById(R.id.text_view_item_phone_number);
        ImageButton ibShortEdit = (ImageButton) view.findViewById(R.id.btn_item_edit);
        tvNamePerson.setText(person.getmName());
        tvNamePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person.selectedPerson = person;
                Intent intent = new Intent(mContext, DetailsPersonsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        tvPhoneNumberPerson.setText(person.getmPhoneNumber());
        tvPhoneNumberPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + person.getmPhoneNumber()));
                //view.getContext().startActivity(intent);
            }
        });
        ibShortEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditDialog(person);
            }
        });
        return view;
    }

    private void openEditDialog(Person person) {
    }
}
