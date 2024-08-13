package com.talent.dependency_injection;

public class Course {
    String name;

    public Course(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
