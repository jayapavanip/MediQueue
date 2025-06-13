package com.mediqueue.backend.controller;

import com.mediqueue.backend.dto.AppointmentResponse;
import com.mediqueue.backend.model.Appointment;
import com.mediqueue.backend.repository.AppointmentRepository;
import com.mediqueue.backend.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Appointment saveAppointment(@RequestBody Appointment appt) {
        appt.setStatus("PENDING");

        // ML prediction
        Map<String, Object> mlResult = mlService.predictDiseaseAndSymptoms(appt.getSymptoms());

        String predictedDisease = (String) mlResult.get("disease");
        List<String> predictedSymptoms = (List<String>) mlResult.get("symptoms");

        appt.setPredictedDisease(predictedDisease);
        appt.setPredictedSymptoms(predictedSymptoms);

        return repository.save(appt);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }
}
