package pl.javastart.equipy.assignment;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.assignment.dto.AssignmentDto;
import pl.javastart.equipy.assignment.dto.AssignmentMapper;
import pl.javastart.equipy.assignment.exception.UserNotFoundException;
import pl.javastart.equipy.user.User;
import pl.javastart.equipy.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {
    private final UserRepository userRepository;

    public AssignmentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<AssignmentDto> allUserAssignments (Long id) {
        return userRepository.findById(id)
                .map(User::getAssignments)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(AssignmentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
