package om.order;

import io.restassured.RestAssured;
import om.order.config.Constants;
import om.order.stub.InventoryClient;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) // binds its actual value to wiremock.server.port for the remote service stubbed
class OrderApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    static {
        mySQLContainer.start();
    }

    @LocalServerPort
    private Integer port; // port for Order Application (this service)

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldSubmitOrder() {
        String submitOrderJson = """
                {
                "orderNumber":"DE3343INT432342342222",\s
                "itemSkuCode":"DE342GES34233111",\s
                "pricePerItem":130.20,
                "quantity":3,
                "userDetails":{           
                "emailAddress":"rishiraj@emails.co",
                "name":"Rishi Raj"
                }
                }""";

        // Mock the dependency on Inventory API for checking the available quantity in stock
        InventoryClient.stubInventoryCheck("DE342GES34233111",3);

        var responseBodyString = RestAssured.given()
                .contentType("application/json")
                .body(submitOrderJson)
                .when()
                .post("/api/v1/orders")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .body().asString();

        assertThat(responseBodyString, Matchers.is(Constants.newOrderPlaced));
    }

    @Test
    void shouldReturnAllOrders() {

    }

    @Test
    void shouldReturnOrdersByUserEmail() {

    }
    @Test
    void shouldReturnOrdersByUserEmail() {

    }
    @Test
    void shouldReturnOrderByOrderNumber() {

    }
    @Test
    void contextLoads() {
    }

}
