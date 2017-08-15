package io.capdevila.poc.msarchitecture.hystrixdashboard

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream

@EnableDiscoveryClient
@EnableTurbineStream
@EnableHystrixDashboard
@SpringBootApplication
class HystrixdashboardApplication

fun main(args: Array<String>) {
    SpringApplication.run(HystrixdashboardApplication::class.java, *args)
}
