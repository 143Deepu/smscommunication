package com.example.smscommunication.service;

import com.example.smscommunication.dto.SmsRequest;

public interface OTPCommunicationService {
  /** @param smsRequest */
  void selectGatewayAndSendOtp(SmsRequest smsRequest);
}
