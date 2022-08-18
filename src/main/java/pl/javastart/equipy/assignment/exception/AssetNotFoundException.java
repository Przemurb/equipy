package pl.javastart.equipy.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.BAD_REQUEST, reason = "Brak wyposażenie o podanym ID")
public class AssetNotFoundException extends RuntimeException {
}
