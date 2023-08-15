package dev.danilosantos.infrasctructure;

import dev.danilosantos.infrasctructure.enums.SpaceCategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpaceManagement {
    private List<Space> spaces = new ArrayList<>();
    private Integer capacity;
    private Date date;
    private Date entry;
    private Date exit;

    public SpaceManagement() {
    }

    public SpaceManagement(List<Space> spaces, Integer capacity, Date date, Date entry, Date exit) {
        this.spaces = spaces;
        this.capacity = capacity;
        this.date = date;
        this.entry = entry;
        this.exit = exit;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
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

    private void insertStandardSpaces() {
        spaces.add(new Space(SpaceCategory.ESPORTES, "quadra de futebol indoor"));
        spaces.add(new Space(SpaceCategory.ESPORTES, "quadra de vôlei de praia"));
        spaces.add(new Space(SpaceCategory.ESPORTES, "quadra de beach tennis"));
        spaces.add(new Space(SpaceCategory.ESPORTES, "campo de golfe 1"));
        spaces.add(new Space(SpaceCategory.ESPORTES, "campo de golfe 2"));
        spaces.add(new Space(SpaceCategory.ESPORTES, "piscina olímpica 1"));
        spaces.add(new Space(SpaceCategory.ESPORTES, "piscina olímpica 2"));
        spaces.add(new Space(SpaceCategory.RELAXAMENTO, "lago com pedalinhos"));
        spaces.add(new Space(SpaceCategory.RECREACAO, "jardim botânico"));
        spaces.add(new Space(SpaceCategory.ESPORTES, "academia"));
        spaces.add(new Space(SpaceCategory.RELAXAMENTO, "spá"));
        spaces.add(new Space(SpaceCategory.RECREACAO, "área para churrasco"));
        spaces.add(new Space(SpaceCategory.RECREACAO, "parque infantil"));
    }
}
