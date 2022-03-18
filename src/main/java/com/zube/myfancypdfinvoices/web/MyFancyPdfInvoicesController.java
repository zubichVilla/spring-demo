package com.zube.myfancypdfinvoices.web;

import com.zube.myfancypdfinvoices.dto.InvoiceDto;
import com.zube.myfancypdfinvoices.model.Invoice;
import com.zube.myfancypdfinvoices.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyFancyPdfInvoicesController {

    private final InvoiceService invoiceService;

    public MyFancyPdfInvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "Hello world";
    }

    @GetMapping("/invoices")
    // @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> invoices (){
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody InvoiceDto invoiceDto){
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }

}
