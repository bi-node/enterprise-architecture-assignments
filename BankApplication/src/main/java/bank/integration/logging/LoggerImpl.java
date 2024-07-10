package bank.integration.logging;

import org.springframework.stereotype.Component;

@Component
public class LoggerImpl implements LoggerDemo {

	public void log(String logString) {
		java.util.logging.Logger.getLogger("BankLogger").info(logString);
	}

}
