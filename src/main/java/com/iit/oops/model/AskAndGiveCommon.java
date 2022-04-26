package com.iit.oops.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AskAndGiveCommon {

    private String uid;
    private String type;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private String[] extra_zip;
    private LocalDateTime date_created;
    private boolean is_active;

    public AskAndGiveCommon(String uid, String type, String description, LocalDate start_date, LocalDate end_date,
                            String[] extra_zip, boolean is_active, LocalDateTime date_created) {
        this.uid = uid;
        this.type = type;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.extra_zip = extra_zip;
        this.is_active = is_active;
        this.date_created = date_created;
    }

    public AskAndGiveCommon() {
    }

    @JsonProperty
    public String getUid() {
        return uid;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public LocalDate getStart_date() {
        return start_date;
    }

    @JsonProperty
    public LocalDate getEnd_date() {
        return end_date;
    }

    @JsonProperty
    public String[] getExtra_zip() {
        return extra_zip;
    }

    @JsonProperty
    public boolean isIs_active() {
        return is_active;
    }

    @JsonProperty
    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @JsonProperty
    public LocalDateTime getDate_created() {
        return date_created;
    }


}
