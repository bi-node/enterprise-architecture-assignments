package com.binode.parta;

import com.binode.parta.employeedepartment.Department;
import com.binode.parta.employeedepartment.DepartmentRepository;
import com.binode.parta.employeedepartment.Employee;
import com.binode.parta.bookpublisher.Book;
import com.binode.parta.bookpublisher.BookRepository;
import com.binode.parta.bookpublisher.Publisher;
import com.binode.parta.passengerflight.Flight;
import com.binode.parta.passengerflight.FlightRepository;
import com.binode.parta.passengerflight.Passenger;
import com.binode.parta.passengerflight.PassengerRepository;
import com.binode.parta.schoolstudent.School;
import com.binode.parta.schoolstudent.SchoolRepository;
import com.binode.parta.schoolstudent.Student;
import com.binode.parta.schoolstudent.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PartAApplication implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(PartAApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a new Department
        Department department = new Department("Human Resources");

        // Create new Employees
        Employee employee1 = new Employee("Binod Rasaili");
        Employee employee2 = new Employee("Sagar Maharjan");

        // Add employees to department
        department.addEmployee(employee1);
        department.addEmployee(employee2);

        // Save department (will cascade to employees)
        departmentRepository.save(department);

        // Retrieve the department and check employees
        Department savedDepartment = departmentRepository.findById(department.getId()).orElse(null);
        if (savedDepartment != null) {
            System.out.println("Department: " + savedDepartment.getName());
            for (Employee emp : savedDepartment.getEmployees()) {
                System.out.println("Employee: " + emp.getName());
            }
        }

        //no2
        Book book = new Book("2456-8654", "Dune:Prophecy", "Frank Herbert");
        book.setPublisher(new Publisher("5-Star Publishers"));
        bookRepository.save(book);

        Book book1 = new Book("2456-8658", "Children of Dune", "Frank Herbert");
        bookRepository.save(book1);

        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);

        //passenger Flight
        System.out.println("\n\n\n\n\n");

        Flight flight1 = new Flight(23, "Miami", "Fairfield", LocalDate.of(2024, 2, 2));
        Flight flight2 = new Flight(24, "Texas", "California", LocalDate.of(2024, 2, 18));
        Passenger passenger = new Passenger("Binod Rasaili");
        passenger.addFlight(flight1);
        passenger.addFlight(flight2);
        passengerRepository.save(passenger);

        System.out.println(passengerRepository.findAll());

        //school student
        System.out.println("\n\n\n\n\n");
        Student student1 = new Student("Sagar", "Maharjan");
        Student student2 = new Student("Abdoon", "Nur");

        studentRepository.save(student1);
        studentRepository.save(student2);

        School school = new School("Greenwood High");
        school.addStudent(student1);
        school.addStudent(student2);

        schoolRepository.save(school);
        // Retrieve and display the school with its students
        School retrievedSchool = schoolRepository.findById(school.getId()).orElse(null);
        if (retrievedSchool != null) {
            System.out.println(retrievedSchool.toString());
            for (Map.Entry<Long, Student> entry : retrievedSchool.getStudents().entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        }


    }
}



