package com.example.smscommunication.gatewayclient;

import com.example.smscommunication.dto.SmsRequest;
import com.nexmo.client.HttpWrapper;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsClient;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.http.TwilioRestClient;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TwilioSmsClientTest {

  @InjectMocks private TwilioSmsClient twilioSmsClient;

  private MockMvc mockMvc;
  @Autowired private WebApplicationContext wac;

  @Value("${client.gateway.twilio-trail-number}")
  private String trailnumber;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    ReflectionTestUtils.setField(twilioSmsClient,trailnumber,"+12053089332");
  }

  @Test
  public void Twilio_Send_Msg() throws TwilioException {
//    assertNotEquals(trailnumber,twilioSmsClient.sendSms(new SmsRequest("+12053089332")));
    assertTrue("twilio sms testcase's.", twilioSmsClient.sendSms(new SmsRequest("+919133259525")));
  }
}
