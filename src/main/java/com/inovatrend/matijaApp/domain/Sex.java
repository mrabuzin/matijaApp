package com.inovatrend.matijaApp.domain;

public enum Sex {

  //  MALE,
    // FEMALE;



    MALE("Male"),
    FEMALE("Female");

    private String label;

    Sex(String sex) {

        this.label = sex;

    }

    public String sex(){
        return label;
    }
}
