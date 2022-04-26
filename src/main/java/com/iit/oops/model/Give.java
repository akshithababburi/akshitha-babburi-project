package com.iit.oops.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Give extends AskAndGiveCommon {

    private String gid;

    public Give(String gid, String uid, String type, String description, LocalDate start_date, LocalDate end_date,
                String[] extra_zip, boolean is_active, LocalDateTime date_created) {
        super(uid, type, description, start_date, end_date, extra_zip, is_active, date_created);
        this.gid = gid;

    }

    public Give() {
    }

    @Override
    public String toString() {
        return "Give{" +
                "gid='" + gid + '\'' +
                "uid='" + this.getUid() + '\'' +
                "type='" + this.getType() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", start_date=" + this.getStart_date() +
                ", end_date=" + this.getEnd_date() +
                ", extra_zip=" + Arrays.toString(this.getExtra_zip()) +
                ", date_created=" + this.getDate_created() +
                ", is_active=" + this.isIs_active() +
                '}';
    }

    @JsonProperty
    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
