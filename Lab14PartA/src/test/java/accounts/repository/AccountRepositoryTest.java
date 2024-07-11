package accounts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import accounts.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import accounts.domain.Account;
import accounts.AccountServiceApplication;

@DataJpaTest
@ContextConfiguration(classes = AccountServiceApplication.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        // Initialize the database with test data
        repository.save(new Account("123", 500.0, "Binod Rasaili"));
        repository.save(new Account("456", 1000.0, "Frank de Jong"));
    }

    @Test
    public void testFindByAccountHolder() {
        // Test the findByAccountHolder method
        Account account = repository.findByAccountHolder("Binod Rasaili");
        assertThat(account).isNotNull();
        assertThat(account.getAccountHolder()).isEqualTo("Binod Rasaili");
        assertThat(account.getAccountNumber()).isEqualTo("123");
        assertThat(account.getBalance()).isEqualTo(500.0);

        Account account2 = repository.findByAccountHolder("Frank de Jong");
        assertThat(account2).isNotNull();
        assertThat(account2.getAccountHolder()).isEqualTo("Frank de Jong");
        assertThat(account2.getAccountNumber()).isEqualTo("456");
        assertThat(account2.getBalance()).isEqualTo(1000.0);
    }

    @Test
    public void testFindByAccountHolderNotFound() {
        // Test the findByAccountHolder method with non-existing account holder
        Account account = repository.findByAccountHolder("Non Existing");
        assertThat(account).isNull();
    }
}
