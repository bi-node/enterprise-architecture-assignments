package repositories;

import domain.products.Dvd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DVDRepository extends JpaRepository<Dvd,Long> {
    List<Dvd> findAllByName(String name);
}
