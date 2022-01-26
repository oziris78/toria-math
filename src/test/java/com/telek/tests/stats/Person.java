package com.telek.tests.stats;

public class Person {

    public String name;
    public int age, height;

    public Person(String name, int age, int height){
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("(%s, %d, %d)", name, age, height);
    }
}
