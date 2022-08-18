package pl.javastart.equipy.assignment;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.asset.AssetRepository;
import pl.javastart.equipy.assignment.dto.AssetAssignmentDto;
import pl.javastart.equipy.assignment.dto.UserAssignmentDto;
import pl.javastart.equipy.assignment.dto.AssignmentMapper;
import pl.javastart.equipy.assignment.exception.AssetNotFoundException;
import pl.javastart.equipy.assignment.exception.UserNotFoundException;
import pl.javastart.equipy.user.User;
import pl.javastart.equipy.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public AssignmentService(UserRepository userRepository, AssetRepository assetRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
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
}
