package ATM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ATM {

    private Map<Integer, MoneyCartridge> cartridges;
    private static final int CARTRIDGE_CAPACITY = 20;
    private static final int WITHDRAW_LIMIT = 10;
    private atmProperties properties;

//    private final int[] denominations = new int[]{
//            500, 200, 100, 50, 20
//    };
    private final int[] denominations;
    private final int[] initialQuantity;

    public ATM() {
        properties = new atmProperties();
        denominations = properties.getDenominations();
        initialQuantity = properties.getInitialQuantity();

        cartridges = new TreeMap<>(
                new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                });

        for (int denomination : denominations) {
            cartridges.put(denomination, new MoneyCartridge(CARTRIDGE_CAPACITY));
        }
    }

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
                        continue;
                    }
                } else {                                                    // нельзя выдать из текущей кассеты
                    continue;
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

//        for (int i = 0; i < denominations.length; i++) {
//            if (denominationsCounter > WITHDRAW_LIMIT) {
//                break;
//            } else {
//                change[i] = sum / denominations[i];
//                if(change[i] > WITHDRAW_LIMIT)
//                    break;
//                sum -= change[i] * denominations[i];
//                denominationsCounter += change[i];
//            }
//        }
//
//        if (sum == 0) {
//            System.out.println("Сумма в " + initialSum + " грн. будет выдана такими купюрами:");
//            for (int i = 0; i < denominations.length; i++) {
//                int counter = change[i];
//
//                if (counter > 0)
//                    System.out.println(counter + " " + denominations[i] + " грн.");
//            }
//            System.out.println("Количество купюр: " + denominationsCounter);
//        } else {
//            System.out.println("Невозможно выдать запрашиваемую сумму в " + initialSum + " грн.");
//            int minSum = 0;
//            for (int i = 0; i < denominations.length; i++) {
//                minSum += change[i] * denominations[i];
//            }
//            System.out.println("К выдаче возможна сумма в " + minSum + " грн.");
//        }
//    }

    public void withdrawDenomination(int denomination, int quantity) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        for (int j = 0; j < quantity; j++) {
            if (cartridge.isEmpty())
                break;
            else {
                cartridge.pop();
            }
        }
    }

    public int getTotalAvailableSum() {
        int result = 0;

        for (int denomination : denominations) {
            MoneyCartridge cartridge = cartridges.get(denomination);
            int quantity = cartridge.getPosition();
            result += quantity * denomination;
        }
        return result;
    }

    public int getAvailableSumOfDenomination(int denomination) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        int quantity = cartridge.getPosition();
        return quantity * denomination;
    }

    public int getAvailableQuantityOfDenomination(int denomination) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        return cartridge.getPosition();
    }

    public void info() {
        for (Map.Entry<Integer, MoneyCartridge> entry : cartridges.entrySet()) {
            System.out.println("Картридж [" + entry.getKey() + "]" + entry.getValue().toString());
        }
    }

    public atmProperties getProperties() {
        atmProperties properties = new atmProperties();
        Map<Integer, Integer> denominations = new HashMap<>();
        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/denomination.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        Enumeration enumeration = properties.keys();

        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            denominations.put(Integer.valueOf(key), Integer.valueOf(properties.getProperty(key)));
        }

        return denominations;
    }
}
