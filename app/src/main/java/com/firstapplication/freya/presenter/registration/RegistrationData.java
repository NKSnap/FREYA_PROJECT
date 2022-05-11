package com.firstapplication.freya.presenter.registration;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationData {
    private static final String TAG_ERROR = "ERROR";
    private String surname;
    private String name;
    private String patronymic;
    private int gender;
    private String date;
    private String email;
    private String password;
    private String number;

    public RegistrationData(String surname, String name, String patronymic, int gender, String date) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.date = date;
    }

    public FirebaseFormat toFirebaseFormat() {
        return new FirebaseFormat(this.email, this.number, this.password, this.surname, this.patronymic);
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
            // Передаваемый алгоритм в getInstance(...) не существует
            Log.d(TAG_ERROR, "Шифрование.\n\tПередаваемый алгоритм в getInstance(...) не существует");
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while (md5Hex.length() < 32)
            md5Hex.insert(0, "0");

        Log.d(TAG_ERROR, md5Hex.toString());
        return md5Hex.toString();
    }
}
