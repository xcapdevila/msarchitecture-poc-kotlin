package io.capdevila.poc.msarchitecture.zuulserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableZuulProxy
@EnableWebSecurity
@SpringBootApplication
class ZuulserverApplication

fun main(args: Array<String>) {
    SpringApplication.run(ZuulserverApplication::class.java, *args)
}
