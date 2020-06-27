package pl.piomin.microservices.customer.intercomm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import pl.piomin.microservices.customer.model.Document;

@Component
public class DocumentFallback implements DocumentClient {

    protected Logger logger = Logger.getLogger(DocumentFallback.class.getName());

    @Override
    public List<Document> getDocuments(Integer customerId) {
        logger.info(String.format("Document.getDocuments(%s) fallback", customerId));
        List<Document> acc = new ArrayList<Document>();
        return acc;
    }
}
