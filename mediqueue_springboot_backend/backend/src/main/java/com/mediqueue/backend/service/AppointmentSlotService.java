package com.mediqueue.backend.service;

import com.mediqueue.backend.model.Appointment;
import com.mediqueue.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentSlotService {

    @Autowired
    private AppointmentRepository repository;

    private final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    public String assignSlotBasedOnSeverity(Appointment appt) {
        String baseSlot = appt.getSelectedSlot().trim(); // e.g., "10:00 AM"
        LocalDate appointmentDate = appt.getDate();    // Date only

        // 1. Get all appointments for same date and same base hour slot
        List<Appointment> sameHourAppointments = repository.findAll().stream()
                .filter(a -> a.getDate() != null && a.getDate().equals(appointmentDate))
                .filter(a -> baseSlot.equals(a.getSelectedSlot().trim()))
                .collect(Collectors.toList());

        // 2. Compute severity score for each
        sameHourAppointments.sort((a, b) -> Integer.compare(
                getSeverityScore(b.getSymptomSeverity()), getSeverityScore(a.getSymptomSeverity())
        ));

        // 3. If 5 or more appointments already scheduled, reject
        if (sameHourAppointments.size() >= 5) {
            throw new IllegalStateException("All 10-minute slots are filled for this hour.");
        }

        // 4. Assign slot: 10:00, 10:10, ..., based on position
        // Use a locale-aware formatter for parsing 12-hour AM/PM time correctly
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        LocalTime baseTime = LocalTime.parse(baseSlot, timeFormatter);

        int position = sameHourAppointments.size(); // 0 to 4

        LocalTime assignedTime = baseTime.plusMinutes(position * 10);

// Format back into 12-hour slot
        String finalSlot = assignedTime.format(timeFormatter);
        appt.setFinalAppointmentTime(finalSlot);

        return finalSlot;
    }

    private int getSeverityScore(Map<String, String> severityMap) {
        if (severityMap == null) return 0;
        int score = 0;
        for (String level : severityMap.values()) {
            switch (level.toLowerCase()) {
                case "high": score += 3; break;
                case "medium": score += 2; break;
                case "low": score += 1; break;
            }
        }
        return score;
    }
}
