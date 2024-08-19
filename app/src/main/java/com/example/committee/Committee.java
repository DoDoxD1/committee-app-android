package com.example.committee;

public class Committee {
    String id;
    String name;
    String numberOfMembers;

    public Committee(String name, String numberOfMembers) {
        this.name = name;
        this.numberOfMembers = numberOfMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfMembers() {
        return numberOfMembers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
