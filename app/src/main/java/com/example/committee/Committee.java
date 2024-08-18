package com.example.committee;

public class Committee {
    String name;
    int numberOfMembers;

    public Committee(String name) {
        this.name = name;
    }

    public Committee(String name, int numberOfMembers) {
        this.name = name;
        this.numberOfMembers = numberOfMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }
}
