package pl.javastart.equipy.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.assignment.AssignmentService;
import pl.javastart.equipy.assignment.dto.UserAssignmentDto;
import pl.javastart.equipy.user.dto.UserDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AssignmentService assignmentService;

    public UserController(UserService userService, AssignmentService assignmentService) {
        this.userService = userService;
        this.assignmentService = assignmentService;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDto userDetails(@PathVariable Long id) {
        return userService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Brak użytkownika o id %s", id)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDto userUpdated(@PathVariable Long id, @RequestBody UserDto user) {
        if (!id.equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
        }
        return userService.update(id, user);
    }

    @GetMapping("/{id}/assignments")
    List<UserAssignmentDto> userAssignments(@PathVariable Long id) {
        return assignmentService.allUserAssignments(id);
    }

}
