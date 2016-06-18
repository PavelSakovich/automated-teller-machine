package ATM;

/**
 * Created by User on 18.06.2016.
 */
public class atmProperties {
    private int[] denominations;
    private int[] initialQuantity;

    public void setDenominations(int[] denominations) {
        this.denominations = denominations;
    }

    public void setInitialQuantity(int[] initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public int[] getDenominations() {
        return denominations;
    }

    public int[] getInitialQuantity() {
        return initialQuantity;
    }
}
