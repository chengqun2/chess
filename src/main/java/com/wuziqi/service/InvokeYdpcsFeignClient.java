package com.wuziqi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "invokeYdpcs", url = "http://localhost:8088")
public interface InvokeYdpcsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/index/t/{id}")
    Map getTest(@PathVariable("id") String id);
}
