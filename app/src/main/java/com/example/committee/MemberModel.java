package com.example.committee;

public class MemberModel {
    String name;
    Boolean isPaid;

    public MemberModel(String name, Boolean isPaid) {
        this.name = name;
        this.isPaid = isPaid;
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

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
