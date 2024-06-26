package bank.aspect;

import bank.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class JMSLoggingAdvice {
    final Logger logger;

    public JMSLoggingAdvice(Logger logger) {
        this.logger = logger;
    }
    @After("execution(* bank.jms.JMSSender.sendJMSMessage(String)) && args(message)")
    public void logJmsMessage(JoinPoint joinpoint,String message) {
        logger.log("JMSSender: sent JMS message "+message);
    }
}