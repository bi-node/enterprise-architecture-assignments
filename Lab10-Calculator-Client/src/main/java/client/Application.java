package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CalculatorClient calculatorClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("The sum of two numbers is: "+ calculatorClient.add(4, 5));
		System.out.println("The difference of two numbers is: "+ calculatorClient.difference(4, 5));
		System.out.println("The multiply of two numbers is: "+ calculatorClient.product(4, 5));

	}
}
