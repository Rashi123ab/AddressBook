package com.rashi.AddressBook.Address.service;

import com.rashi.AddressBook.Address.Interfaces.IAddressBookService;
import com.rashi.AddressBook.Address.DTO.ContactDTO;
import com.rashi.AddressBook.Address.model.Contact;
import com.rashi.AddressBook.Address.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContactService implements IAddressBookService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact createAddressBookEntry(ContactDTO dto) {
        Contact addressBook = new Contact(null,dto.getName(), dto.getEmail(), dto.getPhone());
        return contactRepository.save(addressBook);
    }

    @Override
    public List<Contact> getAllEntries() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getEntryById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact updateEntry(Long id, ContactDTO dto) {
        Optional<Contact> existingEntry = contactRepository.findById(id);

        if (existingEntry.isPresent()) {
            Contact updatedEntry = existingEntry.get();
            updatedEntry.setName(dto.getName());
            updatedEntry.setEmail(dto.getEmail());
            updatedEntry.setPhone(dto.getPhone());
            return contactRepository.save(updatedEntry);
        }
        return null;
    }

    @Override
    public void deleteEntry(Long id) {
        contactRepository.deleteById(id);
    }
}