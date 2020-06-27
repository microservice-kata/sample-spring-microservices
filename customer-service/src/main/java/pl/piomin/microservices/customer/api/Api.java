package pl.piomin.microservices.customer.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.microservices.customer.intercomm.AccountClient;
import pl.piomin.microservices.customer.intercomm.DocumentClient;
import pl.piomin.microservices.customer.model.Account;
import pl.piomin.microservices.customer.model.Customer;
import pl.piomin.microservices.customer.model.CustomerType;
import pl.piomin.microservices.customer.model.Document;

@RestController
public class Api {

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private DocumentClient documentClient;

    protected Logger logger = Logger.getLogger(Api.class.getName());

    private List<Customer> customers;

    public Api() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "12345", "Adam Kowalski", CustomerType.INDIVIDUAL));
        customers.add(new Customer(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
        customers.add(new Customer(3, "12347", "Paweł Michalski", CustomerType.INDIVIDUAL));
        customers.add(new Customer(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));
    }

    @RequestMapping("/customers/pesel/{pesel}")
    public Customer findByPesel(@PathVariable("pesel") String pesel) {
        logger.info(String.format("Customer.findByPesel(%s)", pesel));
        return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();
    }

    @RequestMapping("/customers")
    public List<Customer> findAll() {
        logger.info("Customer.findAll()");
        return customers;
    }

    @RequestMapping("/customers/{id}")
    public Customer findById(@PathVariable("id") Integer id, @RequestParam("type") String type) {
        logger.info(String.format("Customer.findById(%s), type(%s)", id, type));
        Customer customer = customers.stream().filter(it -> it.getId().intValue() == id.intValue())
            .findFirst().get();
        if ("ACCOUNT".equals(type)) {
            List<Account> accounts = accountClient.getAccounts(id);
            customer.setAccounts(accounts);
            logger.info(String.format("Customer account size %s", customer.getAccounts().size()));
            return customer;
        }
        if ("DOCUMENT".equals(type)) {
            List<Document> documents = documentClient.getDocuments(id);
            customer.setDocuments(documents);
            logger.info(String.format("Customer document size %s", customer.getDocuments().size()));
            return customer;
        }
        List<Account> accounts = accountClient.getAccounts(id);
        List<Document> documents = documentClient.getDocuments(id);
        customer.setAccounts(accounts);
        customer.setDocuments(documents);
        logger.info(String.format("Customer account size(%s), document size(%s)", accounts.size(),
            documents.size()));

        return customer;
    }
}
