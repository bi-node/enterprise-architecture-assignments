package sender.partasecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfig {

    @Bean
    public UserDetailsService userDetailsService( BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("bob")
                .password(bCryptPasswordEncoder.encode("12345"))
                .roles("employee")
                .build());

        manager.createUser(User.withUsername("mary")
                .password(bCryptPasswordEncoder.encode("12345"))
                .roles("finance-dept")
                .build());

        manager.createUser(User.withUsername("public")
                .password(bCryptPasswordEncoder.encode("12345"))
                .build());

        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
