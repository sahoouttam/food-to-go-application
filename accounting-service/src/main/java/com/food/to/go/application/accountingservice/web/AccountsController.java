package com.food.to.go.application.accountingservice.web;

import com.food.to.go.application.accountingservice.domain.Account;
import com.food.to.go.application.accountingservice.domain.AccountCommand;
import io.eventuate.EntityNotFoundException;
import io.eventuate.sync.AggregateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountsController {

    private AggregateRepository<Account, AccountCommand> accountRepository;

    @RequestMapping(path = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<GetAccountResponse> getAccount(@PathVariable String accountId) {
        try {
            return new ResponseEntity<>(new GetAccountResponse(accountId), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
