package bank;

import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BankStatementPrinter {
    @Autowired
    AccountService accountService;

    @Scheduled(cron = "0/20 * * * * *")
    public void printBankStatement() {
       accountService.allAccountDetails();
    }

}
