package com.example.smscommunication.service;

import com.example.smscommunication.dto.SmsRequest;
import com.example.smscommunication.gatewayclient.CommunicationGatewayFactory;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

class OTPCommunicationServiceImplTest
{
    @InjectMocks
    private  OTPCommunicationServiceImpl otpCommunicationServiceImpl;

    @Mock
    private CommunicationGatewayFactory communicationGatewayFactory;

    @Value("${number-of-attempts.sms : 3}")
    private int numberofattempts;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

        @Test    public void otpCommunicationService() {
        //  mock = Mockito.mock(OTPCommunicationServiceImpl.class);
            //  Mockito.when((mock).selectGatewayAndSendOtp(new SmsRequest("+919133259525")));//
            //  try {//            mock.selectGatewayAndSendOtp(new SmsRequest("+919133259525"));//
            //  }
            //  catch (Exception e) {
            //            e.printStackTrace();
            //        }       // Mockito.when()
            //  Mockito.when(nexmoClient.getSmsClient()).thenReturn(new SmsClient(new HttpWrapper()));
            // assertEquals("vvvvv", otpCommunicationService.selectGatewayAndSendOtp(new SmsRequest("+919133259525")));


             OTPCommunicationServiceImpl otpCommunicationService = new OTPCommunicationServiceImpl();
             Mockito.when(otpCommunicationService.selectGatewayAndSendOtp(new SmsRequest("+919133259525" )));
             }

            }