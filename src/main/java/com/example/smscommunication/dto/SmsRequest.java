package com.example.smscommunication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class SmsRequest {
  @NotBlank private final String phoneNumber;

  /**
   * Take the input from frontend.
   *
   * @param phoneNumber
   */
  public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
}
