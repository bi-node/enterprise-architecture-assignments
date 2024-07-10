package bank.aop.advice;

import bank.integration.logging.LoggerDemo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class DaoLogAdvice {

    final LoggerDemo loggerDemo;

    public DaoLogAdvice(LoggerDemo loggerDemo) {
        this.loggerDemo = loggerDemo;
    }


    @Before("execution(* bank.repository.*.*(..))")
    public void logBefore(JoinPoint joinpoint) {
        loggerDemo.log("Call made to "+joinpoint.getSignature().getName()+" method");
    }

}