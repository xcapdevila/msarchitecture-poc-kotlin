package io.capdevila.poc.msarchitecture.msdemob.service

import io.capdevila.poc.msarchitecture.dto.lib.HelloRequestDTO
import io.capdevila.poc.msarchitecture.dto.lib.HelloResponseDTO
import org.slf4j.LoggerFactory
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping


@FeignClient(name = "msdemoa", fallback = MsdemoaFeignClientFallback::class)
interface MsdemoaFeignClient {

    @PostMapping("/poc/helloRequest")
    fun helloRequest(helloRequestDTO: HelloRequestDTO): HelloResponseDTO

}

@Component
open class MsdemoaFeignClientFallback : MsdemoaFeignClient {

    companion object {
        private val logger = LoggerFactory.getLogger(MsdemoaFeignClientFallback::class.java)!!
    }

    override fun helloRequest(helloRequestDTO: HelloRequestDTO): HelloResponseDTO {
        logger.debug("error in helloRequest")
        return HelloResponseDTO("Error in helloRequest to msdemoa with a Feign client. This is a fallback message.")
    }
}