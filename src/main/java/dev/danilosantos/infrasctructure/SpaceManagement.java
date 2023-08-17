package dev.danilosantos.infrasctructure;

import dev.danilosantos.infrasctructure.enums.SpaceCategory;
import dev.danilosantos.infrasctructure.util.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpaceManagement {
    private Space space;
    private String memberCardNumber;
    private String date;
    private String timeEnter;
    private Integer timeInUse;

    public SpaceManagement() {
    }

    public SpaceManagement(Space space, String memberCardNumber, Date date, Date timeEnter, Integer timeInUse) {
        this.space = space;
        this.memberCardNumber = memberCardNumber;
        this.date = DateFormat.date.format(date);
        this.timeEnter = DateFormat.time.format(timeEnter);
        this.timeInUse = timeInUse;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public String getMemberCardNumber() {
        return memberCardNumber;
    }

    public void setMemberCardNumber(String memberCardNumber) {
        this.memberCardNumber = memberCardNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeEnter() {
        return timeEnter;
    }

    public void setTimeEnter(String timeEnter) {
        this.timeEnter = timeEnter;
    }

    public Integer getTimeInUse() {
        return timeInUse;
    }

    public void setTimeInUse(Integer timeInUse) {
        this.timeInUse = timeInUse;
    }
}
