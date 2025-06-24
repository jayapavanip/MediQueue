package com.mediqueue.backend.controller;

import com.mediqueue.backend.dto.SeverityRequest;
import com.mediqueue.backend.model.Appointment;
import com.mediqueue.backend.repository.AppointmentRepository;
import com.mediqueue.backend.service.AppointmentSlotService;
import com.mediqueue.backend.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:5173")  // Adjust based on your frontend port
public class AppointmentController {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private MLService mlService;

    @Autowired
    private AppointmentSlotService slotService;

    @PostMapping
    public Appointment saveAppointment(@RequestBody Appointment appt) {
        appt.setStatus("PENDING");

        // ML prediction
        Map<String, Object> mlResult = mlService.predictDiseaseAndSymptoms(appt.getSymptoms());

        String predictedDisease = (String) mlResult.get("disease");
        List<String> predictedSymptoms = (List<String>) mlResult.get("symptoms");

        appt.setPredictedDisease(predictedDisease);
        appt.setPredictedSymptoms(predictedSymptoms);

        System.out.println("Raw appointment: " + appt);
        System.out.println("Date: " + appt.getDate());
        return repository.save(appt);
    }

    @PostMapping("/severity")
    public ResponseEntity<Map<String, Object>> updateSeverity(@RequestBody SeverityRequest request) {
        Optional<Appointment> optionalAppointment = repository.findById(request.getAppointmentId());

        if (optionalAppointment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Appointment not found"));
        }

        Appointment appointment = optionalAppointment.get();
        appointment.setSymptomSeverity(request.getSymptomSeverities());

        System.out.println("Raw appointment: " + appointment);
        System.out.println("Date: " + appointment.getDate());

        // Assign final time slot based on severity sorting
        try {
            String assignedSlot = slotService.assignSlotBasedOnSeverity(appointment);
            appointment.setStatus("SCHEDULED");
            repository.save(appointment);

            return ResponseEntity.ok(Map.of(
                    "message", "Severity updated and slot assigned",
                    "finalAppointmentTime", assignedSlot
            ));

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        }
    }



    @GetMapping
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }
}
