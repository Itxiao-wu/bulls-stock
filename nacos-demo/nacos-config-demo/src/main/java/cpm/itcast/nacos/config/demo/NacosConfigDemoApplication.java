package cpm.itcast.nacos.config.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class NacosConfigDemoApplication {
    @Value(value = "${stockName:中国平安}")
    private String StockName;

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigDemoApplication.class,args);
    }
    /**
     * 提供股票名称的访问接口
     * @return
     */
    @RequestMapping("/getStockName")
    public String getStockName(){
        return "股票名称： "+getStockName();
    }
}
