package guru.springframework.services.security;

import org.springframework.context.ApplicationEvent;

/**
 * Created by cs4r on 4/11/16.
 */
public class LoginFailureEvent extends ApplicationEvent {

    public LoginFailureEvent(Object source) {
        super(source);
    }
}
