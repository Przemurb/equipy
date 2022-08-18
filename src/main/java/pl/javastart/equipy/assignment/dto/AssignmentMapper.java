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

    public static AssignmentDto mapToDto (Assignment assigment) {
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assigment.getId());
        dto.setStart(assigment.getStart());
        dto.setEnd(assigment.getStop());
        dto.setAssetId(assigment.getAsset().getId());
        dto.setAssetName(assigment.getAsset().getName());
        dto.setAssetSerialNumber(assigment.getAsset().getSerialNumber());
        return dto;
    }
}
