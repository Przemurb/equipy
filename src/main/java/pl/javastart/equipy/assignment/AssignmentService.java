package pl.javastart.equipy.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.asset.AssetRepository;
import pl.javastart.equipy.assignment.dto.AssetAssignmentDto;
import pl.javastart.equipy.assignment.dto.AssignmentDto;
import pl.javastart.equipy.assignment.dto.UserAssignmentDto;
import pl.javastart.equipy.assignment.dto.AssignmentMapper;
import pl.javastart.equipy.asset.exception.AssetNotFoundException;
import pl.javastart.equipy.user.exception.UserNotFoundException;
import pl.javastart.equipy.user.User;
import pl.javastart.equipy.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentService(UserRepository userRepository, AssetRepository assetRepository, AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
    }

    List<UserAssignmentDto> allUserAssignments(Long userId) {
        return userRepository.findById(userId)
                .map(User::getAssignments)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(AssignmentMapper::mapToUserAssignmentDto)
                .collect(Collectors.toList());
    }

    List<AssetAssignmentDto> allAssetAssignments(Long assetId) {
        return assetRepository.findById(assetId)
                .map(Asset::getAssignments)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .map(AssignmentMapper::mapToAssetAssignmentDto)
                .collect(Collectors.toList());
    }

    AssignmentDto newAssignment(AssignmentDto assignmentDto) {

        if (!userRepository.findById(assignmentDto.getUserId()).isPresent()) {
            throw new UserNotFoundException();
        }
        if (!assetRepository.findById(assignmentDto.getAssetId()).isPresent()) {
            throw new AssetNotFoundException();
        }
        if (!checkAssetAvailability(assignmentDto.getAssetId())) {
            assignmentDto.setStart(LocalDateTime.now());
            Assignment assignmentToSave = assignmentMapper.mapToAssignmentEntity(assignmentDto);
            Assignment savedAssignment = assignmentRepository.save(assignmentToSave);
            return AssignmentMapper.mapToAssignmentDto(savedAssignment);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Wybrane narzędzie nie jest dostępne");
    }

    private boolean checkAssetAvailability(Long assetId) {
        return assignmentRepository.findAllByAssetId(assetId)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .anyMatch(a -> (a.getStart() != null & a.getStop() == null));
    }
}
