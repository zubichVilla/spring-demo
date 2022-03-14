package com.zube.myfancypdfinvoices.service;

import com.zube.myfancypdfinvoices.model.Invoice;
import com.zube.myfancypdfinvoices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class InvoiceService {

    @Autowired
    private UserService userService;

    List<Invoice> invoices = new CopyOnWriteArrayList<>(); //

    public List<Invoice> findAll(){
        return invoices;
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
