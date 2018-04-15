package com.ashish.ws.api.controllers.contact;

import com.ashish.ws.dtos.contact.ContactDto;
import com.ashish.ws.models.Contact;
import com.ashish.ws.services.contact.ContactService;
import java.util.List;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contact")
public class ContactController {

  @Autowired
  ContactService contactService;

  @RequestMapping(method = RequestMethod.POST, value = "/save")
  public void saveContact(@Valid @RequestBody ContactDto contactDto) {
    contactService.createContact(contactDto);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{contactId}")
  public Contact getContact(@Valid @PathVariable("contactId")Long contactId) {
    return contactService.readContact(contactId);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{contactId}")
  public void updateContact(@Valid @PathVariable("contactId")Long contactId,
      @Valid @RequestBody ContactDto contactDto) {
     contactService.updateContact(contactId, contactDto);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{contactId}")
  public void deleteContact(@Valid @PathVariable("contactId")Long contactId) {
    contactService.deleteContact(contactId);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/search")
  List<Contact> getContactByNameOrEmail(@RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "emailId", required = false) String emailId,
      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
    return contactService.getPageByNameOrEmail(name, emailId, page, size);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/search/{emailId}")
  List<Contact> getContactByNameAndEmail(@PathVariable("emailId") String emailId,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
    return contactService.getPageByNameAndEmail(name, emailId, page, size);
  }

}
