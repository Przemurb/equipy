package pl.javastart.equipy.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.assignment.dto.AssetAssignmentDto;
import pl.javastart.equipy.assignment.dto.AssignmentDto;
import pl.javastart.equipy.assignment.dto.UserAssignmentDto;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;

    public AssignmentController(AssignmentService assignmentService, AssignmentRepository assignmentRepository) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
    }

    @GetMapping("/api/users/{id}/assignments")
    List<UserAssignmentDto> userAssignments(@PathVariable Long id) {
        return assignmentService.allUserAssignments(id);
    }

    @GetMapping("/api/assets/{id}/assignments")
    List<AssetAssignmentDto> assetAssignments(@PathVariable Long id) {
        return assignmentService.allAssetAssignments(id);
    }

    @PostMapping("/api/assignments")
    ResponseEntity<?> newAssignment(@RequestBody AssignmentDto assignmentDto) {
        if (assignmentDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must by null");
        }
        if (assignmentDto.getAssetId() == null || assignmentDto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId or assetId can't by null");
        }
        AssignmentDto savedAssignment = assignmentService.newAssignment(assignmentDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAssignment.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedAssignment);
    }

    @PostMapping("/api/assignments/{id}/end")
    ResponseEntity<LocalDateTime> endAssignment(@PathVariable Long id) {
        if (!assignmentRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wypożyczenie o podanym ID nie istnieje");
        }
        assignmentRepository.findById(id)
                .ifPresent(a -> {
                    if (a.getStop() != null) {
                        ResponseEntity.badRequest().build();
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wyposażenie ma już przypisaną datę zwrotu");
                    }
                    a.setStop(LocalDateTime.now());
                    assignmentRepository.save(a);
                });
        return ResponseEntity.ok(LocalDateTime.now());
    }
}
