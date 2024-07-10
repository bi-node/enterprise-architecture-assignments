package sender.springconfigurationpartc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl {
    @Autowired
    private DemoProperties demoProperties;

    public void print()
    {
        System.out.println("Application Name: " + demoProperties.getApplicationName());
        System.out.println("Server Name: " + demoProperties.getServerName());
        System.out.println("URL: " + demoProperties.getUrl());
        System.out.println("Version: " + demoProperties.getVersion());

        System.out.println("User Details:");
        System.out.println("  First Name: " + demoProperties.getUser().getFirstname());
        System.out.println("  Last Name: " + demoProperties.getUser().getLastname());
        System.out.println("  Username: " + demoProperties.getUser().getUsername());
        System.out.println("  Password: " + demoProperties.getUser().getPassword());

        System.out.println("Countries:");
        demoProperties.getCountries().forEach(country -> System.out.println("  " + country));

    }
}
