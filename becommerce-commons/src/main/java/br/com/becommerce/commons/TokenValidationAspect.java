package br.com.becommerce.commons;

import br.com.becommerce.commons.exception.NotAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class TokenValidationAspect {

	@Autowired
	private FakeAuthenticationService authenticationService;

	@Around("@annotation(br.com.becommerce.commons.annotation.TokenValidation)")
	public Object validateToken(ProceedingJoinPoint joinPoint) throws Throwable {

		String apiKey = getApiKey(joinPoint);
		try {
			authenticationService.validateToken(apiKey);
			return joinPoint.proceed();

		} catch (NotAuthorizedException e) {
			String message = "Api Key inválida!";
			log.error(String.format("m=validateToken, message=%s, api_key=%s", message, apiKey));

			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			response.sendError(HttpStatus.UNAUTHORIZED.value(), message);
			return null;
		}
	}

	private String getApiKey(ProceedingJoinPoint joinPoint){

		String apiKey = "";
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
		Method method = methodSignature.getMethod();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		assert args.length == parameterAnnotations.length;

		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			for (Annotation annotation : parameterAnnotations[argIndex]) {
				if (!(annotation instanceof RequestHeader))
					continue;
				RequestHeader requestHeader = (RequestHeader) annotation;
				if ("api_key".equals(requestHeader.value())) {
					apiKey = String.valueOf(args[argIndex]);
					break;
				}
			}
			if(!apiKey.isEmpty()) {
				break;
			}
		}

		return apiKey;
	}
}
