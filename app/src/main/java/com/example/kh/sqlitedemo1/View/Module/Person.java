package com.example.kh.sqlitedemo1.View.Module;

/**
 * Created by kh on 4/24/2017.
 */

public class Person {
    private int id;
    private String name;
    private String address;
    private double salary;
    public Person(){

    }
    public Person(int id, String name, String address, double salary){
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddrss(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
