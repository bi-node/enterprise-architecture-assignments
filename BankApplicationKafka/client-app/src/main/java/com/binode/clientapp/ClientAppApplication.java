package com.binode.clientapp;

import com.binode.clientapp.dto.CreateAccountRequest;
import com.binode.clientapp.dto.DepositRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
@RequiredArgsConstructor
@EnableKafka
@SpringBootApplication
public class ClientAppApplication implements CommandLineRunner {
    @Autowired
    private final Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(ClientAppApplication.class, args);
    }

    @Override

    public void run(String... args) throws Exception {
        //sending deposit request
        try {
            System.out.println("Sending deposit request");
            sender.sendDeposit("depositMsg", new DepositRequest(1263862, 9874));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //sending creating account
        try {
            System.out.println("Creating Account request");
            sender.createAccount("createAccountMsg", new CreateAccountRequest(8888, "Binod Rasaili"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //sending withdraw
        try {
            System.out.println("Sending withdraw request");
            sender.withdraw("withdrawMsg", new DepositRequest(1263862, 1234));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
