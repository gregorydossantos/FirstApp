package br.com.greg.schedule.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {

    //Attributes
    private int id = 0;
    private String name;
    private String phone;
    private String email;

    //Builders
    public Student() {}

    public Student(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    //Getters and Setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    //Methods
    public boolean hasId() {
        return id > 0;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - " + phone;
    }


}
