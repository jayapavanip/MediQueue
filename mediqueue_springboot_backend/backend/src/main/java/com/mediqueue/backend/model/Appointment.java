package com.mediqueue.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;

@Document(collection = "UserData")
public class Appointment {
    @Id
    private String id;

    private String fullName;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private Date date;
    private String selectedSlot;
    private String symptoms;
    private String status;
    private String predictedDisease;
    private List<String> predictedSymptoms;

    public Appointment() {}

  /*  public Appointment(String fullName, int age, String gender, double weight, double height,
                       Date date, String selectedSlot, String symptoms, String status) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.selectedSlot = selectedSlot;
        this.symptoms = symptoms;
        this.status = status;
    } */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(String selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPredictedDisease() {
        return predictedDisease;
    }

    public void setPredictedDisease(String predictedDisease) {
        this.predictedDisease = predictedDisease;
    }

    public List<String> getPredictedSymptoms() {
        return predictedSymptoms;
    }

    public void setPredictedSymptoms(List<String> predictedSymptoms) {
        this.predictedSymptoms = predictedSymptoms;
    }
// Getters and setters for all fields
    // (You can use Lombok if you prefer)
}
