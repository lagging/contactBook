package com.ashish.ws.repository;

import com.ashish.WebServicesApplication;
import com.ashish.ws.models.Contact;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@SpringBootTest(classes = WebServicesApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup(ContactRepositoryTest.DATASET)
public class ContactRepositoryTest {

  protected static final String DATASET = "classpath:datasets/contacts.xml";

  @Autowired
  private ContactRepository contactRepository;

  @Test
  public void findFirstByEmailId() {
    Contact contact = contactRepository.findFirstByEmailId("ashish1@gmail.com");
    Assert.assertNotNull(contact);
    Assert.assertEquals("a", contact.getName());
  }

  @Test
  public void findByName(){
    List<Contact> contacts = contactRepository.findByName("a");
    Assert.assertEquals(1, contacts.size());
  }

  @Test
  public void findByNameOrEmailId() {
    PageRequest request = new PageRequest(0, 5, Sort.Direction.ASC
        , "id");
    List<Contact> contacts = contactRepository
        .findByNameOrEmailId("a", "ashish2@gmail.com", request);
    Assert.assertEquals(2, contacts.size());
    Assert.assertEquals(1, contacts.get(0).getId().intValue());
    Assert.assertEquals(2, contacts.get(1).getId().intValue());
  }

  @Test
  public void findByNameAndEmailId() {
    PageRequest request = new PageRequest(0, 5, Sort.Direction.ASC
        , "id");
    List<Contact> contacts = contactRepository
        .findByNameAndEmailId("a", "ashish1@gmail.com", request);
    Assert.assertEquals(1,contacts.size());
  }



}
