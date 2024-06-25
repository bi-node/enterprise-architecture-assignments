package customers;

public class CustomerService implements ICustomerService {
	ICustomerDAO customerDAO ;
	IEmailSender emailSender ;


	//1. setter injection for customer DAO and email sender
	public void setCustomerDAO(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setEmailSender(IEmailSender emailSender) {
		this.emailSender = emailSender;
	}



	public void addCustomer(String name, String email, String street,
			String city, String zip) {
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerDAO.save(customer);
		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}
}
