package pl.piomin.microservices.customer.intercomm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import pl.piomin.microservices.customer.model.Account;

@Component
public class AccountFallback implements AccountClient {

    protected Logger logger = Logger.getLogger(AccountFallback.class.getName());

    @Override
    public List<Account> getAccounts(Integer customerId) {
        logger.info(String.format("Account.getAccounts(%s) fallback", customerId));
        List<Account> acc = new ArrayList<Account>();
        return acc;
    }

}
