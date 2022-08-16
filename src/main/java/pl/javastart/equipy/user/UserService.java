package pl.javastart.equipy.user;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.user.dto.UserDto;
import pl.javastart.equipy.user.dto.UserMapper;
import pl.javastart.equipy.user.exception.PeselException;

import java.util.List;
import java.util.Optional;
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
    }

    List<UserDto> findUsersByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
    }

    UserDto registerNewUser(UserDto userDto) {
        if (userRepository.existsByPesel(userDto.getPesel())) {
            throw new PeselException();
        } else {
            User user = UserMapper.mapToEntity(userDto);
            User savedUser = userRepository.save(user);
            return UserMapper.mapToDto(savedUser);
        }
    }

    Optional<UserDto> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::mapToDto);
    }

    UserDto update(Long id, UserDto userDto) {
        userRepository.findByPesel(userDto.getPesel()).ifPresent(
                u -> {if(!id.equals(u.getId()))
            throw new PeselException();
        });
        User user = UserMapper.mapToEntity(userDto);
        userRepository.save(user);
        return UserMapper.mapToDto(user);
    }
}
