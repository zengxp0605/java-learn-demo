package com.stan.swaggerdubbo.config;

import com.deepoove.swagger.dubbo.annotations.EnableDubboSwagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@Configuration
//@ImportResource({ "classpath:dubbo/*.xml" })
//@PropertySource("classpath:swagger-dubbo.properties")
@EnableDubboSwagger
public class DubboConfig {

//    @Bean
//    public ApplicationConfig applicationConfig() {
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName("dubbo-example-app");
//        applicationConfig.setOwner("Sayi");
//        return applicationConfig;
//    }

//    @Bean
//    public RegistryConfig registryConfig() {
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
//        registryConfig.setClient("curator");
//        return registryConfig;
//    }
//
//    @Bean
//    public ProtocolConfig protocolConfig() {
//        ProtocolConfig protocol = new ProtocolConfig();
//        protocol.setName("dubbo");
//        protocol.setPort(29880);
//        return protocol;
//    }

}
