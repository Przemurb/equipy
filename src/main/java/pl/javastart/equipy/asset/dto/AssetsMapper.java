package pl.javastart.equipy.asset.dto;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.category.Category;
import pl.javastart.equipy.category.CategoryRepository;

import java.util.Optional;

@Service
public class AssetsMapper {
    private final CategoryRepository categoryRepository;

    public AssetsMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public AssetDto mapToDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.setId(asset.getId());
        assetDto.setName(asset.getName());
        assetDto.setDescription(asset.getDescription());
        assetDto.setSerialNumber(asset.getSerialNumber());
        assetDto.setCategory(asset.getCategory().getName());
        return assetDto;
    }

    public Asset mapToEntity(AssetDto assetDto) {
        Asset asset = new Asset();
        asset.setId(assetDto.getId());
        asset.setName(assetDto.getName());
        asset.setDescription(assetDto.getDescription());
        asset.setSerialNumber(assetDto.getSerialNumber());
        Optional<Category> category = categoryRepository.findByName(assetDto.getCategory());
        category.ifPresent(asset::setCategory);
        return asset;
    }
}
