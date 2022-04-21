package com.iit.oops.model;

import java.time.LocalDate;

public class Thanks {
    private String thank_to;
    private String description;
    private LocalDate date_created;
    private String uid;
    private String tid;

    @Override
    public String toString() {
        return "Thanks{" +
                "thank_to='" + thank_to + '\'' +
                ", description='" + description + '\'' +
                ", date_created=" + date_created +
                ", uid='" + uid + '\'' +
                ", tid='" + tid + '\'' +
                '}';
    }

    public Thanks(String uid, String tid, String thank_to, String description, LocalDate date_created) {
        this.uid = uid;
        this.tid = tid;
        this.thank_to = thank_to;
        this.description = description;
        this.date_created = date_created;
    }

    public Thanks() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getThank_to() {
        return thank_to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_created() {
        return date_created;
    }
}
