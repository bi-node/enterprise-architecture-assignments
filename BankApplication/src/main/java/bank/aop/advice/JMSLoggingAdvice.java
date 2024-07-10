package bank.aop.advice;

import bank.integration.logging.LoggerDemo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class JMSLoggingAdvice {
    final LoggerDemo loggerDemo;

    public JMSLoggingAdvice(LoggerDemo loggerDemo) {
        this.loggerDemo = loggerDemo;
    }
    @After("execution(* bank.integration.jms.JMSSenderImpl.sendJMSMessage(String)) && args(message)")
    public void logJmsMessage(JoinPoint joinpoint,String message) {
        loggerDemo.log("JMSSender: sent JMS message "+message);
    }
}
