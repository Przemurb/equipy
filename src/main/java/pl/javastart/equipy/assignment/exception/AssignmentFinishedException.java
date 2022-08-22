package pl.javastart.equipy.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wypożyczenie o podanym Id zostało już zwrócone")
public class AssignmentFinishedException extends RuntimeException{
}
