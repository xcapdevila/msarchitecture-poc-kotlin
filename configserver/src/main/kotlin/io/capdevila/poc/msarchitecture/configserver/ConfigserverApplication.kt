package io.capdevila.poc.msarchitecture.configserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.config.server.EnableConfigServer

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
class ConfigserverApplication

fun main(args: Array<String>) {
    SpringApplication.run(ConfigserverApplication::class.java, *args)
}
