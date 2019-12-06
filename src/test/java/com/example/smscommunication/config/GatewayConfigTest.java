package com.example.smscommunication.config;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
class GatewayConfigTest {

    @InjectMocks
    private GatewayConfig gatewayConfig;

    @Value("${client.gateway.nexmo-api-key : b01ac53a }")
    public String Apikey;
    @Value("${client.gateway.nexmo-api-secret : bkvjh3JXwji3N5Mh }")
    public String Apisecretkey;

    @Value("${client.gateway.twilio-account-sid : AC41026a94f82d45e41375ebc637319183}")
    public String apikey;
    @Value("${client.gateway.twilio-auth-token : 10e2db1b46afd7d4e2a036db8673d0a4}")
    public  String authtoken;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(gatewayConfig,"b01ac53a","bkvjh3JXwji3N5Mh");
        ReflectionTestUtils.setField(gatewayConfig,"apikey","AC41026a94f82d45e41375ebc637319183");
        ReflectionTestUtils.setField(gatewayConfig,"authkey","10e2db1b46afd7d4e2a036db8673d0a4");
    }

    @Test
    public void Nexmo_api_build() throws NexmoClientException {
        GatewayConfig gatewayConfig = mock(GatewayConfig.class);
        Mockito.when(gatewayConfig.nexmoClient("b01a1c53a","bkvjh3JXwji3N5Mh")).thenReturn(new NexmoClient.Builder().apiKey("b01a1c53a").apiSecret("bkvjh3JXwji3N5Mh").build());
    }
    @Test
    public void Twilio_api_build() throws TwilioException{
        GatewayConfig gatewayConfig = mock(GatewayConfig.class);
        Twilio.init("AC41026a94f82d45e41375ebc637319183","10e2db1b46afd7d4e2a036db8673d0a4");
        //It's not necessary
//        new GatewayConfig ("AC41026a94f82d45e41375ebc637319183","10e2db1b46afd7d4e2a036db8673d0a4");
    }

}