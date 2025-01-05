package com.iamscjoshi.microservices.order.stubs;


import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubInventoryCall(String skuCode, Integer quantity) {
        if (quantity <= 100) {
            stubFor(get(WireMock.urlEqualTo("/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody("true")));
        } else {
            stubFor(get(urlEqualTo("/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody("false")));
        }
    }
}
