package com.bsoftware.walkingmoney;

import com.bsoftware.walkingmoney.config.property.SpringApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(SpringApiProperty.class)
@SpringBootApplication
public class WalkingMoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalkingMoneyApplication.class, args);
    }

}
