package pl.javastart.equipy.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findAssetByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCase(String name, String serialNumber);
    Asset findBySerialNumberIgnoreCase(String serialNumber);

}
