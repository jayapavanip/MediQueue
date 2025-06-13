package com.mediqueue.backend.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MLService {

    private final RestTemplate restTemplate;
    private static final String FLASK_API_URL = "http://127.0.0.1:5000/predict-disease";

    public MLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> predictDiseaseAndSymptoms(String symptoms) {
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("symptoms", symptoms);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(FLASK_API_URL, request, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            }
        } catch (Exception e) {
            System.err.println("Error calling Flask API: " + e.getMessage());
        }

        // Fallback
        Map<String, Object> fallback = new HashMap<>();
        fallback.put("disease", "Unknown");
        fallback.put("symptoms", Collections.emptyList());
        return fallback;
    }
}
