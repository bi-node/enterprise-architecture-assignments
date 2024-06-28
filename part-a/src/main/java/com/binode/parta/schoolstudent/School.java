package com.binode.parta.schoolstudent;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @MapKey(name="studentId")
    private Map<Long,Student> students =new HashMap<Long,Student>();

    public School() {}

    public School(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(),student);
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

    public Map<Long, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<Long, Student> students) {
        this.students = students;
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
