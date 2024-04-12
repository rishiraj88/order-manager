package om.inventory;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryApplicationTests {

		@ServiceConnection
		static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
		@LocalServerPort
		private Integer port;

		@BeforeEach
		void setup() {
			RestAssured.baseURI = "http://localhost";
			RestAssured.port = port;
		}

		static {
			mySQLContainer.start();
		}

		@Test
		void shouldReadInventory() {
			var positiveResponse = RestAssured.given()
					.when()
					.get("api/inventory?skuCode=DE342GES34233125&quantityForQuery=20")
					.then()
					.log().all()
					.statusCode(200)
					.extract().response().as(Boolean.class);
			assertTrue(positiveResponse);

			var negativeResponse_01 = RestAssured.given()
					.when()
					.get("api/inventory?skuCode=DE342GES34233125&quantityForQuery=200")
					.then()
					.log().all()
					.statusCode(200)
					.extract().response().as(Boolean.class);
			assertFalse(negativeResponse_01);
			var negativeResponse_02 = RestAssured.given()
					.when()
					.get("api/inventory?skuCode=DE342GES34233123&quantityForQuery=20")
					.then()
					.log().all()
					.statusCode(200)
					.extract().response().as(Boolean.class);
			assertFalse(negativeResponse_02);
			var negativeResponse_03 = RestAssured.given()
					.when()
					.get("api/inventory?skuCode=PO342GES34233125&quantityForQuery=1")
					.then()
					.log().all()
					.statusCode(200)
					.extract().response().as(Boolean.class);
			assertFalse(negativeResponse_03);
		}

	@Test
	void contextLoads() {
	}

}
