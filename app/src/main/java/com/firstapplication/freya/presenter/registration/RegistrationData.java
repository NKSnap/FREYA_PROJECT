package com.firstapplication.freya.presenter.registration;

public class RegistrationData {
    private String surname;
    private String name;
    private String patronymic;
    private int gender;
    private String date;

    public RegistrationData(String surname, String name, String patronymic, int gender, String date) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.date = date;
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
