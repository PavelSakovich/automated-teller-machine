package AutomatedTellerMachine;

import AutomatedTellerMachine.exception.ExcessFundsException;
import AutomatedTellerMachine.exception.InsufficientFundsException;

public interface Atm {

    String deposit(int denomination, int quantity) throws InsufficientFundsException, ExcessFundsException;

    String withdraw(int sum) throws InsufficientFundsException;

    int balance();

    String status();

    int[] getDenominations();
}
