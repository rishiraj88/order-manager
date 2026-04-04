package om.order

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<OrderApplication>().with(TestcontainersConfiguration::class).run(*args)
}
