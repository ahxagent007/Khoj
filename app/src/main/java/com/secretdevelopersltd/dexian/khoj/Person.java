package com.secretdevelopersltd.dexian.khoj;

public class Person {

    private String ID;
    private String NAME;
    private String NUMBER;
    private String LOCATION;
    private String SPEC;
    private String CATEGORY;

    Person(){}

    public Person(String ID, String NAME, String NUMBER, String LOCATION, String SPEC, String CATEGORY) {
        this.ID = ID;
        this.NAME = NAME;
        this.NUMBER = NUMBER;
        this.LOCATION = LOCATION;
        this.SPEC = SPEC;
        this.CATEGORY = CATEGORY;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getSPEC() {
        return SPEC;
    }

    public void setSPEC(String SPEC) {
        this.SPEC = SPEC;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }
}
