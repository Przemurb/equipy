package pl.javastart.equipy.asset;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.equipy.asset.dto.AssetDto;

import java.util.List;

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
}
