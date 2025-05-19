package com.mediqueue.backend.controller;

import com.mediqueue.backend.model.Appointment;
import com.mediqueue.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")  // Enable for frontend
public class AppointmentController {

    @Autowired
    private AppointmentRepository repository;

    @PostMapping
    public Appointment saveAppointment(@RequestBody Appointment appt) {
        appt.setStatus("PENDING");
        return repository.save(appt);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }
}
