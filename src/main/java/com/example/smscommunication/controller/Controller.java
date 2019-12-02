package com.example.smscommunication.controller;

import com.example.smscommunication.dto.SmsRequest;
import com.example.smscommunication.service.OTPCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/gateway")
public class Controller {
  /** calling OTPCommunicationService Interface */
  @Autowired private OTPCommunicationService otpCommunicationService;

  /**
   * Calling Interface from -> selectGatewayAndSendOtp(). calling smsreqest take the input from
   * frontend
   *
   * @param smsRequest
   */
  @PostMapping(path = "/sms")
  public void selectGatewayAndSendOtp(@RequestBody SmsRequest smsRequest) {
    otpCommunicationService.selectGatewayAndSendOtp(smsRequest);
  }
}
