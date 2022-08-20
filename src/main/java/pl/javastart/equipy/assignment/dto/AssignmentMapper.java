package pl.javastart.equipy.assignment.dto;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.AssetRepository;
import pl.javastart.equipy.asset.exception.AssetNotFoundException;
import pl.javastart.equipy.assignment.Assignment;
import pl.javastart.equipy.user.UserRepository;
import pl.javastart.equipy.user.exception.UserNotFoundException;

import java.time.LocalDateTime;

@Service
public class AssignmentMapper {
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public AssignmentMapper(UserRepository userRepository, AssetRepository assetRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
    }

//        "id": 2,
//        "start": "2018-10-09T12:00:00",
//        "end": null,
//        "assetId": 5,
//        "assetName": "Samsung Note 8",
//        "assetSerialNumber": "SN882017AX896B"

    public static UserAssignmentDto mapToUserAssignmentDto(Assignment assigment) {
        UserAssignmentDto dto = new UserAssignmentDto();
        dto.setId(assigment.getId());
        dto.setStart(assigment.getStart());
        dto.setEnd(assigment.getStop());
        dto.setAssetId(assigment.getAsset().getId());
        dto.setAssetName(assigment.getAsset().getName());
        dto.setAssetSerialNumber(assigment.getAsset().getSerialNumber());
        return dto;
    }

//        "id": 2,
//        "start": "2018-10-09T12:00:00",
//        "end": "2018-10-18T15:05:12.716537",
//        "userId": 1,
//        "firstName": "Jan",
//        "lastName": "Kowalski",
//        "pesel": "90101222457"
    public static AssetAssignmentDto mapToAssetAssignmentDto (Assignment assignment) {
        AssetAssignmentDto dto = new AssetAssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getStop());
        dto.setUserId(assignment.getUser().getId());
        dto.setFirstName(assignment.getUser().getFirstName());
        dto.setLastName(assignment.getUser().getLastName());
        dto.setPesel(assignment.getUser().getPesel());
        return dto;
    }

//    "id": 6,
//    "start": "2018-10-19T09:00:32.227076",
//    "end": null,
//    "userId": 3,
//    "assetId": 2
    public static AssignmentDto mapToAssignmentDto (Assignment assignment) {
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getStop());
        dto.setUserId(assignment.getUser().getId());
        dto.setAssetId(assignment.getAsset().getId());
        return dto;
    }


//    "userId": 3,
//    "assetId": 2
    public Assignment mapToAssignmentEntity (AssignmentDto dto) {
        Assignment assignment = new Assignment();
        assignment.setId(dto.getId());
        assignment.setStart(dto.getStart());
        assignment.setStop(dto.getEnd());
        assignment.setUser(userRepository.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new));
        assignment.setAsset(assetRepository.findById(dto.getAssetId()).orElseThrow(AssetNotFoundException::new));
        return assignment;
    }

}
