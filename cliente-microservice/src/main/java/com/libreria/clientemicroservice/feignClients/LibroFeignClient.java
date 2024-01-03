package com.libreria.clientemicroservice.feignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "libro-microservice", url = "http://localhost:8001")

public interface LibroFeignClient {


}
