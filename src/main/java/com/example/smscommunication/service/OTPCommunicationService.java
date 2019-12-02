package com.example.smscommunication.service;

import com.example.smscommunication.dto.SmsRequest;

public interface OTPCommunicationService {
  void selectGatewayAndSendOtp(SmsRequest smsRequest);
}
