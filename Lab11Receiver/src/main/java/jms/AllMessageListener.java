package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AllMessageListener {
 
    @JmsListener(destination = "testQueue")
    public void receivePersonMessage(final String personAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person = objectMapper.readValue(personAsString, Person.class);
            System.out.println("JMS receiver received message:" + person.getFirstName()+" "+person.getLastName());

        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + personAsString+" to a Person object");
        }
     }

    int calculatedValue=0;

    @JmsListener(destination = "calcQueue")
    public void receiveCalculatorMessage(String message) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            Calculator calculator = objectMapper.readValue(message, Calculator.class);
            int x=calculatedValue;
            switch (calculator.getOperator()) {
                case "+":
                    calculatedValue+=calculator.getValue();
                    break;
                case "-":
                    calculatedValue-=calculator.getValue();
                    break;
                case "*":
                    calculatedValue*=calculator.getValue();
                    break;
                case "/":
                    calculatedValue/=calculator.getValue();
                    break;
                default:
                    throw new RuntimeException("Invalid operator");
            }

            System.out.println(x+calculator.getOperator()+
                    calculator.getValue()+"=="+calculatedValue);


        } catch (Exception e) {
            System.out.println("Cannot do the calculation ");
        }
    }
}

