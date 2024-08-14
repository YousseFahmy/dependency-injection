package com.talent.dependency_injection.entities;

public class Course {
    private int id;
    private String name;
    private String description;
    private int credit;

    public Course (int id, String name, String description, int credit){
        this.id = id;
        this.name = name;
        this.description = description;
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
