package com.example.smscommunication.service;

import com.example.smscommunication.constants.GatewayClient;
import com.example.smscommunication.dto.SmsRequest;
import com.example.smscommunication.gatewayclient.CommunicationGatewayFactory;
import com.example.smscommunication.gatewayclient.SmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@Service
@Component
public class OTPCommunicationServiceImpl implements OTPCommunicationService {
  /** In this file any information,error and trace print into console using "LOG" object */
  private static final Logger LOG = LoggerFactory.getLogger(OTPCommunicationServiceImpl.class);

  /** Calling the CommunicationGatewayFactory */
  @Autowired private CommunicationGatewayFactory communicationGatewayFactory;

  /**
   * This method is used to iterator purpose on "Twilio and Nexmo".
   *
   * @param smsRequest Take the phone number into frontend. Take the value from
   *     communicationGatewayFactory.java based on for loop. After call the SendSms() ->
   *     SmsClient.java Interface return the value stored into the boolean. After check the result
   *     is "true" break the loop. If result is "false" again loop will be repeat.
   * @throws InterruptedException
   */
  @Override
  public void selectGatewayAndSendOtp(SmsRequest smsRequest){
    LOG.trace("--> selectGatewayAndSendOtp() -- : {}", smsRequest);

    for (Map.Entry<Integer, SmsClient> entrySet :
        communicationGatewayFactory.getGatewayMap().entrySet()) {

      final boolean result = entrySet.getValue().SendSms(smsRequest);

      LOG.debug(
          "-- selectGatewayAndSendOtp() > OTP delivery attempt using {} client. Delivery Status : {}",
          GatewayClient.valueOf(entrySet.getKey()),
          result);
      if (result) break;
    }
    LOG.trace("<-- selectGatewayAndSendOtp()");
  }
}
