package ATM;

import ATM.exception.ExcessFundsException;
import ATM.exception.InsufficientFundsException;

public interface Atm {

    String deposit(int denomination, int quantity) throws InsufficientFundsException, ExcessFundsException;

    String withdraw(int sum) throws InsufficientFundsException;

    int balance();

    String status();

    int[] getDenominations();
}
