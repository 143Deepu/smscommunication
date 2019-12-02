package com.example.smscommunication.gatewayclient;

import com.example.smscommunication.dto.SmsRequest;

public interface SmsClient {

  int gatewayClient();

  boolean sendSms(SmsRequest smsRequest);
}
