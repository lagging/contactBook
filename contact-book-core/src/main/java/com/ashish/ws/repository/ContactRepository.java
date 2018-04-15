package com.ashish.ws.repository;

import com.ashish.ws.models.Contact;
import java.util.List;;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  Contact findFirstByEmailId(String emailId);

  List<Contact> findByName(String name);

  List<Contact> findByNameOrEmailId(String name, String emailId, Pageable pageRequest);

  List<Contact> findByNameAndEmailId(String name, String emailId, Pageable pageRequest);

}
