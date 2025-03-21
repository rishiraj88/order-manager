package om.order.stub;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

// stub for remote service
public class InventoryClient {
    public static void stubInventoryCheck(String skuCode, Integer quantityForQuery) {
        stubFor(get(urlEqualTo("/api/v1/inventory?skuCode=" + skuCode + "&quantityForQuery=" + quantityForQuery)).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("true")));
    }
}
