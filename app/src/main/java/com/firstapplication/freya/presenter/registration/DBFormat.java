package com.firstapplication.freya.presenter.registration;


public class DBFormat {
    private String fID;
    private String email;
    private String number;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String date;
    private int gender;

    public DBFormat() {

    }

    public DBFormat(String email, String number, String password, String surname, String patronymic, String name, String date, int gender) {
        this.email = email;
        this.number = number;
        this.password = password;
        this.surname = surname;
        this.patronymic = patronymic;
        this.name = name;
        this.date = date;
        this.gender = gender;
        fID = number;
    }

    public String getDate() {
        return date;
    }

    public int getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getFID() {
        return fID;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

}
