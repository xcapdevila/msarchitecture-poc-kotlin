package io.capdevila.poc.msarchitecture.msdemoa

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
class MsdemoaApplication

fun main(args: Array<String>) {
    SpringApplication.run(MsdemoaApplication::class.java, *args)
}
