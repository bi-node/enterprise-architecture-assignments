package domain.products;

import jakarta.persistence.Entity;

@Entity
public class Cd extends Product{
    private String artist;

    public Cd(){}

    public Cd(String name, String description, double price, String artist) {
        super(name, description, price);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
