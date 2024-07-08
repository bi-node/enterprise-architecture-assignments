package com.binode.taxservice;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class BankApplicationReceiver {
    @JmsListener(destination = "forTaxOffice")
    public void receiveBankInfo(final String bankMessage)
    {
        try {
            System.out.println(bankMessage);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
