package pl.piomin.microservices.document.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public DocumentNotFoundException(String accountNumber) {
        super("No such account: " + accountNumber);
    }

}
