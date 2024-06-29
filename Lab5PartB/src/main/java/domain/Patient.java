package domain;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
@SecondaryTables({
		@SecondaryTable(name = "patients_table", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")),
		@SecondaryTable(name = "address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "patient_id"))
})
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", table = "patients_table")
	private String name;

	@Column(name = "street", table = "address")
	private String street;

	@Column(name = "zip", table = "address")
	private String zip;

	@Column(name = "city", table = "address")
	private String city;

	public Patient() {
	}

	public Patient(String name, String street, String zip, String city) {
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Patient{" +
				"id=" + id +
				", name='" + name + '\'' +
				", street='" + street + '\'' +
				", zip='" + zip + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
