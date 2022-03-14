package com.zube.myfancypdfinvoices.service;

import com.zube.myfancypdfinvoices.model.Invoice;
import com.zube.myfancypdfinvoices.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class InvoiceService {

    @PostConstruct
    public void init(){
        System.out.println("Fetching PDF templates from S3 ....");
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("Deleting downloaded templates ... ");
    }

    private final UserService userService;

    List<Invoice> invoices = new CopyOnWriteArrayList<>(); //

    public List<Invoice> findAll(){
        return invoices;
    }

    public InvoiceService(UserService userService){
        this.userService = userService;
    }

    public Invoice create(String userId, Integer amount){

        User user = userService.findById(userId);

        if(user == null){
            throw new IllegalStateException();
        }

        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);

        return invoice;
    }
}
