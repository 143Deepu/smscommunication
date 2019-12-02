package com.example.smscommunication.gatewayclient;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommunicationGatewayFactory {

  @Resource private List<SmsClient> smsClientList;
  private Map<Integer, SmsClient> smsClientMap;

  /**
   * This method is used to store all the client implementation class in HashMap for communication
   * with gateway based on priority
   */
  @PostConstruct
  private void init() {
    smsClientMap = new HashMap<>();
    smsClientList.forEach(SmsClient -> smsClientMap.put(SmsClient.gatewayClient(), SmsClient));
  }

  /** @return a set view of the mappings contained in this map */
  public Map<Integer, SmsClient> getGatewayMap() {
    return smsClientMap;
  }
}
