package bank.jms;

import org.springframework.stereotype.Component;


@Component
public interface IJMSSender {
	public void sendJMSMessage (String text);
}
