import ATM.Atm;
import ATM.AtmImpl;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DepositTest {
    @Test
    public void testDepositWithDenominationsLessThanCapacity() {
        // given
        Atm atm = new AtmImpl();

        // when
        boolean result1 = atm.deposit(20, 20);
        boolean result2 = atm.deposit(500, 10);

        // then
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testDepositWithDenominationsMoreThanCapacity() {
        // given
        Atm atm = new AtmImpl();

        // when
        boolean result1 = atm.deposit(50, 55);
        boolean result2 = atm.deposit(200, 45);

        // then
        assertFalse(result1);
        assertFalse(result2);
    }


    @Test
    public void testDepositWithWrongDenominations() {
        // given
        Atm atm = new AtmImpl();

        // when
        boolean result1 = atm.deposit(5, 10);
        boolean result2 = atm.deposit(25, 10);

        // then
        assertFalse(result1);
        assertFalse(result2);
    }
}
