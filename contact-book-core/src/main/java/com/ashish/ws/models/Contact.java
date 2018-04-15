package com.ashish.ws.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "contact")
@Getter
@Setter
public class Contact {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email_id", unique = true)
  private String emailId;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Temporal( TemporalType.TIMESTAMP )
  @CreationTimestamp
  @Column(name = "created_on")
  private Date createdOn;

  @Temporal( TemporalType.TIMESTAMP )
  @UpdateTimestamp
  @Column(name = "updated_on")
  private Date updatedOn;


}
