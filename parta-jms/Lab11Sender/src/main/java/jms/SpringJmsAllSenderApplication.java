package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
@EnableJms
public class SpringJmsAllSenderApplication implements CommandLineRunner {
	@Autowired
	JmsTemplate jmsTemplate;


	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJmsAllSenderApplication.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person("Frank", "Brown");
		//convert person to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String personAsString = objectMapper.writeValueAsString(person);

		System.out.println("Sending a JMS message:" + personAsString);
		jmsTemplate.convertAndSend("testQueue",personAsString);

		//For Calculator
		Calculator cal1=new Calculator(4,"+");
		Calculator cal2=new Calculator(10,"*");
		Calculator cal3=new Calculator(2,"9");

		String cal1AsString=objectMapper.writeValueAsString(cal1);
		String cal2AsString=objectMapper.writeValueAsString(cal2);
		String cal3AsString=objectMapper.writeValueAsString(cal3);

		System.out.println("Sending Calculator message:" + cal1AsString);
		jmsTemplate.convertAndSend("calcQueue",cal1AsString);

		System.out.println("Sending Calculator message:" + cal2AsString);
		jmsTemplate.convertAndSend("calcQueue",cal2AsString);

		System.out.println("Sending Calculator message:" + cal3AsString);
		jmsTemplate.convertAndSend("calcQueue",cal3AsString);

	}

}
