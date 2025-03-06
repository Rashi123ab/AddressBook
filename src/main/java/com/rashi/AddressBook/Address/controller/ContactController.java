package com.rashi.AddressBook.Address.controller;

import com.rashi.AddressBook.Address.model.Contact;
import com.rashi.AddressBook.Address.repository.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactRepository repository;

    public ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @PostMapping  //to save contact details
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(repository.save(contact));
    }

    @GetMapping    //to get all saved contacts
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")  //to get contact stored at particular id
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")   //to update contact stored at particular id
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact newContact) {
        return repository.findById(id).map(contact -> {
            contact.setName(newContact.getName());
            contact.setEmail(newContact.getEmail());
            contact.setPhone(newContact.getPhone());
            return ResponseEntity.ok(repository.save(contact));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")  //to delete contact stored at particular id
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
