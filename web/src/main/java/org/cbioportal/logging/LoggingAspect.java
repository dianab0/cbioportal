package org.cbioportal.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.cbioportal.utils.config.annotation.ConditionalOnProperty;

@Aspect
@Component
@ConditionalOnProperty(name = "aspect.enable.logging", havingValue = "true")
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger("LoggingAspect");

    @Before("execution(* org.cbioportal.web..*(..)) || execution(* org.cbioportal.service.impl..*(..)) || " +
    "execution(* org.cbioportal.persistence.mybatis..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.debug("Entered: " + joinPoint.getTarget().getClass().getName() + "." +
            joinPoint.getSignature().getName());
    }

    @After("execution(* org.cbioportal.web..*(..)) || execution(* org.cbioportal.service.impl..*(..)) || " +
    "execution(* org.cbioportal.persistence.mybatis..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.debug("Exited: " + joinPoint.getTarget().getClass().getName() + "." +
            joinPoint.getSignature().getName());
    }
}
