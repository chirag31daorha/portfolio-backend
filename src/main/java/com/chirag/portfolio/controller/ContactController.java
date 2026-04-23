package com.chirag.portfolio.controller;

import com.chirag.portfolio.dto.ContactRequest;
import com.chirag.portfolio.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "https://chirag-porfolio.vercel.app/")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestBody ContactRequest request) {
        try {
            contactService.sendContactEmail(request);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Email sent successfully!"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "success", false,
                "message", "Failed: " + e.getMessage()
            ));
        }
    }
}