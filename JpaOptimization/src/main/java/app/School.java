package app;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
            @JoinColumn(name="student_id")
    List<Student> students=new ArrayList<>();

    public School() {
    }

    public School(String name) {
        this.name = name;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student Student)
    {
        this.students.add(Student);
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
