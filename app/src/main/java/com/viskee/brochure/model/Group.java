package com.viskee.brochure.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
    private String name;
    private List<Brochure> brochures = new ArrayList<>();
    private List<School> schools = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Brochure> getBrochures() {
        return brochures;
    }

    public void setBrochures(List<Brochure> brochures) {
        this.brochures = brochures;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", schools=" + schools +
                '}';
    }
}
