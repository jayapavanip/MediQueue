package com.mediqueue.backend.dto;

import java.util.Map;

public class SeverityRequest {
    private String appointmentId;
    private Map<String, String> symptomSeverities;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Map<String, String> getSymptomSeverities() {
        return symptomSeverities;
    }

    public void setSymptomSeverities(Map<String, String> symptomSeverities) {
        this.symptomSeverities = symptomSeverities;
    }
}
