package pl.piomin.microservices.customer.intercomm;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.piomin.microservices.customer.model.Document;

@FeignClient(value = "document-service", fallback = DocumentFallback.class)
public interface DocumentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/documents/customer/{customerId}")
    List<Document> getDocuments(@PathVariable("customerId") Integer customerId);

}
