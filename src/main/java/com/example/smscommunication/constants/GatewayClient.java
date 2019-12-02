package com.example.smscommunication.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GatewayClient {
  /** Creating enum's from iterator purpose. */
  TWILIO(1, "Twilio"),
  NEXMO(2, "Nexmo");
  private final int code;
  private final String type;
  /** The Map() is used to particular Arrays function.identity value get into the GatewayClient */
  private static final Map<Integer, GatewayClient> GATEWAY_CLIENT_MAP =
      Arrays.stream(GatewayClient.values())
          .collect(Collectors.toMap(GatewayClient::getCode, Function.identity()));

  /**
   * Create a constructor from enum's parameters.
   *
   * @param code is a variable
   * @param type is a variable
   */
  GatewayClient(int code, String type) {
    this.code = code;
    this.type = type;
  }

  public int getCode() {
    return code;
  }

  public String getType() {
    return type;
  }

  /**
   * @param clientId take the value from OTPCommunicationServiceImpl.java. after check the if
   *     condition.
   * @return the condition is satisfied return the ClientID.
   */
  public static GatewayClient valueOf(final Integer clientId) {
    if (GATEWAY_CLIENT_MAP.containsKey(clientId)) {
      return GATEWAY_CLIENT_MAP.get(clientId);
    }

    throw new IllegalArgumentException("NO MATCHING CLIENT CORRESPONDING TO [ " + clientId + "]");
  }
}
