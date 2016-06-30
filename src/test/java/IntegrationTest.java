import AutomatedTellerMachine.Atm;
import AutomatedTellerMachine.AtmImpl;
import AutomatedTellerMachine.exception.ExcessFundsException;
import AutomatedTellerMachine.exception.InsufficientFundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void testDepositWithDenominationsLessThanCapacity() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();

        // when
        String result1 = atm.deposit(20, 5);
        String result2 = atm.deposit(500, 15);

        // then
        assertEquals("Вы успешно добавили 5 купюр(ы) номиналом 20 грн. на сумму 100 грн.", result1);
        assertEquals("Вы успешно добавили 15 купюр(ы) номиналом 500 грн. на сумму 7500 грн.", result2);
    }

    @Test
    public void testDepositWithDenominationsEqualToCapacity() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();

        // when
        String result1 = atm.deposit(200, 15);
        String result2 = atm.deposit(100, 15);

        // then
        assertEquals("Вы успешно добавили 15 купюр(ы) номиналом 200 грн. на сумму 3000 грн.", result1);
        assertEquals("Вы успешно добавили 15 купюр(ы) номиналом 100 грн. на сумму 1500 грн.", result2);
    }

    @Test(expected = ExcessFundsException.class)
    public void testDepositWithDenominationsMoreThanCapacity() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();

        // when
        String result1 = atm.deposit(50, 21);
    }


    @Test(expected = ExcessFundsException.class)
    public void testDepositWithWrongDenominations() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();

        // when
        String result1 = atm.deposit(5, 10);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawWithSumMoreThanAvailable() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);
        atm.deposit(50, 1);
        atm.deposit(100, 1);
        atm.deposit(200, 1);
        atm.deposit(500, 1);

        // when
        String result = atm.withdraw(5500);
    }

    @Test
    public void testWithdrawWithSumLessThanAvailable() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);
        atm.deposit(50, 1);
        atm.deposit(100, 1);
        atm.deposit(200, 1);
        atm.deposit(500, 1);

        // when
        String result = atm.withdraw(870);

        // then
        assertEquals("Сумма 870 грн. будет выдана:\n\n" +
                    "купюры: 1, номинал: 500 грн.\n" +
                    "купюры: 1, номинал: 200 грн.\n" +
                    "купюры: 1, номинал: 100 грн.\n" +
                    "купюры: 1, номинал: 50 грн.\n" +
                    "купюры: 1, номинал: 20 грн.\n", result);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawWithSumLessThanMinimumDenomination1() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);

        // when
        String result = atm.withdraw(10);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawWithSumLessThanMinimumDenomination2() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);

        // when
        String result = atm.withdraw(19);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawWithSumLessThanMinimumDenomination3() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 1);

        // when
        String result = atm.withdraw(39);
    }

    @Test
    public void testBalance1() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(500, 1);
        atm.deposit(200, 3);
        atm.deposit(100, 5);
        atm.deposit(50, 10);
        atm.deposit(20, 15);

        // when
        int actual = atm.balance();

        // then
        assertEquals(6750, actual);
    }

    @Test
    public void testBalance2() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(500, 5);
        atm.deposit(200, 5);
        atm.deposit(100, 5);
        atm.deposit(50, 5);
        atm.deposit(20, 5);

        // when
        int actual = atm.balance();

        // then
        assertEquals(8700, actual);
    }

    @Test
    public void testInitialBalance() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();

        // when
        int actual = atm.balance();

        // then
        assertEquals(4350, actual);
    }

    @Test
    public void testInitialStatus() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();

        // when
        String result = atm.status();

        // then
        assertEquals(result,
                        "Номинал: 500, количество: 5\n" +
                        "Номинал: 200, количество: 5\n" +
                        "Номинал: 100, количество: 5\n" +
                        "Номинал: 50, количество: 5\n" +
                        "Номинал: 20, количество: 5\n");
    }

    @Test
    public void testStatusWithMaxQuantity() throws ExcessFundsException, InsufficientFundsException {
        // given
        Atm atm = new AtmImpl();
        atm.deposit(20, 15);
        atm.deposit(50, 15);
        atm.deposit(100, 15);
        atm.deposit(200, 15);
        atm.deposit(500, 15);

        // when
        String result = atm.status();

        // then
        assertEquals(result,
                        "Номинал: 500, количество: 20\n" +
                        "Номинал: 200, количество: 20\n" +
                        "Номинал: 100, количество: 20\n" +
                        "Номинал: 50, количество: 20\n" +
                        "Номинал: 20, количество: 20\n");
    }
}
