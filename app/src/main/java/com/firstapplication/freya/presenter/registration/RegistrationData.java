package com.firstapplication.freya.presenter.registration;

import android.util.Log;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RegistrationData implements Serializable {
    private static final String TAG_ERROR = "ERROR";
    private String surname;
    private String name;
    private String patronymic;
    private int gender;
    private String date;
    private String email;
    private String password;
    private String number;

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RegistrationData() {

    }

    public RegistrationData(String surname, String name, String patronymic, int gender, String date) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEncryptPassword(String password) {
        this.password = toEncrypt(password);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getGender() {
        return gender;
    }

    public String getDate() {
        return date;
    }

    public static String toEncrypt(String st) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG_ERROR, "????????????????????.\n\t???????????????????????? ???????????????? ?? getInstance(...) ???? ????????????????????");
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32)
            md5Hex.insert(0, "0");

        return md5Hex.toString();
    }
}
