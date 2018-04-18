package com.inovatrend.matijaApp.domain;


import lombok.Getter;

@Getter

public enum Sex {

  //  MALE,
    // FEMALE;



    MALE("Male"),
    FEMALE("Female");

    private String label;

    Sex(String sex) {

        this.label = sex;

    }

    public String getLabel() {
        return label;
    }
}
