package com.wuziqi.service;

import com.wuziqi.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "invokeYdpcs", url = "http://localhost:8066", configuration = {FeignConfig.class})
public interface InvokeYdpcsFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/authenticate")
    Map getToken(@RequestBody Map map);

    @RequestMapping(method = RequestMethod.POST, value = "/api/test/{id}")
//    String test(@RequestHeader(name = "Authorization") String token, @PathVariable("id") int id);
    String test(@PathVariable("id") int id);

}
