package bank.integration.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class JMSSenderImpl implements JMSSender{
	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendJMSMessage (String text){
		System.out.println("JMSSender: sending JMS message ="+text);
		jmsTemplate.convertAndSend("forTaxOffice", text);
	}

}
