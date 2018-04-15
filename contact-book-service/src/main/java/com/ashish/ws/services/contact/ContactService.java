package com.ashish.ws.services.contact;

import com.ashish.ws.dtos.contact.ContactDto;
import com.ashish.ws.models.Contact;
import java.util.List;

public interface ContactService {

  void createContact(ContactDto contactDto);

  void updateContact(Long contactId, ContactDto contactDto);

  void deleteContact(Long contactId);

  Contact readContact(Long contactId);

  List<Contact> searchByName(String name);

  Contact searchByEmailId(String emailId);

  List<Contact> getPageByNameOrEmail(String name, String emailId, int pageNumber, int size);

  List<Contact> getPageByNameAndEmail(String name, String emailId, Integer page, Integer size);
}
