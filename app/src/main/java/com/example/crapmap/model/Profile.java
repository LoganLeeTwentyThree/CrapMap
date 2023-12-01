package com.example.crapmap.model;

public class Profile {
    protected String name;

    public Profile( String name )
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
