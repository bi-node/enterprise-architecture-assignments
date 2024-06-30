package repositories;

import domain.products.Cd;
import domain.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CdRepository extends JpaRepository<Cd,Long> {
    List<Cd> findByPriceGreaterThanAndArtist(double price, String artistName);
    List<Cd> findAllByArtist(@Param("name") String artistName);

    @Query("select c from Cd c where c.artist=:artist and c.price>=:price ")
    List<Cd> findbypriceandArtist(@Param("artist") String artist, @Param("price") double price);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, c.artist FROM cd c JOIN product p ON c.id = p.id WHERE c.artist = :artist", nativeQuery = true)
    List<Cd> findByArtist(@Param("artist") String artist);
    
}
