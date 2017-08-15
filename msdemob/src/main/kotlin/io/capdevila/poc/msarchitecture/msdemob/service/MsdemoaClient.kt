package io.capdevila.poc.msarchitecture.msdemob.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import io.capdevila.poc.msarchitecture.dto.lib.HelloRequestDTO
import io.capdevila.poc.msarchitecture.dto.lib.HelloResponseDTO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@RefreshScope
//@ConfigurationProperties("mspoc.msdemoa") doesn't work with @RefreshScope, @Value is used instead
open class MsdemoaClient {

    companion object {
        private val logger = LoggerFactory.getLogger(MsdemoaClient::class.java)!!
    }

    @Value("\${mspoc.msdemoa.helloRequestUrl}")
    private val helloRequestUrl: String? = null

    @Autowired
    private val restTemplate: RestTemplate? = null

    @HystrixCommand(fallbackMethod = "retrieveFallbackHelloRequest")
    fun helloRequest(helloRequestDTO: HelloRequestDTO): HelloResponseDTO {
        logger.debug("name: {}", helloRequestDTO.name)
        logger.debug("helloRequestUrl: {}", helloRequestUrl)
        logger.debug("restTemplate: {}", restTemplate)
        var helloResponseDTO: HelloResponseDTO = HelloResponseDTO("Error")
        try {
            helloResponseDTO = restTemplate!!.postForObject(helloRequestUrl, helloRequestDTO, HelloResponseDTO::class.java)
        } catch (e: Exception) {
            logger.error("Exception", e)
            throw e
        }
        return helloResponseDTO
    }

    fun retrieveFallbackHelloRequest(helloRequestDTO: HelloRequestDTO): HelloResponseDTO {
        logger.debug("error in helloRequest")
        return HelloResponseDTO("Error in helloRequest to msdemoa. This is a fallback message.")
    }

}
