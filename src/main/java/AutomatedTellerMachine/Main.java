package AutomatedTellerMachine;

import AutomatedTellerMachine.exception.ExcessFundsException;
import AutomatedTellerMachine.exception.InsufficientFundsException;

public class Main {
    public static void main(String[] args) {

        Atm atm = new AtmImpl();

        try {
            atm.deposit(20, 5);
            atm.deposit(50, 5);
            atm.deposit(100, 5);
            atm.deposit(200, 5);
            atm.deposit(500, 5);
            System.out.println(atm.balance());
            System.out.println(atm.withdraw(3500));
            System.out.println(atm.balance());
            System.out.println(atm.status());
        } catch (InsufficientFundsException | ExcessFundsException e) {
            e.getMessage();
        }
    }
}
