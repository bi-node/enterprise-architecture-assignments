import bank.domain.Account;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class AccountTest {
    @Test
public void testIncrement(){
        Account account = new Account();
        account.deposit(100.0);
        assertThat( account.getBalance(), closeTo (100.0, 0.01));
    }

    @Test
    public void testDecrement(){
        Account account = new Account();
        account.withdraw(500.0);
        assertThat(account.getBalance(), closeTo(-500.0, 0.01));
    }
}