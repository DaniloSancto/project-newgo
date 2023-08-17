package dev.danilosantos.infrasctructure;

import dev.danilosantos.infrasctructure.enums.SpaceCategory;

public class Space {

    private SpaceCategory category;
    private String name;
    private Integer maxCapacity;

    public Space() {
    }

    public Space(SpaceCategory category, String name, Integer maxCapacity) {
        this.category = category;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public SpaceCategory getCategory() {
        return category;
    }

    public void setCategory(SpaceCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return category + ": " + name;
    }
}
