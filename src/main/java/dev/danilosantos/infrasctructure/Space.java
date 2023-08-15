package dev.danilosantos.infrasctructure;

import dev.danilosantos.infrasctructure.enums.SpaceCategory;

public class Space {

    private SpaceCategory category;
    private String name;

    public Space() {
    }

    public Space(SpaceCategory category, String name) {
        this.category = category;
        this.name = name;
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
}
