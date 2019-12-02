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
   *
   */
  @PostConstruct
  private void init() {
    smsClientMap = new HashMap<>();
    smsClientList.forEach(SmsClient -> smsClientMap.put(SmsClient.GatewayClient(), SmsClient));
  }

  /**
   *
   * @return
   */
  public Map<Integer, SmsClient> getGatewayMap() {
    return smsClientMap;
  }
}
