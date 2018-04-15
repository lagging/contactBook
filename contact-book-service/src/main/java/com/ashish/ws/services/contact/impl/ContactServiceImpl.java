package com.ashish.ws.services.contact.impl;

import com.ashish.ws.commons.exceptions.DuplicateException;
import com.ashish.ws.commons.exceptions.ResourceNotFoundException;
import com.ashish.ws.dtos.contact.ContactDto;
import com.ashish.ws.models.Contact;
import com.ashish.ws.repository.ContactRepository;
import com.ashish.ws.services.contact.ContactService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

  @Autowired
  ContactRepository contactRepository;

  @Override
  public void createContact(ContactDto contactDto) {
    Contact contact = getContact(contactDto);
    Contact existingContact = contactRepository.findFirstByEmailId(contactDto.getEmailId());
    if ( existingContact != null ) {
      throw new DuplicateException("Contact","emailId","Given Email Id is already Present");
    }
    contactRepository.save(contact);
  }

  @Override
  public void updateContact(Long contactId, ContactDto contactDto) {
    Contact contact = contactRepository.findById(contactId).
        orElseThrow(() -> new ResourceNotFoundException("Contact", "id",
            "Given Contact Id is not Present"));
    contact.setPhoneNumber(contactDto.getPhoneNumber());
    contact.setName(contactDto.getName());
    contact.setEmailId(contactDto.getEmailId());
    contactRepository.save(contact);
  }

  @Override
  public void deleteContact(Long contactId) {
    Contact contact = contactRepository.findById(contactId).
        orElseThrow(() -> new ResourceNotFoundException("Contact", "id",
            "Given Contact Id is not Present"));
    contactRepository.delete(contact);
  }

  @Override
  public Contact readContact(Long contactId) {
    Contact contact = contactRepository.findById(contactId).
        orElseThrow(() -> new ResourceNotFoundException("Contact", "id",
            "Given Contact Id is not Present"));
    return contact;
  }

  public List<Contact> searchByName(String name) {
    return contactRepository.findByName(name);
  }

  public Contact searchByEmailId(String emailId) {
    Contact contact = contactRepository.findFirstByEmailId(emailId);
    if ( contact == null ) {
      throw new ResourceNotFoundException("Contact","id","Given email Id is not Present");
    }
    return contact;
  }

  @Override
  public List<Contact> getPageByNameOrEmail(String name, String emailId, int pageNumber, int size) {
    PageRequest request = new PageRequest(pageNumber - 1, size, Sort.Direction.ASC
        , "id");
    return contactRepository.findByNameOrEmailId(name, emailId, request);
  }

  @Override
  public List<Contact> getPageByNameAndEmail(String name, String emailId, Integer page,
      Integer size) {
    PageRequest request = new PageRequest(page - 1, size, Sort.Direction.ASC
        , "id");
    return contactRepository.findByNameAndEmailId(name, emailId, request);
  }

  private Contact getContact(ContactDto contactDto) {
    Contact contact = new Contact();
    contact.setEmailId(contactDto.getEmailId());
    contact.setName(contactDto.getName());
    contact.setPhoneNumber(contactDto.getPhoneNumber());
    return contact;
  }
}
