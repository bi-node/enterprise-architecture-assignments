package com.binode.part_b;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class Logger implements ILogger {

	@Override
	public void log(String logstring) {
		System.out.println("Logging " + LocalDateTime.now() + " " + logstring);
	}
}
