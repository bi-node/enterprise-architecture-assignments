package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private ProductDAO productDAO;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());

		//for product
		productDAO.clearDB();
		//first product
		Product product=new Product(201, "Iphone", 500);
		Supplier supplier=new Supplier(101,"acb inc", "6412339632");
		product.setSupplier(supplier);
		productDAO.save(product);

		//second product
		product=new Product(202, "OnePlus", 400);
		 supplier=new Supplier(102,"xy inc", "6412330000");
		product.setSupplier(supplier);
		productDAO.save(product);

		System.out.println("-----------------Product by Id---------------------");
		System.out.println(productDAO.findProductById(201));
		System.out.println("-----------------Product by Name---------------------");
		System.out.println(productDAO.findProductByName("OnePlus"));
		System.out.println("-----------------Remove product---------------------");
		System.out.println(productDAO.removeProduct(201));
		System.out.println("-----------------get All product---------------------");
		System.out.println(productDAO.getAllProducts());





	}
}
