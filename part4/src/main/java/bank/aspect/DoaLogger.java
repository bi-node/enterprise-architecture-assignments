package bank.aspect;


import bank.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class DoaLogger {

    final Logger logger;

    public DoaLogger(Logger logger) {
        this.logger = logger;
    }


    @Before("execution(* bank.dao.*.*(..))")
    public void logBefore(JoinPoint joinpoint) {
        logger.log("Call made to "+joinpoint.getSignature().getName()+" method");
    }

}