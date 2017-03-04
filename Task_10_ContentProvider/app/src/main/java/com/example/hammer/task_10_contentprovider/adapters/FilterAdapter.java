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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hammer.task_10_contentprovider.R;
import com.example.hammer.task_10_contentprovider.activites.DetailsPersonsActivity;
import com.example.hammer.task_10_contentprovider.activites.MainActivity;
import com.example.hammer.task_10_contentprovider.db.DBContentProvider;
import com.example.hammer.task_10_contentprovider.db.PersonContract;
import com.example.hammer.task_10_contentprovider.model.Person;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16.01.2017.
 */

public class FilterAdapter extends ArrayAdapter<Person> implements Filterable {

    private List<Person> personsList;
    private Context mContext;
    private Filter personFilter;
    private List<Person> origPersonList;

    public FilterAdapter(Context context, ArrayList<Person> personsList) {
        super(context, R.layout.item_short_person, personsList);
        this.personsList = personsList;
        this.mContext = context;
        this.origPersonList = personsList;
    }

    @Override
    public int getCount() {
        return personsList.size();
    }

    @Override
    public Person getItem(int position) {
        return personsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personsList.get(position).hashCode();
    }

    private static class PersonHolder {
        public TextView tvNamePerson;
        public TextView tvPhoneNumberPerson;
        public ImageButton ibEditPerson;
    }

    public void resetData() {
        personsList = origPersonList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        PersonHolder holder = new PersonHolder();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_short_person, null);

            TextView tvName = (TextView) view.findViewById(R.id.text_view_item_short_name);
            TextView tvPhoneNumber = (TextView) view.findViewById(R.id.text_view_item_short_phone_number);
            ImageButton ibEdit = (ImageButton) view.findViewById(R.id.btn_short_edit);

            holder.tvNamePerson = tvName;
            holder.tvPhoneNumberPerson = tvPhoneNumber;
            holder.ibEditPerson = ibEdit;

            view.setTag(holder);
        } else
            holder = (PersonHolder) view.getTag();
        final Person person = personsList.get(position);

        holder.tvNamePerson.setText(person.getmName());
        holder.tvNamePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person.selectedPerson = person;
                Intent intent = new Intent(mContext, DetailsPersonsActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.tvPhoneNumberPerson.setText(person.getmPhoneNumber());
        holder.tvPhoneNumberPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + person.getmPhoneNumber()));
                v.getContext().startActivity(intent);
            }
        });
        holder.ibEditPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditDialog(person);
            }
        });
        return view;
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
    public void changePersonsList(ArrayList<Person> newPersonsList, Person person) {
        this.personsList = newPersonsList;
        newPersonsList.add(person);
        notifyDataSetChanged();
    }
    public  void dropListPersons(){
        if (!personsList.isEmpty()) {
            personsList.clear();
            mContext.getContentResolver().delete(DBContentProvider.PERSONS_CONTENT_URI, null, null);
        }
        notifyDataSetChanged();
    }
    private void openDeleteDialog(final Person personItem) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

        alertDialogBuilder.setMessage("Delete contact " + personItem.getmName() + " " + personItem.getmSurname() + "?");

        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!personsList.isEmpty()) {
                    personsList.remove(personItem);
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

    @Override
    public Filter getFilter() {
        if (personFilter == null)
            personFilter = new PersonFilter();

        return personFilter;
    }


    private class PersonFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = origPersonList;
                results.count = origPersonList.size();
            } else {
                List<Person> newPersonList = new ArrayList<Person>();

                for (Person p : personsList) {
                    if (p.getmName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        newPersonList.add(p);
                }

                results.values = newPersonList;
                results.count = newPersonList.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                personsList = (List<Person>) results.values;
                notifyDataSetChanged();
            }

        }

    }
}
