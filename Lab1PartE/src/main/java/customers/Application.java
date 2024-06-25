package customers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ICustomerService customerService = context.getBean(ICustomerService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com", "mainstreet 5", "Chicago", "60613");

		ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
		IProductService productService = appContext.getBean(IProductService.class);
		productService.addProduct(1,"Iphone X", 5000, "abc@xyz.com");

	}
}
