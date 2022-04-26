package com.iit.oops.model;

import java.time.LocalDate;

public class Note {
    private String uid;
    private String nid;
    private String to_type;
    private String to_user_id;
    private String to_id;
    private String description;
    private LocalDate date_created;

    public Note(String uid, String nid, String to_type, String to_user_id, String to_id, String description, LocalDate date_created) {
        this.uid = uid;
        this.nid = nid;
        this.to_type = to_type;
        this.to_user_id = to_user_id;
        this.to_id = to_id;
        this.description = description;
        this.date_created = date_created;
    }

    public Note() {
    }

    @Override
    public String toString() {
        return "Note{" +
                "uid='" + uid + '\'' +
                ", nid='" + nid + '\'' +
                ", to_type='" + to_type + '\'' +
                ", to_user_id='" + to_user_id + '\'' +
                ", to_id='" + to_id + '\'' +
                ", description='" + description + '\'' +
                ", date_created=" + date_created +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTo_type() {
        return to_type;
    }

    public String getTo_user_id() {
        return to_user_id;
    }

    public String getTo_id() {
        return to_id;
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
