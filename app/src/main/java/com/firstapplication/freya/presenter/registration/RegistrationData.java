package com.firstapplication.freya.presenter.registration;

public class RegistrationData {
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
}
