package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.asset.dto.AssetDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
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
}
