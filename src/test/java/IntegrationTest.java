import ATM.Atm;
import ATM.AtmImpl;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntegrationTest {
//
//    @Test
//    public void testDepositWithDenominationsLessThanCapacity() {
//        // given
//        Atm atm = new AtmImpl();
//
//        // when
//        boolean result1 = atm.deposit(20, 5);
//        boolean result2 = atm.deposit(500, 19);
//
//        // then
//        assertTrue(result1);
//        assertTrue(result2);
//    }
//
//    @Test
//    public void testDepositWithDenominationsEqualToCapacity() {
//        // given
//        Atm atm = new AtmImpl();
//
//        // when
//        boolean result1 = atm.deposit(200, 20);
//        boolean result2 = atm.deposit(100, 20);
//
//        // then
//        assertTrue(result1);
//        assertTrue(result2);
//    }
//
//    @Test
//    public void testDepositWithDenominationsMoreThanCapacity() {
//        // given
//        Atm atm = new AtmImpl();
//
//        // when
//        boolean result1 = atm.deposit(50, 21);
//        boolean result2 = atm.deposit(200, 45);
//
//        // then
//        assertFalse(result1);
//        assertFalse(result2);
//    }
//
//
//    @Test
//    public void testDepositWithWrongDenominations() {
//        // given
//        Atm atm = new AtmImpl();
//
//        // when
//        boolean result1 = atm.deposit(5, 10);
//        boolean result2 = atm.deposit(25, 10);
//
//        // then
//        assertFalse(result1);
//        assertFalse(result2);
//    }
//
//    @Test
//    public void testWithdrawWithSumMoreThanAvailable() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 1);
//        atm.deposit(50, 1);
//        atm.deposit(100, 1);
//        atm.deposit(200, 1);
//        atm.deposit(500, 1);
//
//        // when
//        boolean result = atm.withdraw(5500);
//
//        // then
//        assertFalse(result);
//    }
//
//    @Test
//    public void testWithdrawWithSumLessThanAvailable() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 1);
//        atm.deposit(50, 1);
//        atm.deposit(100, 1);
//        atm.deposit(200, 1);
//        atm.deposit(500, 1);
//
//        // when
//        boolean result = atm.withdraw(870);
//
//        // then
//        assertTrue(result);
//    }
//
//    @Test
//    public void testWithdrawWithSumLessThanMinimumDenomination1() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 1);
//
//        // when
//        boolean result = atm.withdraw(10);
//
//        // then
//        assertFalse(result);
//    }
//
//    @Test
//    public void testWithdrawWithSumLessThanMinimumDenomination2() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 1);
//
//        // when
//        boolean result = atm.withdraw(19);
//
//        // then
//        assertFalse(result);
//    }
//
//    @Test
//    public void testWithdrawWithSumLessThanMinimumDenomination3() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 1);
//
//        // when
//        boolean result = atm.withdraw(39);
//
//        // then
//        assertFalse(result);
//    }
//
//    @Test
//    public void testBalance1() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(500, 1);
//        atm.deposit(200, 5);
//        atm.deposit(100, 10);
//        atm.deposit(50, 15);
//        atm.deposit(20, 20);
//
//        // when
//        int actual = atm.balance();
//
//        // then
//        assertEquals(3650, actual);
//    }
//
//    @Test
//    public void testBalance2() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(500, 5);
//        atm.deposit(200, 5);
//        atm.deposit(100, 5);
//        atm.deposit(50, 5);
//        atm.deposit(20, 5);
//
//        // when
//        int actual = atm.balance();
//
//        // then
//        assertEquals(4350, actual);
//    }
//
//    @Test
//    public void testBalanceEmpty() {
//        // given
//        Atm atm = new AtmImpl();
//
//        // when
//        int actual = atm.balance();
//
//        // then
//        assertEquals(0, actual);
//    }
//
//    @Test
//    public void testStatusWithLowQuantity() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 1);
//        atm.deposit(50, 1);
//        atm.deposit(100, 1);
//        atm.deposit(200, 1);
//        atm.deposit(500, 1);
//
//        // when
//        String result = atm.status();
//
//        // then
//        assertEquals(result,
//                "Номинал: 500, количество: 1\n" +
//                        "Номинал: 200, количество: 1\n" +
//                        "Номинал: 100, количество: 1\n" +
//                        "Номинал: 50, количество: 1\n" +
//                        "Номинал: 20, количество: 1\n");
//    }
//
//    @Test
//    public void testStatusWithMaxQuantity() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 20);
//        atm.deposit(50, 20);
//        atm.deposit(100, 20);
//        atm.deposit(200, 20);
//        atm.deposit(500, 20);
//
//        // when
//        String result = atm.status();
//
//        // then
//        assertEquals(result,
//                "Номинал: 500, количество: 20\n" +
//                        "Номинал: 200, количество: 20\n" +
//                        "Номинал: 100, количество: 20\n" +
//                        "Номинал: 50, количество: 20\n" +
//                        "Номинал: 20, количество: 20\n");
//    }
//
//    @Test
//    public void testStatusWithWrongQuantity() {
//        // given
//        Atm atm = new AtmImpl();
//        atm.deposit(20, 22);
//        atm.deposit(50, 33);
//        atm.deposit(100, 44);
//        atm.deposit(200, 55);
//        atm.deposit(500, 66);
//
//        // when
//        String result = atm.status();
//
//        // then
//        assertEquals(result,
//                "Номинал: 500, количество: 0\n" +
//                        "Номинал: 200, количество: 0\n" +
//                        "Номинал: 100, количество: 0\n" +
//                        "Номинал: 50, количество: 0\n" +
//                        "Номинал: 20, количество: 0\n");
//    }
}
