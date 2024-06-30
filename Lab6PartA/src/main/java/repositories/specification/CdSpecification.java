package repositories.specification;

import domain.products.Cd;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.jpa.domain.Specification;

public class CdSpecification {

    public static Specification<Cd> hasArtist(String artist) {
        return
                (root,query,cb)->cb.like(cb.lower(root.get("artist")), "%"+artist.toLowerCase()+"%");

    }

    public static Specification<Cd> priceGreaterThan(double price) {
        return
                (root,query,cb)->cb.greaterThan(root.get("price"),price);
    }
}
