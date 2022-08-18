package pl.javastart.equipy.assignment.dto;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.assignment.Assignment;

@Service
public class AssignmentMapper {

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
        dto.setStop(assignment.getStop());
        dto.setUserId(assignment.getUser().getId());
        dto.setFirstName(assignment.getUser().getFirstName());
        dto.setLastName(assignment.getUser().getLastName());
        dto.setPesel(assignment.getUser().getPesel());
        return dto;
    }
}
