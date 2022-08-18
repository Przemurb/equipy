package pl.javastart.equipy.assignment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.equipy.assignment.dto.AssignmentDto;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping ("/{id}/assignments")
    List<AssignmentDto> assignments (@PathVariable Long id) {
        return assignmentService.allUserAssignments(id);
    }
}
