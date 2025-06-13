package com.mediqueue.backend.dto;

import com.mediqueue.backend.model.Appointment;
import java.util.List;

public class AppointmentResponse {
    private Appointment appointment;
    private String disease;
    private List<String> symptoms;

    public AppointmentResponse(Appointment appointment, String disease, List<String> symptoms) {
        this.appointment = appointment;
        this.disease = disease;
        this.symptoms = symptoms;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
