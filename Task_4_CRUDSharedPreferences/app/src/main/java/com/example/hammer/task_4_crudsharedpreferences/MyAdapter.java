package com.example.hammer.task_4_crudsharedpreferences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hammer.task_4_crudsharedpreferences.database.CRUDSharedPreferences;

import java.util.ArrayList;

/**
 * Created by hammer on 08.02.2017.
 */

public class MyAdapter extends BaseAdapter
{
//    private static final int RESULT_OK = 0;
    private Context mContext;
    private ArrayList<Person> mList;
//    CRUDSharedPreferences crudSharedPreferences;

    public MyAdapter(Context mContext, ArrayList<Person> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rootView = view;
        if (rootView == null)
        {
            rootView = LayoutInflater.from(mContext).inflate(R.layout.item_person, viewGroup, false);
        }
//        TextView tvId = (TextView) rootView.findViewById(R.id.tv_item_person_id);
        TextView tvName = (TextView) rootView.findViewById(R.id.tv_item_person_name);
        TextView tvSurname = (TextView) rootView.findViewById(R.id.tv_item_person_surname);
        TextView tvPhone = (TextView) rootView.findViewById(R.id.tv_item_person_phone);
        TextView tvMail = (TextView) rootView.findViewById(R.id.tv_item_person_mail);
        TextView tvSkype = (TextView) rootView.findViewById(R.id.tv_item_person_skype);

        final Person person = mList.get(position);

        tvName.setText(person.getmName());
        tvSurname.setText(person.getmSurename());
        tvPhone.setText(person.getmPhoneNumber());
        tvMail.setText(person.getmMail());
        tvSkype.setText(person.getmSkype());

        return rootView;
    }
}
