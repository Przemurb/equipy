package pl.javastart.equipy.user;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.user.dto.UserDto;
import pl.javastart.equipy.user.dto.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<UserDto> allUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());

    };
}
