package io.capdevila.poc.msarchitecture.eurekaserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class EurekaserverApplication

fun main(args: Array<String>) {
    SpringApplication.run(EurekaserverApplication::class.java, *args)
}
