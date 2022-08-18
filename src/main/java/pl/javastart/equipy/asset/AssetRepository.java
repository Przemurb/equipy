package pl.javastart.equipy.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findAssetByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCase(String name, String serialNumber);
    Optional<Asset> findBySerialNumberIgnoreCase(String serialNumber);

    Optional<Asset> findBySerialNumber (String serialNumber);
}
