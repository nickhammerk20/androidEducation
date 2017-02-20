package com.example.hammer.task_4_crudsharedpreferences;

import java.io.Serializable;

/**
 * Created by hammer on 08.02.2017.
 */

public class Person implements Serializable
{
    private int mId;
    private String mName;
    private String mSurename;
    private String mPhoneNumber;
    private String mMail;
    private String mSkype;


    public Person(int mId, String mName, String mSurename, String mPhoneNumber, String mMail, String mSkype) {
        this.mId = mId;
        this.mName = mName;
        this.mSurename = mSurename;
        this.mPhoneNumber = mPhoneNumber;
        this.mMail = mMail;
        this.mSkype = mSkype;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurename() {
        return mSurename;
    }

    public void setmSurename(String mSurename) {
        this.mSurename = mSurename;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmMail() {
        return mMail;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }

    public String getmSkype() {
        return mSkype;
    }

    public void setmSkype(String mSkype) {
        this.mSkype = mSkype;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurename='" + mSurename + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", mMail='" + mMail + '\'' +
                ", mSkype='" + mSkype + '\'' +
                '}';
    }


}
