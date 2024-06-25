package com.binode.part_b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailSender implements IEmailSender {

	@Value("${smtpserver}")
	String smtpServer;

	@Autowired
	private ILogger logger;

	public EmailSender(ILogger logger) {
		this.logger = logger;
	}

	public String getOutgoingMailServer() {
		return smtpServer;
	}

	@Override
	public void sendEmail(String email, String message) {
		System.out.println("EmailSender: sending '" + message + "' to " + email);
		logger.log("Email is sent: message= " + message + " , emailaddress =" + email);
		System.out.println("Sending Email through smtp server: " + smtpServer);
	}
}
