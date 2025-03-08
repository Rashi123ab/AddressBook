package com.rashi.AddressBook.Address.controller;

import com.rashi.AddressBook.Address.DTO.ContactDTO;
import com.rashi.AddressBook.Address.model.Contact;
import com.rashi.AddressBook.Address.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<Contact> createEntry(@RequestBody ContactDTO dto) {
        Contact addressBook = contactService.createAddressBookEntry(dto);
        return ResponseEntity.ok(addressBook);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllEntries() {
        return ResponseEntity.ok(contactService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getEntryById(@PathVariable Long id) {
        Contact addressBook = contactService.getEntryById(id);
        return (addressBook != null) ? ResponseEntity.ok(addressBook) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateEntry(@PathVariable Long id, @RequestBody ContactDTO dto) {
        Contact updatedEntry = contactService.updateEntry(id, dto);
        return (updatedEntry != null) ? ResponseEntity.ok(updatedEntry) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        contactService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}