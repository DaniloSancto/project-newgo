package dev.danilosantos.infrasctructure;

import dev.danilosantos.infrasctructure.enums.SpaceCategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpaceManagement {
    private Space spaces;
    private Integer capacity;
    private Date date;
    private Date entry;
    private Date exit;

    public SpaceManagement() {
    }

    public SpaceManagement(Space spaces, Integer capacity, Date date, Date entry, Date exit) {
        this.spaces = spaces;
        this.capacity = capacity;
        this.date = date;
        this.entry = entry;
        this.exit = exit;
    }

    public Space getSpaces() {
        return spaces;
    }

    public void setSpaces(Space spaces) {
        this.spaces = spaces;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEntry() {
        return entry;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public Date getExit() {
        return exit;
    }

    public void setExit(Date exit) {
        this.exit = exit;
    }
}
