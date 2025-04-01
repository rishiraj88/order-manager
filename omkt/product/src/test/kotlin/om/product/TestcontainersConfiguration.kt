package om.product

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.kafka.KafkaContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	fun kafkaContainer(): KafkaContainer {
		return KafkaContainer(DockerImageName.parse("apache/kafka-native:latest"))
	}

	@Bean
	@ServiceConnection
	fun mongoDbContainer(): MongoDBContainer {
		return MongoDBContainer(DockerImageName.parse("mongo:latest"))
	}

	@Bean
	@ServiceConnection
	fun mysqlContainer(): MySQLContainer<*> {
		return MySQLContainer(DockerImageName.parse("mysql:latest"))
	}

}
