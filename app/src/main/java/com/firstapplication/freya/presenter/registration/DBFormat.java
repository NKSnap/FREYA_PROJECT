package com.firstapplication.freya.presenter.registration;

public class DBFormat {
    private String fID;
    private String email;
    private String number;
    private String password;
    private String surname;
    private String patronymic;

    public DBFormat(String email, String number, String password, String surname, String patronymic) {
        this.email = email;
        this.number = number;
        this.password = password;
        this.surname = surname;
        this.patronymic = patronymic;
        fID = email;
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
