package om.product

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ProductApplication>().with(TestcontainersConfiguration::class).run(*args)
}
