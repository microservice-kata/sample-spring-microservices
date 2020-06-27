package pl.piomin.microservices.document.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.microservices.document.model.Document;

@RestController
public class Api {

    @Value("${PORT}")
    int port;

    @Value("${WAIT}")
    int wait;

    private List<Document> documents;

    protected Logger logger = Logger.getLogger(Api.class.getName());

    public Api() {
        documents = new ArrayList<>();
        documents.add(new Document(1, 1, "111111"));
        documents.add(new Document(2, 2, "222222"));
        documents.add(new Document(3, 3, "333333"));
        documents.add(new Document(4, 4, "444444"));
        documents.add(new Document(5, 1, "555555"));
        documents.add(new Document(6, 2, "666666"));
        documents.add(new Document(7, 2, "777777"));
    }

    @RequestMapping("/documents/{number}")
    public Document findByNumber(@PathVariable("number") String number) {
        logger.info(String.format("Document.findByNumber(%s)", number));
        return documents.stream().filter(it -> it.getContent().equals(number)).findFirst().get();
    }

    @RequestMapping("/documents/customer/{customer}")
    public List<Document> findByCustomer(@PathVariable("customer") Integer customerId) {
        logger.info(String.format("Document.findByCustomer(%s) wait: {%s}", customerId, wait));
        if (port % 2 == 0) {
            try {
                logger.info(String
                    .format(" current thread name waited: {%s}", Thread.currentThread().getName()));
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Document> collect = documents.stream()
            .filter(it -> it.getCustomerId().intValue() == customerId.intValue())
            .collect(Collectors.toList());
        logger.info(String.format("Found Documents size {%s}", collect.size()));
        return collect;
    }

    @RequestMapping("/documents")
    public List<Document> findAll() {
        logger.info("Document.findAll()");
        return documents;
    }

}
