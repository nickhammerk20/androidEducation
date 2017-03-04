package com.example.hammer.task_13_picasso_list_search;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalashnyk.denys.task_4_courses_from_vadim.R;
import com.kalashnyk.denys.task_4_courses_from_vadim.database.CRUDSharedPreferences;
import com.kalashnyk.denys.task_4_courses_from_vadim.model.Person;

import java.util.ArrayList;

/**
 * Created by User on 14.12.2016.
 */

public class MyAdapter extends RecyclerView.Adapter<Product> {

    private static final int RESULT_OK = 0;
    private Context mContext;
    private ArrayList mList;

    public MyAdapter(Context mContext, ArrayList mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public Product onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        Product holder = new Product(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Product holder, final int position) {
        final Product person = (Product) mList.get(position);

        holder.cvPerson.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {}});

        holder.tvName.setText(person.getmName());
        holder.tvSurname.setText(person.getmSurname());
        holder.tvPhoneNumber.setText(person.getmPhoneNumber());
        holder.tvMail.setText(person.getmMail());
        holder.tvSkype.setText(person.getmSkype());

        holder.ibUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id", String.valueOf(person.getId()));
                intent.putExtra("name", person.getmName());
                intent.putExtra("surname", person.getmSurname());
                intent.putExtra("phoneNumber", person.getmPhoneNumber());
                intent.putExtra("mail", person.getmMail());
                intent.putExtra("skype", person.getmSkype());
                getActivityFromContext(view.getContext()).setResult(RESULT_OK, intent);
                getActivityFromContext(view.getContext()).finish();
            }
        });

        holder.ibCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.ibMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//                emailIntent.setData(Uri.parse("mailto:" + person.getmMail()));
//                view.getContext().startActivity(emailIntent);
            }
        });

        holder.ibCallSkype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent skypeIntent = new Intent("android.intent.action.VIEW");
//                skypeIntent.setData(Uri.parse("Skype :" +  holder.tvSkype.getText().toString()));
//                view.getContext().startActivity(skypeIntent);
            }
        });

        holder.tvPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+ person.getmPhoneNumber()));
//                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void dismissPerson(int position) {
        Product person = (Product) mList.get(position);
        if(!mList.isEmpty()) {
            mList.remove(position);
        }
        this.notifyItemRemoved(position);
    }

    private static AppCompatActivity getActivityFromContext(Context context) {
        if(context instanceof Activity) {
            return (AppCompatActivity) context;
        }
        if(context instanceof ContextWrapper &&
                ((ContextWrapper)context).getBaseContext() instanceof Activity) {
            return  (AppCompatActivity) ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
}
