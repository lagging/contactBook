package com.ashish.ws.api.controllers.contact;

import com.ashish.ws.models.Contact;
import java.util.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  private HttpHeaders createHttpHeaders(String user, String password) {
    String notEncoded = user + ":" + password;
    String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Authorization", encodedAuth);
    return headers;
  }

  private Contact getContact() {
    Contact contact = new Contact();
    contact.setEmailId("ashish123@gmail.com");
    contact.setPhoneNumber("8171229208");
    contact.setName("ashish");
    return contact;
  }


  @Test
  public void createContact() {
    HttpHeaders headers = createHttpHeaders("ashish", "ashish123");
    Contact contact = getContact();
    HttpEntity<?> entity = new HttpEntity<Object>(contact, headers);
    ResponseEntity<Void> responseEntity =
        restTemplate.postForEntity("/v1/contact/save", entity, Void.class);
    Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }



}
