package com.github.luyiisme.ddddemo;

import com.github.luyiisme.ddddemo.order.interfaces.config.OrderModuleConfig;
import com.github.luyiisme.ddddemo.user.interfaces.config.UserModuleConfig;
import com.github.luyiisme.ddddemo.user.interfaces.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

//启动需要的 bizModules
@Import({UserModuleConfig.class, OrderModuleConfig.class})
@SpringBootApplication
public class BootStrap {
    @Autowired
    UserFacade userFacade;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("====>");
        userFacade.getAllUsers().forEach(x -> System.out.println(x));
    }


}
