package com.ashish.ws.services.contact;

import com.ashish.ws.commons.exceptions.DuplicateException;
import com.ashish.ws.dtos.contact.ContactDto;
import com.ashish.ws.models.Contact;
import com.ashish.ws.repository.ContactRepository;
import com.ashish.ws.services.contact.impl.ContactServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContactServiceTest {

  @InjectMocks
  private ContactServiceImpl contactService;

  @Mock
  private ContactRepository contactRepository;

  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
  }

  private ContactDto getContactDto() {
    ContactDto contact = new ContactDto();
    contact.setEmailId("tmp1@gmail.com");
    contact.setName("ashish");
    contact.setPhoneNumber("9411425508");
    return contact;
  }

  private Contact getContact() {
    Contact contact = new Contact();
    contact.setId(1l);
    contact.setEmailId("tmp2@gmail.com");
    contact.setName("ashish123");
    contact.setPhoneNumber("9411425509");
    return contact;
  }

  @Test
  public void successfullyCreateContact() {

    ContactDto contact = getContactDto();
    ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
    Mockito.when(contactRepository.findFirstByEmailId("tmp1@gmail.com")).thenReturn(null);
    contactService.createContact(contact);
    Mockito.verify(contactRepository).save(contactArgumentCaptor.capture());
    Contact contact1 = contactArgumentCaptor.getValue();
    Assert.assertEquals(contact.getEmailId(), contact1.getEmailId());
    Assert.assertEquals(contact.getName(), contact1.getName());
    Assert.assertEquals(contact.getPhoneNumber(), contact1.getPhoneNumber());
  }

  @Test(expected = DuplicateException.class)
  public void failedCreatingContact() {
    ContactDto contactDto = getContactDto();
    Contact contact = getContact();
    Mockito.when(contactRepository.findFirstByEmailId("tmp1@gmail.com")).thenReturn(contact);
    contactService.createContact(contactDto);
  }

  @Test
  public void successfullyUpdateContact() {
    ContactDto contactDto = getContactDto();
    Contact contact = getContact();
    ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
    Mockito.when(contactRepository.findById(1l)).thenReturn(java.util.Optional.ofNullable(contact));
    contactService.updateContact(1l,contactDto);
    Mockito.verify(contactRepository).save(contactArgumentCaptor.capture());
    Contact contact1 = contactArgumentCaptor.getValue();
    Assert.assertEquals(contactDto.getName(), contact1.getName());
    Assert.assertEquals(contactDto.getPhoneNumber(), contact1.getPhoneNumber());

  }

  @Test
  public void successfullyDeleteContact() {
    Contact contact = getContact();
    Mockito.when(contactRepository.findById(1l)).thenReturn(java.util.Optional.ofNullable(contact));
    contactService.deleteContact(1l);
    Mockito.verify(contactRepository,Mockito.times(1)).delete(contact);
  }

  @Test
  public void successfullyReadContact() {
    Contact contact = getContact();
    Mockito.when(contactRepository.findById(1l)).thenReturn(java.util.Optional.ofNullable(contact));
    Contact contact1 = contactService.readContact(1l);
    Assert.assertEquals(contact.getEmailId(), contact1.getEmailId());
  }

  @Test
  public void successfullyGetPageByNameOrEmail() {

    List<Contact> contacts = new ArrayList<>();
    Contact contact = getContact();
    contacts.add(contact);
    Mockito.when(contactRepository.findByNameOrEmailId(Mockito.anyString(),
        Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(contacts);
    List<Contact> contactsList = contactService
        .getPageByNameOrEmail("ashish", "ashishrathore@gmail.com",
            1, 10);
    Assert.assertEquals(contacts.size(), contactsList.size());
  }

  @Test
  public void successfullyGetPageByNameAndEmail() {

    List<Contact> contacts = new ArrayList<>();
    Contact contact = getContact();
    contacts.add(contact);
    Mockito.when(contactRepository.findByNameAndEmailId(Mockito.anyString(),
        Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(contacts);
    List<Contact> contactsList = contactService
        .getPageByNameAndEmail("ashish", "ashishrathore@gmail.com",
            1, 10);
    Assert.assertEquals(contacts.size(), contactsList.size());
  }



}
