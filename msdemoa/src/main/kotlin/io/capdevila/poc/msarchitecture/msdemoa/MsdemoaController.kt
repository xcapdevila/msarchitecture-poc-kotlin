package io.capdevila.poc.msarchitecture.msdemoa

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import io.capdevila.poc.msarchitecture.dto.lib.HelloRequestDTO
import io.capdevila.poc.msarchitecture.dto.lib.HelloResponseDTO
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/poc")
class MsdemoaController {

    companion object {
        private val logger = LoggerFactory.getLogger(MsdemoaController::class.java)!!
    }

    @HystrixCommand(fallbackMethod = "retrieveFallbackHello")
    @GetMapping("/hello/{name}")
    fun hello(@PathVariable(name = "name") name: String, request: HttpServletRequest): String {
        logger.debug("name: {}", name)
        return "Hi $name!! This a response from ${request.requestURL}"
    }

    fun retrieveFallbackHello(name: String, request: HttpServletRequest): String {
        logger.debug("error in hello")
        return "Error in hello for name: $name. This is a fallback message from ${request.requestURL}"
    }

    @HystrixCommand(fallbackMethod = "retrieveFallbackHelloRequest")
    @PostMapping("/helloRequest")
    fun helloRequest(@RequestBody helloRequest: HelloRequestDTO, request: HttpServletRequest): HelloResponseDTO {
        logger.debug("request received")
        logger.debug("name: {}", helloRequest.name)
        return HelloResponseDTO("Hi ${helloRequest.name}!! This is a response from ${request.requestURL}")
    }

    fun retrieveFallbackHelloRequest(helloRequestDTO: HelloRequestDTO, request: HttpServletRequest): HelloResponseDTO {
        logger.debug("error in helloRequest")
        return HelloResponseDTO("Error in helloRequest. This is a fallback message from ${request.requestURL}")
    }

}