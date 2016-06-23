package ATM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class AtmImpl implements Atm {

    private Map<Integer, MoneyCartridge> cartridges;
    private static int withdrawLimit;
    private String propertiesPath;
    private final int[] denominations;

    public AtmImpl() {
        propertiesPath = "src/main/resources/atm.properties";
        denominations = propertiesLoader("denominations");
        int[] initialQuantity = propertiesLoader("initialQuantity");
        withdrawLimit = propertiesLoader("withdrawLimit")[0];
        int cartridgeCapacity = propertiesLoader("cartridgeCapacity")[0];

        cartridges = new TreeMap<>(
                new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                });

        for (int i = 0; i < denominations.length; i++) {
            int denomination = denominations[i];
            cartridges.put(denomination, new MoneyCartridge(cartridgeCapacity, initialQuantity[i]));
        }
    }

    @Override
    public boolean deposit(int denomination, int quantity) {
        MoneyCartridge cartridge = cartridges.get(denomination);

        if (cartridge.isFull() || quantity > cartridge.getMaxSize()) {
            System.out.println("Невозможно добавить " + quantity
                    + " купюр(ы) номиналом " + denomination + " грн.");
            return false;
        } else {
            for (int i = 0; i < quantity; i++) {
                cartridge.push(1); // "1" if denomination exist
                cartridges.put(denomination, cartridge);
            }
            System.out.println("Вы успешно добавили " + quantity + " купюр(ы) номиналом "
                    + denomination + " грн. на сумму " + quantity * denomination + " грн.");
            return true;
        }
    }

    @Override
    public boolean withdraw(int sum) {
        int initialSum = sum;
        int[] change = new int[denominations.length];
        int denominationsCounter = 0;
        int availableSum = getTotalAvailableSum();

        if (sum > availableSum) { // вообще нельзя выдать
            System.out.println("Невозможно выдать запрашиваемую сумму " + initialSum + " грн.");
            System.out.println("Доступная к выдаче сумма " + availableSum + " грн.");
            return false;
        } else {                  // можно выдать
            for (int i = 0; i < denominations.length; i++) {

                if (getAvailableSumOfDenomination(denominations[i]) > sum) { // можно выдать из текущей кассеты
                    if (sum >= denominations[i]) {                            // сумма больше номинала
                        int withdrawalQuantity = sum / denominations[i];
                        denominationsCounter += withdrawalQuantity;
                        change[i] = withdrawalQuantity;
                        sum = sum - (withdrawalQuantity * denominations[i]);
                        withdrawDenomination(denominations[i], withdrawalQuantity);
                    } else {                                                // сумма меньше номинала
                        // no operation
                    }
                } else {                                               // нельзя выдать из текущей кассеты
                    // no operation
                }
            }
            System.out.println("Сумма " + initialSum + " грн. будет выдана такими купюрами:");
            for (int i = 0; i < denominations.length; i++) {
                int counter = change[i];

                if (counter > 0)
                    System.out.println(counter + " " + denominations[i] + " грн.");
            }
            System.out.println("Количество купюр: " + denominationsCounter);
        }

        return true;
    }

    private void withdrawDenomination(int denomination, int quantity) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        for (int j = 0; j < quantity; j++) {
            if (cartridge.isEmpty())
                break;
            else {
                cartridge.pop();
            }
        }
    }

    @Override
    public int getTotalAvailableSum() {
        int result = 0;

        for (int denomination : denominations) {
            MoneyCartridge cartridge = cartridges.get(denomination);
            int quantity = cartridge.getPosition();
            result += quantity * denomination;
        }
        return result;
    }

    @Override
    public int getAvailableSumOfDenomination(int denomination) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        int quantity = cartridge.getPosition();
        return quantity * denomination;
    }

    @Override
    public int getAvailableQuantityOfDenomination(int denomination) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        return cartridge.getPosition();
    }

    @Override
    public String info() {
        String result = "";

        for (Map.Entry<Integer, MoneyCartridge> entry : cartridges.entrySet()) {
            result += "Картридж [" + entry.getKey() + "]" + entry.getValue().toString() + "\n";
        }

        return result;
    }

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