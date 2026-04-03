package om.gateway

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<GatewayApplication>().with(TestcontainersConfiguration::class).run(*args)
}
