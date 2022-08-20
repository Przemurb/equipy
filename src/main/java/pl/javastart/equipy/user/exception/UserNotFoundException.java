package pl.javastart.equipy.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Brak użytkownika o podanym ID")
public class UserNotFoundException extends RuntimeException {
}
