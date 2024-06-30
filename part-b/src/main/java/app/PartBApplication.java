package app;

import domain.Course;
import domain.Department;
import domain.Grade;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.StudentRepository;


@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")

public class PartBApplication implements CommandLineRunner {
   @Autowired
   StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(PartBApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {






        //student 1
        Student student1=new Student("John Munny", new Department("Computer Science"));
        Grade grade1=new Grade("A+");
        grade1.setCourse(new Course("Web Applications"));

        Grade grade2=new Grade("B+");
        grade2.setCourse(new Course("Java Spring boot"));


        student1.addGrade(grade1);
        student1.addGrade(grade2);

        studentRepository.save(student1);

        //student 2
        Student student2=new Student("Josey Wales", new Department("MBA"));
        grade1=new Grade("B+");
        grade1.setCourse(new Course("SAP"));

        grade2=new Grade("A");
        grade2.setCourse(new Course("ERP"));


        student2.addGrade(grade1);
        student2.addGrade(grade2);

        studentRepository.save(student2);

        //*********Queries*******************
        System.out.println("\n********************Queries******************");
        //Method Queries
        //1. Get all students from a certain department
        System.out.println("\nall students from a MBA department");
        studentRepository.findAllByDepartmentName("MBA").forEach(System.out::println);

        //2. Get all students who took a course with a certain name.
        System.out.println("\nall students who took a course with a certain name");
        studentRepository.findAllByGradesCourseName("SAP").forEach(System.out::println);

        //Using @Query
        //Get all students from a certain department
        System.out.println("\nGet all students from a certain department");
        studentRepository.allStudentByDepartmentName("Computer Science").forEach(System.out::println);
        //Get all students who took a course with a certain name.
        System.out.println("\nall students who took a course with a certain name");
        studentRepository.allStudentByCourseName("Java Spring boot").forEach(System.out::println);

    }
}
