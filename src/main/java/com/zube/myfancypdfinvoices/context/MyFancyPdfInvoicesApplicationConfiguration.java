package com.zube.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zube.myfancypdfinvoices.ApplicationLauncher;
import com.zube.myfancypdfinvoices.service.InvoiceService;
import com.zube.myfancypdfinvoices.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
