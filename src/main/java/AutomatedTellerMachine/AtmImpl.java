package AutomatedTellerMachine;

import AutomatedTellerMachine.exception.ExcessFundsException;
import AutomatedTellerMachine.exception.InsufficientFundsException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AtmImpl implements Atm {

    private final String propertiesPath;

    public int[] getDenominations() {
        return denominations;
    }

    private final int[] denominations;
    volatile private int[] amounts;
    private final int capacity;
    private final int limit;
    private final String maxApproximateSum = " Максимально приближенная к запрашиваемой сумма ";
    private final String currency = " грн.";

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

    private static final Logger log = Logger.getLogger(Atm.class);

    public AtmImpl() {
        propertiesPath = "src/main/resources/atm.properties";
        denominations = propertiesLoader("denominations");
        amounts = propertiesLoader("amounts");
        capacity = propertiesLoader("capacity")[0];
        limit = propertiesLoader("limit")[0];
    }

    /**
     * Adds specified quantity of denominations to the current ATM's balance.
     *
     * @param denomination
     * @param quantity
     * @return a string with result of operation;
     * @throws ExcessFundsException     on error;
     * @throws IllegalArgumentException if denomination does not exist.
     */
    @Override
    public synchronized String deposit(int denomination, int quantity) throws ExcessFundsException {

        int position = getArrayPosition(denominations, denomination);

        if (position < 0) {
            throw new IllegalArgumentException("Указан неправильный номинал валюты.");

        } else if (amounts[position] + quantity > capacity || quantity <= 0) {
            throw new ExcessFundsException("Невозможно добавить " + quantity
                    + " купюр(ы) номиналом " + denomination + currency);

        } else {
            amounts[position] += quantity;

            log.info("[DEPOSIT] " + quantity * denomination + ", банкноты: "
                    + quantity + ", номинал: " + denomination + ", баланс: " + balance());

            return "Вы успешно добавили " + quantity + " купюр(ы) номиналом "
                    + denomination + " грн. на сумму " + quantity * denomination + currency;
        }
    }

    /**
     * Withdraws specified sum from the current ATM's balance.
     *
     * @param sum
     * @return a string with result of operation;
     * @throws InsufficientFundsException on error.
     */
    @Override
    public synchronized String withdraw(final int sum) throws InsufficientFundsException {
        int maxAvailableSum = getMaxAvailableSumWithLimit(limit);
        String canNotWithdrawSum = "Невозможно выдать запрашиваемую сумму ";
        String message = canNotWithdrawSum + sum + currency + "\n";

        if (sum < denominations[denominations.length - 1]) {
            message += "\n" + "Минимальная сумма выдачи " + denominations[denominations.length - 1] + currency;
            throw new InsufficientFundsException(message);

        } else if (maxAvailableSum == 0) {
            message += "Отсутствуют номиналы для выдачи.";
            throw new InsufficientFundsException(message);

        } else if (!isSumCorrect(sum)) {
            message += "Сумма должна быть кратна 10" + currency;
            int approximateSum = (sum / 10) * 10;
            List<Integer[]> possibleChanges = getPossibleChanges(denominations, amounts,
                    new int[denominations.length], approximateSum, 0);

            if (possibleChanges.isEmpty()) {
                message += noAvailableChanges(approximateSum, possibleChanges);
            } else {
                int[] minimalChange = ArrayUtils.toPrimitive(getMinimalChange(possibleChanges));
                message += maxApproximateSum + getChangeSum(denominations, minimalChange) + currency;
            }
            throw new InsufficientFundsException(message);

        } else if (sum > maxAvailableSum) {
            message += maxApproximateSum + maxAvailableSum + currency;
            throw new InsufficientFundsException(message);

        } else {
            List<Integer[]> possibleChanges = getPossibleChanges(denominations, amounts,
                    new int[denominations.length], sum, 0);
            if (possibleChanges.isEmpty()) {
                message += noAvailableChanges(sum, possibleChanges);
                throw new InsufficientFundsException(message);
            }
            int[] minimalChange = ArrayUtils.toPrimitive(getMinimalChange(possibleChanges));
            message = "Сумма " + sum + currency + " будет выдана:" + "\n\n";
            String logInfo = "";

            for (int i = 0; i < denominations.length; i++) {
                int counter = minimalChange[i];
                if (counter > 0) {
                    message += "купюры: " + counter + ", номинал: " + denominations[i]
                            + currency + "\n";
                    logInfo += " купюры: " + counter + ", номинал: " + denominations[i] + ";";
                }
            }
            withdrawFromBalance(amounts, minimalChange);
            log.info("[WITHDRAW] " + sum + ";" + logInfo + " баланс: " + balance());
        }
        return message;
    }

    /**
     * Returns a current AutomatedTellerMachine's balance.
     *
     * @return a current balance.
     */
    @Override
    public int balance() {
        int result = 0;

        for (int i = 0; i < denominations.length; i++) {
            result += denominations[i] * amounts[i];
        }
        return result;
    }

    /**
     * Returns a current quantity of denominations.
     *
     * @return a current quantity of denominations.
     */
    @Override
    public String status() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < denominations.length; i++) {
            sb.append("Номинал: ")
                    .append(denominations[i])
                    .append(currency)
                    .append(", количество: ")
                    .append(amounts[i]).append("\n");
        }
        return String.valueOf(sb);
    }

    /**
     * Returns a maximum available sum according to withdraw limit.
     *
     * @param limit
     * @return a maximum available sum according to withdraw limit.
     * @throws IllegalArgumentException if limit is negative.
     */
    private int getMaxAvailableSumWithLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Лимит не может быть отрицательным.");
        }
        int result = 0;

        for (int i = 0; i < denominations.length; i++) {
            if (limit == 0) {
                break;
            } else if (amounts[i] > limit) {
                result += denominations[i] * limit;
                break;
            } else {
                result += denominations[i] * amounts[i];
                limit -= amounts[i];
            }
        }
        return result;
    }

    /**
     * Returns all possible variants that sum could be withdrawn.
     *
     * @param denominations
     * @param amounts
     * @param change
     * @param sum
     * @param position
     * @return all possible variants that sum could be withdrawn.
     */
    private List<Integer[]> getPossibleChanges(int[] denominations, int[] amounts,
                                               int[] change, int sum, int position) {
        List<Integer[]> possibleChanges = new ArrayList<>();
        int changeSum = getChangeSum(denominations, change);

        if (changeSum < sum) {
            for (int i = position; i < denominations.length; i++) {
                if (amounts[i] > change[i]) {
                    int[] newChange = change.clone();
                    newChange[i]++;
                    List<Integer[]> subChanges = getPossibleChanges(
                            denominations, amounts, newChange, sum, i);

                    if (subChanges != null) {
                        possibleChanges.addAll(subChanges);
                    }
                }
            }

        } else if (changeSum == sum) {
            possibleChanges.add(ArrayUtils.toObject(change));
        }
        return possibleChanges;
    }

    /**
     * Returns a minimal denominations' quantity that sum could be withdrawn.
     *
     * @param possibleChanges
     * @return a minimal denominations' quantity that sum could be withdrawn.
     */
    private Integer[] getMinimalChange(List<Integer[]> possibleChanges) {
        Map<Integer, Integer[]> map = new TreeMap<>();

        for (Integer[] change : possibleChanges) {
            int sum = 0;
            for (int i : change)
                sum += i;
            map.put(sum, change);
        }
        if (map.isEmpty()) {
            return new Integer[0];
        } else {
            return map.entrySet().iterator().next().getValue();
        }
    }

    /**
     * Returns a sum of specified denominations' quantity.
     *
     * @param denominations
     * @param change
     * @return a sum of specified denominations' quantity.
     */
    private int getChangeSum(int[] denominations, int[] change) {
        int result = 0;
        for (int i = 0; i < denominations.length; i++) {
            result += denominations[i] * change[i];
        }
        return result;
    }

    /**
     * Withdraws denominations from current balance.
     *
     * @param amounts
     * @param change
     */
    private void withdrawFromBalance(int[] amounts, int[] change) {
        for (int i = 0; i < denominations.length; i++) {
            amounts[i] -= change[i];
        }
    }

    /**
     * Returns position of specified value in array.
     *
     * @param array
     * @param value
     * @return position of specified value in array if succeed;
     * {@code -1} otherwise.
     */
    private int getArrayPosition(int[] array, int value) {
        int position = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * Holds a situation when a list of possible changes is empty.
     *
     * @param sum
     * @param possibleChanges
     * @return a string with next suitable sum.
     */
    private String noAvailableChanges(int sum, List<Integer[]> possibleChanges) {
        possibleChanges = tryNextSuitableSum(sum, possibleChanges);
        int[] minimalChange = ArrayUtils.toPrimitive(getMinimalChange(possibleChanges));

        return maxApproximateSum + getChangeSum(denominations, minimalChange) + currency;
    }

    /**
     * Returns a list of possible changes calculated from the next suitable sum.
     *
     * @param sum
     * @param possibleChanges
     * @return a list of possible changes calculated from the next suitable sum.
     */
    private List<Integer[]> tryNextSuitableSum(int sum, List<Integer[]> possibleChanges) {
        while (possibleChanges.isEmpty() && sum != 0) {
            sum -= 10;
            possibleChanges = getPossibleChanges(denominations, amounts,
                    new int[denominations.length], sum, 0);
        }
        return possibleChanges;
    }

    /**
     * Checks if input is multiple of 10.
     *
     * @param sum
     * @return {@code true} on success;
     * {@code false} otherwise.
     */
    private boolean isSumCorrect(int sum) {
        return sum % 10 == 0;
    }

    /**
     * Returns an array of properties.
     *
     * @param property
     * @return an array of properties.
     */
    private int[] propertiesLoader(String property) {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        String[] propertiesArray = properties.get(property).toString().split(",");
        int[] results = new int[propertiesArray.length];

        for (int i = 0; i < propertiesArray.length; i++) {
            try {
                results[i] = Integer.parseInt(propertiesArray[i]);
            } catch (NumberFormatException nfe) {
                throw new RuntimeException("ОШИБКА: Неверные значения в файле свойств!");
            }
        }
        return results;
    }
}