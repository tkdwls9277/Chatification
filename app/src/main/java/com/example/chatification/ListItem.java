package com.example.chatification;

public class ListItem {

    private String title;
    private String endDate;
    private boolean status;

    public ListItem (String title, String endDate, boolean status) {
        this.title = title;
        this.endDate = endDate;
        this.status = status;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
