package com.example.smscommunication.gatewayclient;

import com.example.smscommunication.config.GatewayConfig;
import com.example.smscommunication.dto.SmsRequest;
import com.nexmo.client.HttpWrapper;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.account.CreateSecretRequest;
import com.nexmo.client.sms.SmsClient;
import com.nexmo.client.verify.VerifyClient;
import com.nexmo.client.verify.VerifyRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NexmoSmsClientTest {

  @InjectMocks
  private NexmoSmsClient nexmoSmsClient;

  @Mock
  private NexmoClient nexmoClient;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    ReflectionTestUtils.setField(nexmoSmsClient,"apikey","b01a1c53a");
    ReflectionTestUtils.setField(nexmoSmsClient, "apisecretkey", "bkvjh3JXwji3N5Mh");
    ReflectionTestUtils.setField(nexmoSmsClient, "From", "NEXMO");
  }

  @Test
  public void Nexmo_Send_Msg() throws NexmoClientException {

        GatewayConfig gatewayConfig = mock(GatewayConfig.class);
        Mockito.when(gatewayConfig.nexmoClient(Mockito.anyString(),Mockito.anyString())).thenReturn(new

     NexmoClient.Builder().apiKey("b01a1c53a").apiSecret("bkvjh3JXwji3N5Mh").build());
//    Mockito.when(nexmoClient.getSmsClient()).thenReturn(new SmsClient(new HttpWrapper()));
       assertTrue("nexmo sms testcase.", nexmoSmsClient.sendSms(new SmsRequest("+919133259525")));
  }
}



