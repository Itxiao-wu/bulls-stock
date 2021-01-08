package com.itcast.nacos.discovery.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosDiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryServerApplication.class,args);
    }
    /**
     * 提供查询股票价格的接口
     * @param name
     * @return
     */
    @RequestMapping("/getStockPrice")
    public String getStockPrice(@RequestParam(defaultValue = "中国平安")String name){
        return "股票名称： "+ name +", 股票价格：" + (new Random().nextInt(100-20)+20);
    }
}
