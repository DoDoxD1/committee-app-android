package com.example.committee;

public class MemberModel {
    String id;
    String name;
    Boolean isPaid;

    public MemberModel(String name, Boolean isPaid) {
        this.name = name;
        this.isPaid = isPaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPaid() {
        return isPaid;
    }

}
