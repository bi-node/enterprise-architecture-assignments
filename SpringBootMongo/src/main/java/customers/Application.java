package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        // create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		//get customers
		System.out.println(customerRepository.findById(66).get());
		System.out.println(customerRepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());
		//update customer
		customer = customerRepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerRepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerRepository.findByCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

		//*******************************************************************
		//create Students
		Grade g1=new Grade(1001,"WAP", "A+");
		Grade g2=new Grade(1002,"MPP", "B+");
		Grade g3=new Grade(1003,"WAA", "C+");
		Grade g4=new Grade(1004,"EA", "A+");

		Student student=new Student(101,"Binod Rasaili", "6412339287",
				new Address(1,"1000 N street", "Fairfield", "52556"));
		student.addGrade(g1);
		student.addGrade(g2);
		studentRepository.save(student);

		 student = new Student(102, "Sita Kumari", "9876543210",
				new Address(2, "2000 E Avenue", "Springfield", "12345"));
		student.addGrade(g1);
		student.addGrade(g2);
		studentRepository.save(student);

		student = new Student(103, "Rajesh Patel", "1234567890",
				new Address(3, "3000 S Road", "Greenville", "67890"));
		student.addGrade(g1);
		student.addGrade(g2);
		student.addGrade(g3);
		student.addGrade(g4);
		studentRepository.save(student);

		student = new Student(105, "Karan Singh", "3216549870",
				new Address(5, "5000 N Lane", "Centerville", "34567"));
		student.addGrade(g1);
		student.addGrade(g2);
		student.addGrade(g3);
		studentRepository.save(student);



		//1. find by Student name
		System.out.println("*****************************************************");
		System.out.println(studentRepository.findByName("Binod Rasaili"));

		//2. find Student with certain number
		System.out.println("******************Student by Ph NUmber***********************************");
		System.out.println(studentRepository.findByPhoneno("1234567890"));

		//3. find student by City
		System.out.println("******************Student by City Centerville*********************");
		System.out.println(studentRepository.findByAddressCity("Centerville"));

		//4 student with particular course
		System.out.println("******************Student by Course*********************");
		System.out.println(studentRepository.findAllByGradesCourse("EA"));

		//5 student with particular course and grade
		System.out.println("******************Student by Course and grade*********************");
		System.out.println(studentRepository.findAllByGradesWithGrade("WAP", "A+"));
	}

}
