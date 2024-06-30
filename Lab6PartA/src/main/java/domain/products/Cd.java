package domain.products;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name="Cd.findAllByArtist", query = "select c from Cd c where c.artist=:name")
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

    @Override
    public String toString() {
        return "CD\n"+super.toString()+"\nArtist: "+artist;

    }
}
