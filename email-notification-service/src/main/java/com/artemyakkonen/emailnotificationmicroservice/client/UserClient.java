package com.artemyakkonen.emailnotificationmicroservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/users/checkemail")
    String checkEmail(@RequestBody String email);
}
