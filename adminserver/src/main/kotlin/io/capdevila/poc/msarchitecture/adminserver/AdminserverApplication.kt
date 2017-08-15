package io.capdevila.poc.msarchitecture.adminserver

import de.codecentric.boot.admin.config.EnableAdminServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
class AdminserverApplication

fun main(args: Array<String>) {
    SpringApplication.run(AdminserverApplication::class.java, *args)
}
