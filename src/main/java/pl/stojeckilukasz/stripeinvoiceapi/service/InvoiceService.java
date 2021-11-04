package pl.stojeckilukasz.stripeinvoiceapi.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Service
public class InvoiceService {

    @Value("${API_KEY}")
    private String secretKey;

    @PostConstruct
    public void initialize() {
        Stripe.apiKey = secretKey;
    }

    public Invoice getInvoice(String id) throws StripeException {
        Invoice invoice = Invoice.retrieve(id);

        return invoice;
    }

    public Invoice createInvoice(String customerId) throws StripeException {

        createInvoiceItem(customerId);

        Map<String, Object> params1 = new HashMap<>();
        params1.put("customer", customerId);

        Invoice invoice = Invoice.create(params1);
        return invoice;
    }

    public void createInvoiceItem(String customerId) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("customer", customerId);
        params.put("amount", 100);
        params.put("currency", "usd");

        InvoiceItem.create(params);
    }
}
