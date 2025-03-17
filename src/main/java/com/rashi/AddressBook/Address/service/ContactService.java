package com.rashi.AddressBook.Address.service;

import com.rashi.AddressBook.Address.Interfaces.IAddressBookService;
import com.rashi.AddressBook.Address.DTO.ContactDTO;
import com.rashi.AddressBook.Address.model.Contact;
import com.rashi.AddressBook.Address.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IAddressBookService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact createAddressBookEntry(ContactDTO dto) {
        try {
            Contact addressBook = new Contact(null, dto.getName(), dto.getEmail(), dto.getPhone());
            return contactRepository.save(addressBook);
        } catch (Exception e) {
            System.out.println("Error creating entry: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Contact> getAllEntries() {
        try {
            return contactRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error fetching entries: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public Contact getEntryById(Long id) {
        try {
            Optional<Contact> contact = contactRepository.findById(id);
            return contact.orElse(null);
        } catch (Exception e) {
            System.out.println("Error fetching entry: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Contact updateEntry(Long id, ContactDTO dto) {
        try {
            Optional<Contact> optionalContact = contactRepository.findById(id);
            if (optionalContact.isPresent()) {
                Contact addressBook = optionalContact.get();
                addressBook.setName(dto.getName());
                addressBook.setEmail(dto.getEmail());
                addressBook.setPhone(dto.getPhone());
                return contactRepository.save(addressBook);
            } else {
                System.out.println("Address Book entry with ID " + id + " not found");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error updating entry: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteEntry(Long id) {
        try {
            if (contactRepository.existsById(id)) {
                contactRepository.deleteById(id);
            } else {
                System.out.println("Entry with ID " + id + " not found");
            }
        } catch (Exception e) {
            System.out.println("Error deleting entry: " + e.getMessage());
        }
    }
}