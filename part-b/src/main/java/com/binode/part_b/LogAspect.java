package com.binode.part_b;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LogAspect {

    @After("execution(* com.binode.part_b.EmailSender.sendEmail(..))")
    public void logAfterSendEmail(JoinPoint joinPoint) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("A log message: sendEmail method was called at " + currentTime);
        System.out.println("Method name : " + joinPoint.getSignature().getName());

        // Retrieve method arguments from the join point
        Object[] args = joinPoint.getArgs();
        String email = (String) args[0];
        String message = (String) args[1];

        // Log the information
        System.out.println(currentTime + " method=sendEmail address=" + email + " message=" + message);

        // Retrieve the target object
        EmailSender emailSender = (EmailSender) joinPoint.getTarget();
        String outgoingMailServer = emailSender.getOutgoingMailServer();

        // Log the information
        System.out.println("Outgoing mail server=" + outgoingMailServer);

    }
}
