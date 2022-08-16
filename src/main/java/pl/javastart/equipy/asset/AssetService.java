package pl.javastart.equipy.asset;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.dto.AssetDto;
import pl.javastart.equipy.asset.dto.AssetsMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetService  {
    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    List<AssetDto> findAllAssets () {
        return assetRepository.findAll()
                .stream()
                .map(AssetsMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
