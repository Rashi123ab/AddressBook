package com.rashi.AddressBook.Address.service;

import com.rashi.AddressBook.Address.Exception.ContactException;
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
        Contact addressBook = new Contact(null, dto.getName(), dto.getEmail(), dto.getPhone());
        return contactRepository.save(addressBook);
    }

    @Override
    public List<Contact> getAllEntries() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getEntryById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactException("Employee with ID " + id + " not found"));
    }

    @Override
    public Contact updateEntry(Long id, ContactDTO dto) {
        Contact addressBook = contactRepository.findById(id)
                .orElseThrow(() -> new ContactException("Address Book entry with ID " + id + " not found"));
        addressBook.setName(dto.getName());
        addressBook.setEmail(dto.getEmail());
        addressBook.setPhone(dto.getPhone());

        return contactRepository.save(addressBook);
    }

    @Override
    public void deleteEntry(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactException("Entry with ID " + id + " not found");
        }
        contactRepository.deleteById(id);
    }
}