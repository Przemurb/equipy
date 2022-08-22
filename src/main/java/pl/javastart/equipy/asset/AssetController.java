package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.asset.dto.AssetDto;
import pl.javastart.equipy.assignment.AssignmentService;
import pl.javastart.equipy.assignment.dto.AssetAssignmentDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/assets")
public class AssetController {
    private final AssetService assetService;
    private final AssignmentService assignmentService;

    public AssetController(AssetService assetService, AssignmentService assignmentService) {
        this.assetService = assetService;
        this.assignmentService = assignmentService;
    }


    @GetMapping("")
    List<AssetDto> assets (@RequestParam (required = false) String text) {
        if (text == null) {
            return assetService.findAllAssets();
        } else {
            return assetService.findAssetsByNameOrSerial(text);
        }
    }

    @PostMapping("")
    @ResponseStatus (HttpStatus.CREATED)
    ResponseEntity<?> newAsset (@RequestBody AssetDto assetDto) {
        if(assetDto.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must by null");
        }
        AssetDto assetDtoToSave = assetService.saveNewAsset(assetDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assetDtoToSave.getId())
                .toUri();
        return ResponseEntity.created(uri).body(assetDtoToSave);
    }

    @GetMapping("{id}")
    ResponseEntity<AssetDto> assetDetails (@PathVariable Long id) {
        return assetService.findAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<AssetDto> updateAsset (@RequestBody AssetDto assetdto, @PathVariable Long id) {
        if(!id.equals(assetdto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
        }
        AssetDto updatedAsset = assetService.update(id, assetdto);
        return ResponseEntity.ok(updatedAsset);
    }

    @GetMapping("/{id}/assignments")
    List<AssetAssignmentDto> assetAssignments(@PathVariable Long id) {
        return assignmentService.allAssetAssignments(id);
    }
}
