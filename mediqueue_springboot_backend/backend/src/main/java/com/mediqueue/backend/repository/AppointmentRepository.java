package com.mediqueue.backend.repository;

import com.mediqueue.backend.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
