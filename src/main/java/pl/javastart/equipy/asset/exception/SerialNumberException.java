package pl.javastart.equipy.asset.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Wyposażenie o podanym numerze seryjnym już istnieje")
public class SerialNumberException extends RuntimeException{
}
