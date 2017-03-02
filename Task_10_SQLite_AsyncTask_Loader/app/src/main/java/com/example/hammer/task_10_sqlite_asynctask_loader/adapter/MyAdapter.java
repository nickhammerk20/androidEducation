package com.example.hammer.task_10_sqlite_asynctask_loader.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hammer.task_10_sqlite_asynctask_loader.R;
import com.example.hammer.task_10_sqlite_asynctask_loader.db.CRUDSQLite;
import com.example.hammer.task_10_sqlite_asynctask_loader.model.Person;
import com.example.hammer.task_10_sqlite_asynctask_loader.ui.PersonDetailActivity;

import java.util.ArrayList;

/**
 * Created by hammer on 02.03.2017.
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ArrayList<Person> persons;
    private CRUDSQLite crudsqLite;

    public MyAdapter(Context context, ArrayList<Person> persons){
        this.mContext = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.persons = persons;
    }



    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return persons.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = layoutInflater.inflate(R.layout.item_person, parent, false);

        final Person person = persons.get(position);
        TextView tvNamePerson = (TextView) view.findViewById(R.id.text_view_item_name);
        TextView tvPhonePerson = (TextView) view.findViewById(R.id.text_view_item_phone);
        ImageButton idShortEdit = (ImageButton) view.findViewById(R.id.btn_item_edit);
        tvNamePerson.setText(person.getmName());
        tvNamePerson.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Person.selectedPerson = person;
                Intent intent = new Intent(mContext, PersonDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
        tvPhonePerson.setText(person.getmPhone());
        idShortEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Person.selectedPerson = person;  // добавить в Person.java переменную
                Intent intent = new Intent(mContext, PersonDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

}
