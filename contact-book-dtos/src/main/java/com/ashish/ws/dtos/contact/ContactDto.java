package com.ashish.ws.dtos.contact;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {

  private String name;

  @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
      +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
      +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
      message="{invalid.email}")
  @NotNull
  private String emailId;

  @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
      message="{invalid.phonenumber}")
  private String phoneNumber;

}
