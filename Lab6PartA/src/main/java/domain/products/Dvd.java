package domain.products;

import jakarta.persistence.Entity;

@Entity
public class Dvd extends Product {
    private String genre;

    public Dvd() {

    }

    public Dvd(String name, String description, double price, String genre) {
        super(name, description, price);
        this.genre = genre;

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "DVD\n" + super.toString() + "\nGenre: " + genre;
    }
}
