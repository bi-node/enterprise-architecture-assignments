package bank.integration.kafka;

import bank.kafkaDtos.DepositRequest;
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
}
