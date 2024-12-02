package com.alten.ecommerce.controllers;

import com.alten.ecommerce.model.Contact;
import com.alten.ecommerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> submitContactForm(@RequestBody Contact contact) {
        try {
            return ResponseEntity.ok(contactService.saveContact(contact));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
