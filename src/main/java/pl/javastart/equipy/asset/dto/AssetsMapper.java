package pl.javastart.equipy.asset.dto;

import pl.javastart.equipy.asset.Asset;

public class AssetsMapper {
    public static AssetDto mapToDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.setId(asset.getId());
        assetDto.setName(asset.getName());
        assetDto.setDescription(asset.getDescription());
        assetDto.setSerialNumber(asset.getSerialNumber());
        assetDto.setCategory(asset.getCategory().getName());
        return assetDto;
    }
}
