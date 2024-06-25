package com.binode.part_b;

public interface IEmailSender {
	void sendEmail(String email, String message);
	String getOutgoingMailServer();
}