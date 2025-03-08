package com.rashi.AddressBook.Address.Interfaces;

import com.rashi.AddressBook.Address.DTO.ContactDTO;
import com.rashi.AddressBook.Address.model.Contact;

import java.util.List;

public interface IAddressBookService {
    Contact createAddressBookEntry(ContactDTO dto);
    List<Contact> getAllEntries();
    Contact getEntryById(Long id);
    Contact updateEntry(Long id, ContactDTO dto);
    void deleteEntry(Long id);
}