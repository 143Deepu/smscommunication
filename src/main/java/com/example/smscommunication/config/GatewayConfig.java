package com.example.smscommunication.config;

import com.nexmo.client.NexmoClient;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
  /**
   * Nexmo credentials Calling to application.properties
   *
   * @param apiKey
   * @param secretKey
   * @return the credentials to NexmoSmsClient.java Class.
   */
  @Bean
  public NexmoClient nexmoClient(
      @Value("${client.gateway.nexmo-api-key}") final String apiKey,
      @Value("${client.gateway.nexmo-api-secret}") final String secretKey) {
    return new NexmoClient.Builder().apiKey(apiKey).apiSecret(secretKey).build();
  }

  /**
   * Twilio credentials Calling to application.properties. After checking it's credentials Correct
   * or not
   *
   * @param accountSID
   * @param authToken
   */
  public GatewayConfig(
      @Value("${client.gateway.twilio-account-sid}") final String accountSID,
      @Value("${client.gateway.twilio-auth-token}") final String authToken) {
    Twilio.init(accountSID, authToken);
  }
}
