package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSender implements IEmailSender {

	private final String outgoingMailServer = "smtp.acme.com";
	@Autowired
	private ILogger logger;

	public EmailSender(ILogger logger) {
		this.logger = logger;
	}

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}

	@Override
	public void sendEmail(String email, String message) {
		System.out.println("EmailSender: sending '" + message + "' to " + email);
		logger.log("Email is sent: message= " + message + " , emailaddress =" + email);
	}
}
