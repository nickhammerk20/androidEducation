package com.example.hammer.task_10_sqlite_asynctask_loader.model;

/**
 * Created by hammer on 02.03.2017.
 */

public class Person {

    public static Person selectedPerson;
    private int mId;
    private String mName;
    private String mSurname;
    private String mPhone;
    private String mSkype;
    private String mMail;

    public Person ( int mId, String mName, String mSurname, String mPhone, String mSkype, String mMail)
    {
        this.mId = mId;
        this.mName = mName;
        this.mSurname = mSurname;
        this.mPhone = mPhone;
        this.mSkype = mSkype;
        this.mMail = mMail;
    }
    public Person (String mName, String mSurname, String mPhone, String mSkype, String mMail)
    {
        this.mName = mName;
        this.mSurname = mSurname;
        this.mPhone = mPhone;
        this.mSkype = mSkype;
        this.mMail = mMail;
    }
    public Person()
    {
        super();
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

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmSkype() {
        return mSkype;
    }

    public void setmSkype(String mSkype) {
        this.mSkype = mSkype;
    }

    public String getmMail() {
        return mMail;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSurename='" + mSurname + '\'' +
                ", mPhoneNumber='" + mPhone + '\'' +
                ", mMail='" + mMail + '\'' +
                ", mSkype='" + mSkype + '\'' +
                '}';
    }
}
