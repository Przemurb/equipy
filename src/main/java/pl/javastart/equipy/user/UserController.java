package pl.javastart.equipy.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.user.dto.UserDto;
import pl.javastart.equipy.user.exception.PeselException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    List<UserDto> getAllUsers(@RequestParam(required = false) String lastName) {
        if (lastName == null) {
            return userService.allUsers();
        } else {
            return userService.findUsersByLastName(lastName);
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> newUser(@RequestBody UserDto userDto) {
        if (userDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must by null");
        } else {
            UserDto savedUser = userService.registerNewUser(userDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userDto.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(savedUser);
        }
    }
}
