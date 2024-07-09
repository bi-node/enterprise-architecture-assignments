package bank.integration.kafka;

import bank.kafkaDtos.CreateAccountRequest;
import bank.kafkaDtos.DepositRequest;
import bank.kafkaDtos.WithdrawRequest;
import bank.service.AccountService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerBox {
    @Autowired
    AccountService accountService;

    @KafkaListener(topics = {"depositMsg"})
    public void deposit(@Payload String request)
    {
        try {


            ObjectMapper mapper = new ObjectMapper();
            DepositRequest depositRequest = mapper.readValue(request, DepositRequest.class);
            long accountNumber = depositRequest.getAccountNumber();
            double amount = depositRequest.getAmount();
            System.out.println("Depositing amount to the account "+accountNumber);
            accountService.deposit(accountNumber, amount);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @KafkaListener(topics = {"createAccountMsg"})
    public void accountCreation(@Payload String request)
    {
        try {


            ObjectMapper mapper = new ObjectMapper();
            CreateAccountRequest createRequest = mapper.readValue(request, CreateAccountRequest.class);
            long accountNumber = createRequest.getAccountNumber();
            String name = createRequest.getCustomerName();
            System.out.println("Creating account , customer name  "+accountNumber+" "+name);
            accountService.createAccount(accountNumber, name);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @KafkaListener(topics = {"withdrawMsg"})
    public void withdrawAmount(@Payload String request)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            WithdrawRequest withdrawRequest = mapper.readValue(request, WithdrawRequest.class);
            long accountNumber = withdrawRequest.getAccountNumber();
            double amount = withdrawRequest.getAmount();
            System.out.println("withdrawing amount  "+accountNumber+" "+amount);
            accountService.withdraw(accountNumber, amount);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
