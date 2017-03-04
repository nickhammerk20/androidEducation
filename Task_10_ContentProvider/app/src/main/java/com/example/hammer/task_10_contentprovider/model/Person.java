package com.example.hammer.task_10_contentprovider.model;

import java.io.Serializable;

/**
 * Created by User on 16.12.2016.
 */

public class Person implements Serializable{

    private int id;
    private String mName;
    private String mSurname;
    private String mPhoneNumber;
    private String mMail;
    private String mSkype;

    public Person() {super();}

    public static Person selectedPerson;

    public Person(int id, String mName, String mSurname, String mPhoneNumber, String mMail, String mSkype) {
        this.id = id;
        this.mName = mName;
        this.mSurname = mSurname;
        this.mPhoneNumber = mPhoneNumber;
        this.mMail = mMail;
        this.mSkype = mSkype;
    }
    public Person(String mName, String mSurname, String mPhoneNumber, String mMail, String mSkype) {
        this.mName = mName;
        this.mSurname = mSurname;
        this.mPhoneNumber = mPhoneNumber;
        this.mMail = mMail;
        this.mSkype = mSkype;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", mMail='" + mMail + '\'' +
                ", mSkype='" + mSkype + '\'' +
                '}';
    }
}