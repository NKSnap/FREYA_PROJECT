package com.firstapplication.freya.presenter.account;

public class Record {
    private String haircut;
    private String dateAndTime;
    private int isActive;

    public Record(String haircut, String dateAndTime, int flag){
        this.haircut = haircut;
        this.dateAndTime = dateAndTime;
        this.isActive =flag;
    }

    public String getHaircut() {
        return this.haircut;
    }

    public void setHaircut(String haircut) {
        this.haircut = haircut;
    }

    public String getDateAndTime() {
        return this.dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
