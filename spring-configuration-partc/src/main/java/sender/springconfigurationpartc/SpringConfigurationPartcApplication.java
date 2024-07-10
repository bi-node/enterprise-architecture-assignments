package sender.springconfigurationpartc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(DemoProperties.class)
@SpringBootApplication
public class SpringConfigurationPartcApplication implements CommandLineRunner {
    @Autowired
    ServiceImpl service;

    public static void main(String[] args) {
        SpringApplication.run(SpringConfigurationPartcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       service.print();
    }
}
