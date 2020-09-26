package br.com.greg.schedule.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Student implements Serializable {

    //Attributes
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String lastname;
    private String phone;
    private String cellPhone;
    private String email;
    private Calendar registrationDate = Calendar.getInstance();

    //Builders
    public Student() {}

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

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    //Methods
    public boolean hasId() {
        return id > 0;
    }

    @SuppressLint("SimpleDateFormat")
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(registrationDate.getTime());
    }

    @NonNull
    @Override
    public String toString() {
        return name + " - " + cellPhone;
    }

    public String getFullName() {
        return name + " " + lastname;
    }
}
