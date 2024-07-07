package client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CalculatorClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("generated"); // Replace with the package name of your generated classes
        return marshaller;
    }

    @Bean
    public CalculatorClient calculatorClient(Jaxb2Marshaller marshaller) {
        CalculatorClient client = new CalculatorClient();
        client.setDefaultUri("http://localhost:8080/ws"); // Set your service URI here
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
