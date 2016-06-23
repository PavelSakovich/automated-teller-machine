import ATM.ATM;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Created by User on 15.06.2016.
 */
public class WithdrawTest {
    @Test
    public void testWithdrawWithSumMoreThanAvailable() {
        // given
        ATM atm = new ATM();
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
        ATM atm = new ATM();
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

    @Test
    public void testInfo() {
        // given
        ATM atm = new ATM();
        atm.deposit(20, 1);
        atm.deposit(50, 1);
        atm.deposit(100, 1);
        atm.deposit(200, 1);
        atm.deposit(500, 1);

        // when
        String result = atm.info();

        // then
        assertEquals(result,
                "Картридж [500], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                "Картридж [200], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                "Картридж [100], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                "Картридж [50], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                "Картридж [20], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
    }

    @Test
    public void testGetAvailableQuantityOfDenomination() {
        // given
        ATM atm = new ATM();
        atm.deposit(500, 1);
        atm.deposit(200, 5);
        atm.deposit(100, 10);
        atm.deposit(50, 15);
        atm.deposit(20, 20);

        // when
        int actual500 = atm.getAvailableQuantityOfDenomination(500);
        int actual200 = atm.getAvailableQuantityOfDenomination(200);
        int actual100 = atm.getAvailableQuantityOfDenomination(100);
        int actual50 = atm.getAvailableQuantityOfDenomination(50);
        int actual20 = atm.getAvailableQuantityOfDenomination(20);

        // then
        assertEquals(1, actual500);
        assertEquals(5, actual200);
        assertEquals(10, actual100);
        assertEquals(15, actual50);
        assertEquals(20, actual20);
    }

    @Test
    public void testGetAvailableSumOfDenomination() {
        // given
        ATM atm = new ATM();
        atm.deposit(500, 1);
        atm.deposit(200, 5);
        atm.deposit(100, 10);
        atm.deposit(50, 15);
        atm.deposit(20, 20);

        // when
        int actual500 = atm.getAvailableSumOfDenomination(500);
        int actual200 = atm.getAvailableSumOfDenomination(200);
        int actual100 = atm.getAvailableSumOfDenomination(100);
        int actual50 = atm.getAvailableSumOfDenomination(50);
        int actual20 = atm.getAvailableSumOfDenomination(20);

        // then
        assertEquals(500, actual500);
        assertEquals(1000, actual200);
        assertEquals(1000, actual100);
        assertEquals(750, actual50);
        assertEquals(400, actual20);
    }

    @Test
    public void testGetTotalAvailableSum() {
        // given
        ATM atm = new ATM();
        atm.deposit(500, 1);
        atm.deposit(200, 5);
        atm.deposit(100, 10);
        atm.deposit(50, 15);
        atm.deposit(20, 20);

        // when
        int actual = atm.getTotalAvailableSum();

        // then
        assertEquals(3650, actual);
    }
}
