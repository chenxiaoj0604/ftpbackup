package com.backup.config;

import com.backup.webservice.WebService1;
import com.backup.webservice.WebService1Soap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by duanxun on 2018-08-27.
 */
@Configuration
public class WebserviceConfig {

    @Bean
    public WebService1Soap webService1Soap(){
        WebService1 webService1 = new WebService1();
        return webService1.getWebService1Soap();
    }



}
