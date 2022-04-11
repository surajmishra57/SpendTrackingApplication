package com.incs.SpendTrackingApplication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "activity")
public class Activity {
    @Id
    private String id;
    private String email;
    private String activity;
    private Date date;

    public Activity() {
    }

    public Activity(String id, String email, String activity, Date date) {
        this.id = id;
        this.email = email;
        this.activity = activity;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", activity='" + activity + '\'' +
                ", date=" + date +
                '}';
    }
}
