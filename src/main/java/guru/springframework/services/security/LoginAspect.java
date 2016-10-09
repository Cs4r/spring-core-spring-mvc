package guru.springframework.services.security;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 
 * @author cs4r
 *
 */
@Aspect
@Component
public class LoginAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginAspect.class);

	@Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
	public void doAuthenticate() {

	}

	@Before("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
	public void logBefore(Authentication authentication) {
		String user = (String) authentication.getPrincipal();
		LOGGER.info("@Before: is user {} authenticated?  {}", user, authentication.isAuthenticated());
	}

	@AfterReturning(value = "guru.springframework.services.security.LoginAspect.doAuthenticate()", returning = "authentication")
	public void logAfterAuthenticate(Authentication authentication) {
		String user = (String) authentication.getPrincipal();
		LOGGER.info("@AfterReturning: is user {} authenticated?  {}", user, authentication.isAuthenticated());
	}

	@AfterThrowing("guru.springframework.services.security.LoginAspect.doAuthenticate() && args(authentication)")
	public void logAuthenicationException(Authentication authentication) {
		String user = (String) authentication.getPrincipal();
		LOGGER.info("@AfterThrowing: Logging failed for user {}", user);
	}
}
