import ATM.Atm;
import ATM.AtmImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 23.06.2016.
 */
public class AtmTest {

    @Test
    public void testGetAvailableQuantityOfDenomination() {
        // given
        Atm atm = new AtmImpl();
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
        Atm atm = new AtmImpl();
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
        Atm atm = new AtmImpl();
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

    @Test
    public void testInfo() {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);
        atm.deposit(50, 1);
        atm.deposit(100, 1);
        atm.deposit(200, 1);
        atm.deposit(500, 1);

        // when
        String result = atm.status();

        // then
        assertEquals(result,
                "Картридж [500], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                        "Картридж [200], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                        "Картридж [100], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                        "Картридж [50], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n" +
                        "Картридж [20], ёмкость [20], заполнение [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n");
    }
}
