package om.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.CrossOrigin

@SpringBootApplication// @CrossOrigin
class OrderApplication

fun main(args: Array<String>) {
	runApplication<OrderApplication>(*args)
}
