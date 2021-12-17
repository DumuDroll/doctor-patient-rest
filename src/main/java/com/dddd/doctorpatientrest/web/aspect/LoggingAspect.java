package com.dddd.doctorpatientrest.web.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

	private static final String STRING_WITH_ARGUMENTS = "{}.{}() with argument[s] = {}";
	private static final String RESULTED_IN = "{}.{}() resulted in {}";

	@Pointcut("execution(* com.dddd.doctorpatientrest.application.services..create(..))" +
			" || execution(* com.dddd.doctorpatientrest.application.services..update(..))" +
			" || execution(* com.dddd.doctorpatientrest.application.services..addDoctorToPatient(..))" +
			" || execution(* com.dddd.doctorpatientrest.application.services..addDrugToPatient(..))")
	public void createUpdatePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices
	}

	@Pointcut("execution(* com.dddd.doctorpatientrest.application.services..findAll())" +
			"|| execution(* com.dddd.doctorpatientrest.application.services..findById(long))" +
			"|| execution(* com.dddd.doctorpatientrest.application.services..findAllFiltered())")
	public void getPointCut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices
	}

	@Pointcut("execution(* com.dddd.doctorpatientrest.application.services..deleteById(long))")
	public void deletePointCut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices
	}


	@Around("createUpdatePointcut()")
	public Object logCreateUpdate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug(STRING_WITH_ARGUMENTS, proceedingJoinPoint.getSignature().getDeclaringTypeName(),
					proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
		}
		Object result = proceedingJoinPoint.proceed();
		if (log.isDebugEnabled()) {
			log.debug(RESULTED_IN, proceedingJoinPoint.getSignature().getDeclaringTypeName(),
					proceedingJoinPoint.getSignature().getName(), result.toString());
		}
		return result;
	}

	@Around("getPointCut()")
	public Object logGet(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object result = proceedingJoinPoint.proceed();
		if (log.isDebugEnabled()) {
			if (proceedingJoinPoint.getArgs() != null) {
				log.debug(STRING_WITH_ARGUMENTS, proceedingJoinPoint.getSignature().getDeclaringTypeName(),
						proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
			}
			log.debug(RESULTED_IN, proceedingJoinPoint.getSignature().getDeclaringTypeName(),
					proceedingJoinPoint.getSignature().getName(), result.toString());
		}
		return result;
	}

	@Around("deletePointCut()")
	public Object logDelete(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug(STRING_WITH_ARGUMENTS, proceedingJoinPoint.getSignature().getDeclaringTypeName(),
					proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
		}
		return proceedingJoinPoint.proceed();
	}

}
