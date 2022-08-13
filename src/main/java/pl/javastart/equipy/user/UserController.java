package pl.javastart.equipy.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.equipy.user.dto.UserDto;

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
}
