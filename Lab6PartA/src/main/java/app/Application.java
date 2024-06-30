package app;


import domain.Customer;
import domain.Order;
import domain.OrderLine;
import domain.products.Book;
import domain.products.Cd;
import domain.products.Dvd;
import domain.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.*;
import repositories.specification.CdSpecification;
import repositories.specification.CustomerSpecification;
import repositories.specification.OrderSpecification;

import java.util.List;


@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CdRepository cdRepository;
	@Autowired
	DVDRepository dvdRepository;
	@Autowired
	AddressRepository addressRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product product = new Cd();
		product.setName("Song CD Album");
		product.setDescription("Good collection of song album");
		product.setPrice(35.50);
		((Cd)product).setArtist("U2");
		OrderLine ol1 = new OrderLine(2, product);

		Dvd product2 = new Dvd();
		product2.setName("Rocky3");
		product2.setDescription("Album from 1995");
		product2.setPrice(12.98);
		product2.setGenre("Rock");
		OrderLine ol2 = new OrderLine(4, product2);


		Book product3 = new Book();
		product3.setName("The best of Queen");
		product3.setDescription("Album from 1995");
		product3.setPrice(12.98);
		product3.setIsbn("Song Book");
		OrderLine ol3 = new OrderLine(4, product3);

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New york", "43221");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		orderRepository.save(o1);

		Book product1=new Book();
		product1.setDescription("Nice Book");
		product1.setName("God Father");
		product1.setPrice(56.05);
		product1.setIsbn("4581251");

		OrderLine ol4=new OrderLine(2,product1);
		Order o2=new Order("589556", "05/06/19", "closed");
		o2.addOrderLine(ol4);
		Customer c2 = new Customer("John", "Doe", "Amsterdam",
				"New Castle", "58954");
		c2.addOrder(o2);
		o2.setCustomer(c2);
		orderRepository.save(o2);

		//Methods Queries
//		//1, find all customer
		System.out.println("\n The All customers.\n-----------------------------------");
		System.out.println(customerRepository.findAll());
		System.out.println("\n\n");

		//2. Give all CD’s from U2 with a price smaller than 10 euro
		System.out.println("\nAll CD's from U2 with price smaller than 10 euro\n--------------------");
		System.out.println(cdRepository.findByPriceGreaterThanAndArtist(10,"U2"));
		System.out.println("\n\n");

		//3. Give all customers with zip code 2389HJ
		System.out.println("\n all customers with zip code 2389H\n-----------------------------------");
		System.out.println(customerRepository.findAllByAddressZip("58954"));
		System.out.println("\n\n");

		//4. Give all customers who ordered a DVD with the name Rocky3
		System.out.println("\n Give all customers who ordered a DVD with the name Rocky3\n-----------------------------------");
		System.out.println(dvdRepository.findAllByName("Rocky3"));
		System.out.println("\n\n");

		//Named Queries
		//5. List all customer based on city
		System.out.println("\n Give all customers based on address for eg city\n-----------------------------------");
		System.out.println(customerRepository.findAllByAddressCity("New york"));
		System.out.println("\n\n");

		//6. List all Cd by artist name
		System.out.println("\n List all Cd by artist name\n-----------------------------------");
		System.out.println(cdRepository.findAllByArtist("U2"));
		System.out.println("\n\n");

		//JPQL queries
		//7.Find all order which are closed
		System.out.println("\n Find all order which are closed\n-----------------------------------");
		System.out.println(orderRepository.findByStatus("closed"));
		System.out.println("\n\n");

		//8. Give the first and lastnames of all customers who live in Amsterdam.
		System.out.println("\n first and lastnames of all customers who live in Amsterdam\n-----------------------------------");
		List< Object[]> results =customerRepository.findByStreet("Amsterdam");
		System.out.println("FirstName\t\t"+"LastName");
		for(Object[] result:results) {
			System.out.println((String) result[0]+"\t\t"+(String) result[1]);
		}
		System.out.println("\n\n");


		//9. Give the order numbers of all orders from customers who live in a certain city (city is a parameter).
		System.out.println("\nOrder numbers of all orders from customers who live in a certain city");
		System.out.println(orderRepository.findOrdersFromCity("New Castle"));
		System.out.println("\n\n");

		//10. Give all CD’s from a certain artist with a price bigger than a certain amount (
		System.out.println("\nGive all CD’s from a certain artist with a price bigger than a certain amount ");
		System.out.println(cdRepository.findbypriceandArtist("U2", 50));
		System.out.println("\n\n");

		//**********Native Query*******************//
		//11. Give all addresses in Amsterdam
		System.out.println("\nGive all address from certain city ");
		System.out.println(addressRepository.findbyCity("New york"));
		System.out.println("\n\n");

		//12. Give all CD’s from U2.
		System.out.println("\nGive CD from artist ");
		System.out.println(cdRepository.findByArtist("U2"));
		System.out.println("\n\n");

		//**********specification*************
		//13. Give the order numbers of all orders with status ‘open’
		System.out.println("\nStatus open order\n---------------------------------------------- ");
		Specification<Order> orderStatus= OrderSpecification.hasInStatus("open");
		List<Order> specorder = orderRepository.findAll(orderStatus);
		System.out.println(specorder);
		System.out.println("\n\n");

		//13. Give all customers who live in NewYork
		System.out.println("\nCus live in city\n---------------------------------------------- ");
		Specification<Customer> cusspec= CustomerSpecification.customerFromAdressCity("New Castle");
		List<Customer> liscus = customerRepository.findAll(cusspec);
		System.out.println(liscus);
		System.out.println("\n\n");


		//14. Give all CD’s from a certain artist with a price bigger than a certain
		// amount (artist and amount are parameter2
		System.out.println("CD with author and price ");
		Specification<Cd> cdAutherSpec= CdSpecification.hasArtist("U2");
		Specification<Cd> CdPriceGreaterThan=CdSpecification.priceGreaterThan(20);
		List<Cd> cdList=cdRepository.findAll(Specification.where(cdAutherSpec).and(CdPriceGreaterThan));
		System.out.println(cdList);




		List<Order> orders=orderRepository.findAll();
		for (Order order:orders){
			printOrder(order);
		}




	}

	public static void printOrder(Order order) {
		System.out.println("\n");
		System.out.println("Order with orderNumber: " + order.getOrderNumber());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
			if(product instanceof Dvd){
				System.out.println(((Dvd)product).getGenre());
			}
			else if(product instanceof Cd){
				System.out.println(((Cd)product).getArtist());
			}
			else if(product instanceof Book){
				System.out.println(((Book)product).getIsbn());
			}

		}
		System.out.println("\n");
	}
}
