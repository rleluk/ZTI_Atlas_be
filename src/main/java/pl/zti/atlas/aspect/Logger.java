package pl.zti.atlas.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log
@Component
@Aspect
public class Logger {

    @Before("execution(* pl.zti.atlas.controller..*.*(..)) || execution(* pl.zti.atlas.service..*.* (..))")
    public void printBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget().getClass().getSimpleName()
                + " executes method " + joinPoint.getSignature().getName() + " with arguments "
                + Arrays.toString(joinPoint.getArgs()));
    }
}