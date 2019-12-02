package com.example.smscommunication.gatewayclient;

import com.example.smscommunication.dto.SmsRequest;

public interface SmsClient {

  int GatewayClient();

  boolean SendSms(SmsRequest smsRequest);
}
