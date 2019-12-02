package com.example.smscommunication.gatewayclient;

import com.example.smscommunication.constants.GatewayClient;
import com.example.smscommunication.dto.SmsRequest;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSingleSearchResponse;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Objects;

/** @author */
@Service
@Component
public class NexmoSmsClient implements SmsClient {
  /** In this file any information,error and trace print into console using "LOG" object */
  private static final Logger LOG = LoggerFactory.getLogger(NexmoSmsClient.class);

  /** Calling NexmoClient method */
  @Autowired private NexmoClient nexmoClient;

  /**
   * Take the value's from application.properties. if you not take the value application.properties
   * default value is given (${client.gateway.nexmo-timeout : 2500})
   */
  @Value("${client.gateway.nexmo-timeout : 2500}")
  private long timeout;

  /** Take the Brand Name from Application.properties */
  @Value("${client.gateway.nexmo-brand-name}")
  private String brandname;

  /** @return */
  @Override
  public int GatewayClient() {
    return GatewayClient.NEXMO.getCode();
  }

  /**
   * @param smsRequest Take the phone number into OTPCommunicationServiceImpl. java. After based on
   *     the phone number send the message into end * user. If message sent are not check based on
   *     status. send the message into end user after some time check the sms Status based on
   *     Thread.sleep(). If any Exception come handle TRY and CATCH block's.
   * @return If message status is "OK" return true going to (OTPCommunicationServiceImpl.java) or
   *     status is "Fail","undelivered","null" return false going to
   *     (OTPCommunicationServiceImpl.java)
   */
  @Override
  public boolean SendSms(final SmsRequest smsRequest) {
    LOG.trace("--> SendSms() > {}", smsRequest);

    boolean result = true;
    try {
      String messageText = "Welcome To FullPlate!";
      TextMessage message = new TextMessage(brandname, smsRequest.getPhoneNumber(), messageText);

      final SmsSubmissionResponse response = nexmoClient.getSmsClient().submitMessage(message);
      if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {

        Thread.sleep(timeout);

        final SmsSingleSearchResponse data =
            nexmoClient.getSmsClient().getSms(response.getMessages().get(0).getId());

        if (Objects.isNull(data.getFinalStatus())
            || data.getFinalStatus().equals("failed")
            || data.getFinalStatus().equals("rejected")) {

          LOG.error("NEXMO Message Sent Failed.");
          result = false;
        }
      } else {
        LOG.error("Message NOT Sent successfully.");
        result = false;
      }
    } catch (NexmoClientException | InterruptedException e) {
      LOG.error("Nexmo Exception is : {}", e);
      Thread.currentThread().interrupt();
    }
    LOG.trace("<-- SendSms() > response : {}", result);
    return result;
  }
}
