package com.example.smscommunication.gatewayclient;

import com.example.smscommunication.constants.GatewayClient;
import com.example.smscommunication.dto.SmsRequest;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.Objects;

@Service
public class TwilioSmsClient implements SmsClient {
  /** In this file any information,error and trace print into console using "LOG" object */
  private static final Logger LOG = LoggerFactory.getLogger(TwilioSmsClient.class);
  /**
   * Take the value's from application.properties. if you not take the value application.properties
   * default value is given (${client.gateway.nexmo-timeout : 2500})
   */
  @Value("${client.gateway.nexmo-timeout : 2500}")
  private long timeout;
  /** Take the Trail-Number from Application.properties */
  @Value("${client.gateway.twilio-trail-number}")
  private String trailnumber;

  /**
   * Get the code into GatewayClient.java
   *
   * @return the Nexmo code into CommunicationGatewayFactory.java
   */
  @Override
  public int gatewayClient() {
    return GatewayClient.TWILIO.getCode();
  }
  /**
   * @param smsRequest Take the phone number into OTPCommunicationServiceImpl.java. After based on
   *     the phone number send the message into end user. If message sent are not check based on
   *     status. send the message into end user after some time check the sms Status based on
   *     Thread.sleep(). If any Exception come handle TRY and CATCH block's.
   * @return If message status is "OK" return true going to (OTPCommunicationServiceImpl.java) or
   *     status is "Fail","undelivered","null" return false going to
   *     (OTPCommunicationServiceImpl.java)
   */
  @Override
  public boolean sendSms(final SmsRequest smsRequest) {
    LOG.trace("--> SendSms() > {}", smsRequest);

    boolean result = true;
    try {
      PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
      PhoneNumber from = new PhoneNumber(trailnumber);
      String messageText = "Welcome To FullPlate!";
      final Message message =
          Message.creator(to, from, messageText)
              .setStatusCallback(URI.create("http://postb.in/1234abcd"))
              .create();
      Thread.sleep(timeout);
      if (String.valueOf(message.getStatus()).equals("sent")
          || String.valueOf(message.getStatus()).equals("queued")
          || String.valueOf(message.getStatus()).equals("delivered")) {
        if (Objects.isNull(message.getStatus())
            || String.valueOf(message.getStatus()).equals("failed")
            || String.valueOf(message.getStatus()).equals("undelivered")) {
          LOG.error("Twilio Message Sent Failed.");
          result = false;
        }

      } else {
        LOG.error("Message NOT Sent successfully.");
        result = false;
      }

    } catch (TwilioException | InterruptedException e) {
      LOG.error("Twilio Exception is : {} ", e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    LOG.trace("<-- SendSms() > response : {}", result);

    return result;
  }
}
