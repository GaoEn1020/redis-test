package com.piesat.redistest.controller;

import com.piesat.redistest.service.TestRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private TestRedisService redisService;

    @GetMapping("/test")
    public String getRedisInfo(){
        return redisService.getTestInfoFromRedis();
    }
}
