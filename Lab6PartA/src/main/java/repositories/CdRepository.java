package repositories;

import domain.products.Cd;
import domain.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CdRepository extends JpaRepository<Cd,Long> {
    List<Cd> findByPriceGreaterThanAndArtist(double price, String artistName);
}
