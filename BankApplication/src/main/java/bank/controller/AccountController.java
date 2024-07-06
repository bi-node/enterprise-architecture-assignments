package bank.controller;

import bank.domain.Account;
import bank.dto.AccountDto;
import bank.service.AccountService;
import bank.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountServiceImpl accountServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccounts(@PathVariable long id) {
        return new ResponseEntity<>(accountService.getAccount(id),  HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<AccountDto>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestParam("acn") Long id, @RequestParam("cus") String customerName) {
        return new ResponseEntity<>(accountService.createAccount(id,customerName), HttpStatus.CREATED);
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam("acn") Long id, @RequestParam("amount") double amount) {
        accountService.deposit(id, amount);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam("acn") Long id, @RequestParam("amount") double amount) throws Exception {
        accountService.withdraw(id,amount);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }

    @PutMapping("/transfer-money")
    public ResponseEntity<String> transferMoney(@RequestParam("from") long fromacnId,  @RequestParam("to") long toacnId, @RequestParam("amount") double amount, @RequestParam("desc")String description) {
        accountService.transferFunds(fromacnId,toacnId,amount,description);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }



}
