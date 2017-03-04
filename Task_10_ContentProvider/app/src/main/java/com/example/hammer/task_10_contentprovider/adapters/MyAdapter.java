package com.example.hammer.task_10_contentprovider.adapters;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hammer.task_10_contentprovider.R;
import com.example.hammer.task_10_contentprovider.activites.DetailsPersonsActivity;
import com.example.hammer.task_10_contentprovider.activites.MainActivity;
import com.example.hammer.task_10_contentprovider.db.DBContentProvider;
import com.example.hammer.task_10_contentprovider.db.PersonContract;
import com.example.hammer.task_10_contentprovider.model.Person;

import java.util.ArrayList;

/**
 * Created by User on 30.12.2016.
 */

public class MyAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater layoutInflater;
    public ArrayList<Person> persons;


    public MyAdapter(Context context, ArrayList<Person> persons) {
        this.mContext = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        this.persons = persons;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
                view.getContext().startActivity(intent);
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
    @Override
    public int getCount() {
        return  persons.size();
    }
    @Override
    public long getItemId(int position) {
        return persons.get(position).hashCode();
    }
    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    private void openEditDialog(final Person personItem) {
        LayoutInflater dialogInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View root = dialogInflater.inflate(R.layout.dialog_edit, null);

        final EditText etDialogId = (EditText) root.findViewById(R.id.edit_text_dialog_id);
        final EditText etDialogName = (EditText) root.findViewById(R.id.edit_text_dialog_name);
        final EditText etDialogSurname = (EditText) root.findViewById(R.id.edit_text_dialog_surname);
        final EditText etDialogPhoneNumber = (EditText) root.findViewById(R.id.edit_text_dialog_phone_number);
        final EditText etDialogMail = (EditText) root.findViewById(R.id.edit_text_dialog_mail);
        final EditText etDialogSkype = (EditText) root.findViewById(R.id.edit_text_dialog_skype);

        etDialogId.setText(String.valueOf(personItem.getId()));
        etDialogName.setText(personItem.getmName());
        etDialogSurname.setText(personItem.getmSurname());
        etDialogPhoneNumber.setText(personItem.getmPhoneNumber());
        etDialogMail.setText(personItem.getmMail());
        etDialogSkype.setText(personItem.getmSkype());

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(root);
        alertDialogBuilder.setMessage("Edit Contact");

        alertDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ContentValues mUpdateValues = new ContentValues();
                mUpdateValues.put(PersonContract.KEY_NAME, etDialogName.getText().toString());
                mUpdateValues.put(PersonContract.KEY_SURNAME, etDialogSurname.getText().toString());
                mUpdateValues.put(PersonContract.KEY_PHONE, etDialogPhoneNumber.getText().toString());
                mUpdateValues.put(PersonContract.KEY_MAIL, etDialogMail.getText().toString());
                mUpdateValues.put(PersonContract.KEY_SKYPE, etDialogSkype.getText().toString());
                mContext.getContentResolver().update(DBContentProvider.PERSONS_CONTENT_URI.buildUpon().
                        appendPath(String.valueOf(personItem.getId())).build(),  mUpdateValues, null, null);
                mContext.getContentResolver().notifyChange(DBContentProvider.PERSONS_CONTENT_URI, null);
                notifyDataSetChanged();
            }
        });
        alertDialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openDeleteDialog(personItem);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }
    private void openDeleteDialog(final Person personItem) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

        alertDialogBuilder.setMessage("Delete contact " + personItem.getmName() + " " + personItem.getmSurname() + "?");

        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!persons.isEmpty()) {
                    persons.remove(personItem);
                    mContext.getContentResolver().delete(DBContentProvider.PERSONS_CONTENT_URI.buildUpon().
                            appendPath(String.valueOf(personItem.getId())).build(), null, null);
                }
                notifyDataSetChanged();
                sendNotification(personItem);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    private void sendNotification(final Person personItem) {
        final int NOTIFY_ID = 101;
        Context context = mContext.getApplicationContext();
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_delete_forever_black_24dp)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Deleted Contact " + personItem.getmSurname()) //+ personItem.getmName()+ " "
                .setContentText("Follow to Main Activity")
                .setAutoCancel(false);
        Notification notification = builder.getNotification();
        nm.notify(NOTIFY_ID, notification);
    }

    public void changePersonsList(ArrayList<Person> personsList, Person person) {
        this.persons = personsList;
        personsList.add(person);
        notifyDataSetChanged();
    }
    public  void dropListPersons(){
        if (!persons.isEmpty()) {
            persons.clear();
            mContext.getContentResolver().delete(DBContentProvider.PERSONS_CONTENT_URI, null, null);
        }
        notifyDataSetChanged();
    }
}
































//        View view = convertView;
//        if(view == null)
//        {
//            view = layoutInflater.inflate(R.layout.item_person, parent, false);
//        }
//        final Person person = persons.get(position);
//
//        TextView tvNamePerson = (TextView) view.findViewById(R.id.text_view_item_name);
//        TextView tvSurnamePerson = (TextView) view.findViewById(R.id.text_view_item_surname);
//        TextView tvPhoneNumberPerson = (TextView) view.findViewById(R.id.text_view_item_phone_number);
//        TextView tvMailPerson = (TextView) view.findViewById(R.id.text_view_item_mail);
//        TextView tvSkypePerson = (TextView) view.findViewById(R.id.text_view_item_skype);
//        tvNamePerson.setText(person.getmName());
//        tvSurnamePerson.setText(person.getmSurname());
//        tvPhoneNumberPerson.setText(person.getmPhoneNumber());
//        tvPhoneNumberPerson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
//        tvMailPerson.setText(person.getmMail());
//        tvMailPerson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        tvSkypePerson.setText(person.getmSkype());
//        tvSkypePerson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });



