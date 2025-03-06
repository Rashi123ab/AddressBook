package com.rashi.AddressBook.Address.service;

import com.rashi.AddressBook.Address.DTO.ContactDTO;
import com.rashi.AddressBook.Address.model.Contact;
import com.rashi.AddressBook.Address.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService{

    @Autowired
    private ContactRepository contactRepository;

    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(contact -> {
            ContactDTO dto = new ContactDTO();
            dto.setName(contact.getName());
            dto.setEmail(contact.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    // Get contact by ID
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    // Add new contact
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Update contact by ID
    public Contact updateContact(Long id, Contact newContact) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(newContact.getName());
            existingContact.setEmail(newContact.getEmail());
            existingContact.setPhone(newContact.getPhone());
            return contactRepository.save(existingContact);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    // Delete contact by ID
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}