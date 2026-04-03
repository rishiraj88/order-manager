package om.inventory

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<InventoryApplication>().with(TestcontainersConfiguration::class).run(*args)
}
