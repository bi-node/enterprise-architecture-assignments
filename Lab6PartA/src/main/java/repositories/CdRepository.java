package repositories;

import domain.products.Cd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CdRepository extends JpaRepository<Cd,Long> {
    List<Cd> findByPriceGreaterThanAndArtist(double price, String artistName);
    List<Cd> findAllByArtist(@Param("name") String artistName);
}
