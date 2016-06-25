import ATM.Atm;
import ATM.AtmImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class WithdrawTest {
    @Test
    public void testWithdrawWithSumMoreThanAvailable() {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);
        atm.deposit(50, 1);
        atm.deposit(100, 1);
        atm.deposit(200, 1);
        atm.deposit(500, 1);

        // when
        boolean result = atm.withdraw(5500);

        // then
        assertFalse(result);
    }

    @Test
    public void testWithdrawWithSumLessThanAvailable() {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);
        atm.deposit(50, 1);
        atm.deposit(100, 1);
        atm.deposit(200, 1);
        atm.deposit(500, 1);

        // when
        boolean result = atm.withdraw(870);

        // then
        assertTrue(result);
    }
}
