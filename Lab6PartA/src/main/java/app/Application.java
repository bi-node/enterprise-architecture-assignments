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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.OrderRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	@Autowired
	OrderRepository orderRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product product = new Cd();
		product.setName("Hibernate 3");
		product.setDescription("Good book on Hibernate");
		product.setPrice(35.50);
		((Cd)product).setArtist("John Doe");
		OrderLine ol1 = new OrderLine(2, product);

		Dvd product2 = new Dvd();
		product2.setName("The best of Queen");
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
