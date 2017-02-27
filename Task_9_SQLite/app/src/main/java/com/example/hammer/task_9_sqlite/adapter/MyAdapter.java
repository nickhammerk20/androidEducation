package com.example.hammer.task_9_sqlite.adapter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hammer.task_9_sqlite.ui.DetailsPersonsActivity;
import com.example.hammer.task_9_sqlite.model.Person;
import com.example.hammer.task_9_sqlite.R;
import com.example.hammer.task_9_sqlite.ui.MainActivity;
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

    public void changePersonsList(ArrayList<Person> personsList) {
        this.persons = personsList;
        notifyDataSetChanged();
    }

    public void dropListPersons() {
        crudsqLite = new CRUDSQLite(mContext);
        if (!persons.isEmpty()) {
            persons.clear();
            crudsqLite.deleteAllPerson();
        }
        notifyDataSetChanged();
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

    @Override  //почему position передается как final int??
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

    //почему final??
    private void openEditDialog(final Person personItem) {
        LayoutInflater dialogInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View root = dialogInflater.inflate(R.layout.dialog_edit, null);

        final EditText etDialogId = (EditText) root.findViewById(R.id.edit_text_dialog_id);
        final EditText etDialogName = (EditText) root.findViewById(R.id.edit_text_dialog_name);
        final EditText etDialogSurname = (EditText) root.findViewById(R.id.edit_text_dialog_surname);
        final EditText etDialogPhoneNumber = (EditText) root.findViewById(R.id.edit_text_dialog_phone_number);
        final EditText etDialogMail = (EditText) root.findViewById(R.id.edit_text_dialog_mail);
        final EditText etDialogSkype = (EditText) root.findViewById(R.id.edit_text_dialog_skype);

        etDialogId.setText(String.valueOf(personItem.getmId()));
        etDialogName.setText(personItem.getmName());
        etDialogSurname.setText(personItem.getmSurename());
        etDialogPhoneNumber.setText(personItem.getmPhoneNumber());
        etDialogMail.setText(personItem.getmMail());
        etDialogSkype.setText(personItem.getmSkype());

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(root);
        alertDialogBuilder.setMessage("Edit Contact");

        alertDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                crudsqLite = new CRUDSQLite(mContext);
//                personItem.setId(Integer.parseInt(etDialogId.getText().toString()));
//                personItem.setmName(etDialogName.getText().toString());
//                personItem.setmSurname(etDialogSurname.getText().toString());
//                personItem.setmPhoneNumber(etDialogPhoneNumber.getText().toString());
//                personItem.setmMail(etDialogMail.getText().toString());
//                personItem.setmSkype(etDialogSkype.getText().toString());
                crudsqLite.updatePerson(personItem.getmId(), etDialogName.getText().toString(),
                        etDialogSurname.getText().toString(),etDialogPhoneNumber.getText().toString(),
                        etDialogMail.getText().toString(),etDialogSkype.getText().toString());
//                notifyDataSetChanged();
            }
        });
        alertDialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openDeleteDialog(personItem);
                sendNotification(personItem);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        notifyDataSetChanged();
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    private void openDeleteDialog(final Person personItem) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

        alertDialogBuilder.setMessage("Delete contact " + personItem.getmName() + " " + personItem.getmSurename() + "?");

        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                crudsqLite = new CRUDSQLite(mContext);
                if (!persons.isEmpty()) {
                    persons.remove(personItem);
                    crudsqLite.deletePerson(personItem.getmId());
                }
                notifyDataSetChanged();
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
                .setSmallIcon(R.mipmap.ic_delete_forever_black_18dp)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Deleted Contact " + personItem.getmSurename()) //+ personItem.getmName()+ " "
                .setContentText("Follow to Main Activity")
                .setAutoCancel(false);
        Notification notification = builder.getNotification();
        nm.notify(NOTIFY_ID, notification);
    }
}
