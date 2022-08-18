package pl.javastart.equipy.assignment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.equipy.assignment.dto.AssetAssignmentDto;
import pl.javastart.equipy.assignment.dto.UserAssignmentDto;

import java.util.List;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping ("/api/users/{id}/assignments")
    List<UserAssignmentDto> userAssignments (@PathVariable Long id) {
        return assignmentService.allUserAssignments(id);
    }

    @GetMapping("/api/assets/{id}/assignments")
    List<AssetAssignmentDto> assetAssignments (@PathVariable Long id) {
        return assignmentService.allAssetAssignments(id);
    }
}
