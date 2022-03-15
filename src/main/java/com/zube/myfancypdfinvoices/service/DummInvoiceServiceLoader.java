package com.zube.myfancypdfinvoices.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("dev")
public class DummInvoiceServiceLoader {

    private final InvoiceService invoiceService;


    public DummInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void setup(){
        System.out.println("Creating dev invoices...");
        invoiceService.create("someUserId", 50);
        invoiceService.create("someOthesUserId", 100);

    }
}
