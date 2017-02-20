package com.kalashnyk.denys.task_4_courses_from_vadim.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kalashnyk.denys.task_4_courses_from_vadim.R;

/**
 * Created by User on 14.12.2016.
 */

public class ViewHolderPerson extends RecyclerView.ViewHolder {

    public CardView cvPerson;
    public TextView tvName;
    public TextView tvSurname;
    public TextView tvPhoneNumber;
    public TextView tvMail;
    public TextView tvSkype;
    public ImageButton ibUpdate;
    public ImageButton ibCallPhone;
    public ImageButton ibMail;
    public ImageButton ibCallSkype;

    public ViewHolderPerson(View itemView) {
        super(itemView);
        this.cvPerson = (CardView) itemView.findViewById(R.id.card_view_person);
        this.tvName = (TextView) itemView.findViewById(R.id.text_view_first_name);
        this.tvSurname = (TextView) itemView.findViewById(R.id.text_view_last_name);
        this.tvPhoneNumber = (TextView) itemView.findViewById(R.id.text_view_phone_number);
        this.tvMail = (TextView) itemView.findViewById(R.id.text_view_mail);
        this.tvSkype = (TextView) itemView.findViewById(R.id.text_view_skype);
        this.ibUpdate = (ImageButton) itemView.findViewById(R.id.image_button_update);
        this.ibCallPhone = (ImageButton) itemView.findViewById(R.id.image_button_call);
        this.ibMail = (ImageButton) itemView.findViewById(R.id.image_button_mail);
        this.ibCallSkype = (ImageButton) itemView.findViewById(R.id.image_button_skype);
    }
}
