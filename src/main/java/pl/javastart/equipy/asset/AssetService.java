package pl.javastart.equipy.asset;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.dto.AssetDto;
import pl.javastart.equipy.asset.dto.AssetsMapper;
import pl.javastart.equipy.asset.exception.SerialNumberException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetService  {
    private final AssetRepository assetRepository;
    private final AssetsMapper assetsMapper;

    public AssetService(AssetRepository assetRepository, AssetsMapper assetsMapper) {
        this.assetRepository = assetRepository;
        this.assetsMapper = assetsMapper;
    }

    List<AssetDto> findAllAssets () {
        return assetRepository.findAll()
                .stream()
                .map(assetsMapper::mapToDto)
                .collect(Collectors.toList());
    }

    List<AssetDto> findAssetsByNameOrSerial(String text) {
        return assetRepository.findAssetByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCase(text, text)
                .stream()
                .map(assetsMapper::mapToDto)
                .collect(Collectors.toList());
    }

    AssetDto saveNewAsset (AssetDto assetDto) {
        if(assetRepository.findBySerialNumberIgnoreCase(assetDto.getSerialNumber()) != null) {
            throw new SerialNumberException();
        }
        Asset asset = assetsMapper.mapToEntity(assetDto);
        Asset assetSaved = assetRepository.save(asset);
        return assetsMapper.mapToDto(assetSaved);
    }
}
