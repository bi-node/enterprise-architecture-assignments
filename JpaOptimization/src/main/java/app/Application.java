package app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		insertCustomers();
//		retrieveCustomers();
//		updateCustomers();

		insertSchool();
		getSchool();
		getSchoolwithStudents();

	}

	private void getSchool() {
		System.out.println("Retrieving all school ...");
		long start = System.currentTimeMillis();

		List<School> schools = schoolRepository.findSchoolOnly();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		for (School school : schools) {
			System.out.println(school.getName());
		}
		System.out.println("To retrieve all school without student took "+timeElapsed+" ms");

	}
	private void getSchoolwithStudents() {
		System.out.println("Retrieving all school ...");
		long start = System.currentTimeMillis();

		List<School> schools = schoolRepository.findSchoolWithStudents();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println(schools);

		System.out.println("To retrieve all school with students took "+timeElapsed+" ms");

	}


	private void insertSchool() {
		long start = System.currentTimeMillis();
		for(int i=0; i<50;i++)
		{
			School school = new School("Holy Vision School");
			for(int j=0; j<100;j++)
			{
				Student student = new Student("Jane","Doe","janedoe@unknown.com");
				school.addStudent(student);
			}
			schoolRepository.save(school);
		}
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("Total time elapsed while inserting: " + timeElapsed);
	}


	private void insertCustomers() {
		for (int x=0; x<5000; x++) {
			Customer customer = new Customer("John Doe "+x);
			Account account = new Account("123"+x);
			customer.setAccount(account);
			customerRepository.save(customer);
			System.out.println("Inserting customer  "+x);
		}
	}

	private void retrieveCustomers() {
		System.out.println("Retrieving all customers ...");
		long start = System.currentTimeMillis();

		List<Customer> customers = customerRepository.findAll();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To retrieve all Customers took "+timeElapsed+" ms");
	}

	private void updateCustomers() {
		System.out.println("Change the name of all customers ...");
		long start = System.currentTimeMillis();

		List<Customer> customers = customerRepository.findAll();
		for(Customer c: customers){
			c.setName("James Bond");
			customerRepository.save(c);
		}
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To change the name of all customers took "+timeElapsed+" ms");
	}
}
