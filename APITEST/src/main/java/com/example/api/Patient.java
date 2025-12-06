package com.example.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient {
    private int id;
    private Integer insuranceIdFk;
    private String name;
    private String bloodGroup;
    private String email;
    private String gender;

    // Getters & Setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Integer getInsuranceIdFk() {
        return insuranceIdFk;
    }
    public void setInsuranceIdFk(Integer insuranceIdFk) {
        this.insuranceIdFk = insuranceIdFk;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }


}
