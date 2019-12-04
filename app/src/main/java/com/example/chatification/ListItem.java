package com.example.chatification;

public class ListItem {

    private String title;
    private String endDate;

    public ListItem (String title, String endDate) {
        this.title = title;
        this.endDate = endDate;
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
}
