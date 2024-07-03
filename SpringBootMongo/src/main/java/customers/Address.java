package customers;

import org.springframework.data.annotation.Id;

public class Address {
    @Id
    private long id;
    private String street;
    private String city;
    private String zip;

    public Address(long id, String street, String city, String zip) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
