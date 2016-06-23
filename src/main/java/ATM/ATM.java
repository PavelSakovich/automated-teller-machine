package ATM;

/**
 * Created by User on 23.06.2016.
 */
public interface Atm {

    boolean deposit(int denomination, int quantity);

    boolean withdraw(int sum);

    int getTotalAvailableSum();

    int getAvailableSumOfDenomination(int denomination);

    int getAvailableQuantityOfDenomination(int denomination);

    String info();
}
