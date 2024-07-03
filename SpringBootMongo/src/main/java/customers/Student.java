package customers;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Student {
    @Id
    private long id;
    private String name;
    private String phoneno;
    private Address address;


    private List<Grade> grades=new ArrayList<>();



    public Student(long id, String name, String phoneno, Address address) {
        this.id = id;
        this.name = name;
        this.phoneno = phoneno;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", address=" + address +
                '}';
    }

    public void addGrade(Grade grade)
    {
        grades.add(grade);
    }
}
