package com.rashi.AddressBook.Address.service;

import com.rashi.AddressBook.Address.DTO.ContactDTO;
import com.rashi.AddressBook.Address.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    List<Contact> contacts = new ArrayList<>();
    private long idCounter = 1; //auto-increment ID

    public List<ContactDTO> getAllContacts() {
        return contacts.stream().map(contact -> {
            ContactDTO dto = new ContactDTO();
            dto.setName(contact.getName());
            dto.setEmail(contact.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    // Get contact by ID
    public Contact getContactById(Long id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    // Add new contact
    public Contact addContact(Contact contact) {
        contact.setId(idCounter++); // Assign ID manually
        contacts.add(contact);
        return contact;
    }

    public Contact updateContact(Long id, Contact newContact) {
        Optional<Contact> optionalContact = contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();

        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(newContact.getName());
            existingContact.setEmail(newContact.getEmail());
            existingContact.setPhone(newContact.getPhone());
            return existingContact;
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    public void deleteContact(Long id) {
        contacts.removeIf(contact -> contact.getId().equals(id));
    }
}
