package pl.stojeckilukasz.stripeinvoiceapi.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.stojeckilukasz.stripeinvoiceapi.service.InvoiceService;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/invoices/{id}")
    public String getInvoice(@PathVariable String id) throws StripeException {
        return invoiceService.getInvoice(id).toJson();
    }

    @PostMapping("/{customerId}")
    public String createInvoice(@PathVariable String customerId) throws StripeException {
        Invoice invoice = invoiceService.createInvoice(customerId);
        return invoice.getId();
    }


}
