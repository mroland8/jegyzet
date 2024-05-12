package com.example.jegyzet;

public class jegyzet {
    private String cim;
    private String tartalom;

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getTartalom() {
        return tartalom;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
    }


    public jegyzet(String cim, String tartalom) {
        this.cim = cim;
        this.tartalom = tartalom;
    }


}
