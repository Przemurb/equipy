package pl.javastart.equipy.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.assignment.dto.AssignmentDto;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("")
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

    @PostMapping("/{id}/end")
    public ResponseEntity<?> finishAssignment(@PathVariable Long id) {
        LocalDateTime endDate = assignmentService.finishAssignment(id);
        return ResponseEntity.accepted().body(endDate);
    }

}
