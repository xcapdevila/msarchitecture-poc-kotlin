package io.capdevila.poc.msarchitecture.msdemob

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import io.capdevila.poc.msarchitecture.dto.lib.HelloRequestDTO
import io.capdevila.poc.msarchitecture.dto.lib.HelloResponseDTO
import io.capdevila.poc.msarchitecture.msdemob.service.MsdemoaClient
import io.capdevila.poc.msarchitecture.msdemob.service.MsdemoaFeignClient
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/poc")
class MsdemobController {

    companion object {
        private val logger = LoggerFactory.getLogger(MsdemobController::class.java)!!
    }

    @Autowired
    private val msdemoaClient: MsdemoaClient? = null

    @Autowired
    private val msdemoaFeignClient: MsdemoaFeignClient? = null

    @HystrixCommand(fallbackMethod = "retrieveFallbackBye")
    @GetMapping("/bye/{name}")
    fun bye(@PathVariable(name = "name") name: String, request: HttpServletRequest): String {
        logger.debug("name: {}", name)
        return "Bye $name!! This a response from ${request.requestURL}"
    }

    fun retrieveFallbackBye(name: String, request: HttpServletRequest): String {
        logger.debug("error in bye")
        return "Error in bye for name: $name. This is a fallback message from ${request.requestURL}"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "msdemoaHelloRequest")
    fun helloRequestMsdemoa(@RequestBody helloRequestDTO: HelloRequestDTO): HelloResponseDTO {
        logger.debug("--> request received - name: {}", helloRequestDTO.name)
        return msdemoaClient!!.helloRequest(helloRequestDTO)
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "msdemoaHelloRequestFeign")
    fun helloRequestMsdemoaFeign(@RequestBody helloRequestDTO: HelloRequestDTO): HelloResponseDTO {
        logger.debug("--> request received - name: {}", helloRequestDTO.name)
        return msdemoaFeignClient!!.helloRequest(helloRequestDTO)
    }

}