package com.example.mcarfixrecycler.model;

public class ListData {

    private String flag;
    private String countryname;
    private String regulatorname;
    private String regulatorweb;

    public ListData(String flag, String countryname, String regulatorname, String regulatorweb) {
        this.flag = flag;
        this.countryname = countryname;
        this.regulatorname = regulatorname;
        this.regulatorweb = regulatorweb;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getRegulatorname() {
        return regulatorname;
    }

    public void setRegulatorname(String regulatorname) {
        this.regulatorname = regulatorname;
    }

    public String getRegulatorweb() {
        return regulatorweb;
    }

    public void setRegulatorweb(String regulatorweb) {
        this.regulatorweb = regulatorweb;
    }
}
