package om.order

import io.restassured.RestAssured
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.testcontainers.containers.MySQLContainer

@Import(TestcontainersConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApplicationTests() {
    @LocalServerPort
    lateinit var applicationPort: Integer

    @Autowired
    lateinit var mysqlContainer: MySQLContainer<Nothing>

    init {
        mysqlContainer = MySQLContainer<Nothing>("mysql:8.3.0")
        mysqlContainer.start()
    }

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = applicationPort as Int
    }

    @Test
    fun shouldCreateOneNewOrder_1() {
        var requestBody = """
                {
                "orderNumber":"DE3343INT432342342222", 
                "skuCode":"DE342GES34233111", 
                "itemRate":130.20,
                "itemQuantity":3
                }
                """.trimIndent()
        RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .`when`()
            .post("/api/v2/orders")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            //testing the values of response
            .body("id", Matchers.notNullValue())
            .body("orderNumber", Matchers.equalTo("DE3343INT432342342222"))
            .body("skuCode", Matchers.equalTo("DE342GES34233111"))
            .body("itemRate", Matchers.equalTo(130.20))
            .body("itemQuantity", Matchers.equalTo(3))
    }

    // TODO paramaterize the tests
    @Test
    fun shouldCreateOneNewOrder_2() {
        var requestBody = """
                {
                "orderNumber":"DE3343INT432342342223", 
                "skuCode":"DE342GES34233112", 
                "itemRate":280.40,
                "itemQuantity":1
                }
                """.trimIndent()
        RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .`when`()
            .post("/api/v2/orders")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            //testing the values of response
            .body("id", Matchers.notNullValue())
            .body("orderNumber", Matchers.equalTo("DE3343INT432342342223"))
            .body("skuCode", Matchers.equalTo("DE342GES34233112"))
            .body("itemRate", Matchers.equalTo(280.40f))
            .body("itemQuantity", Matchers.equalTo(1))
    }


    @Test
    fun shouldCreateOneNewOrder_3() {
        var requestBody = """
                {
                "orderNumber":"DE3343INT432342342224", 
                "skuCode":"DE342GES34233113", 
                "itemRate":180.40,
                "itemQuantity":2
                }
                """.trimIndent()
        RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .`when`()
            .post("/api/v2/orders")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            //testing the values of response
            .body("id", Matchers.notNullValue())
            .body("orderNumber", Matchers.equalTo("DE3343INT432342342224"))
            .body("skuCode", Matchers.equalTo("DE342GES34233113"))
            .body("itemRate", Matchers.equalTo(180.40f))
            .body("itemQuantity", Matchers.equalTo(2))
    }

    //TODO
    @Test
    fun shouldReturnAllOrders() {
    }

    @Test
    fun shouldReturnOrdersByUserEmail() {
    }

    @Test
    fun shouldReturnOrdersByUserName() {
    }

    @Test
    fun shouldReturnOrderByOrderNumber() {
    }
}
