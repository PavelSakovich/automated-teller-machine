package ATM;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AtmImpl implements Atm {

    private Map<Integer, MoneyCartridge> cartridges;
    private int withdrawLimit;
    private String propertiesPath;
    private final int[] denominations;

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

    private static final Logger log = Logger.getLogger(Atm.class);

    public AtmImpl() {
        propertiesPath = "src/main/resources/atm.properties";
        denominations = propertiesLoader("denominations");
        int[] amounts = propertiesLoader("amounts");
        int cartridgeCapacity = propertiesLoader("cartridgeCapacity")[0];
        withdrawLimit = propertiesLoader("withdrawLimit")[0];

        cartridges = new TreeMap<>(
                new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                });

        for (int i = 0; i < denominations.length; i++) {
            int denomination = denominations[i];
            cartridges.put(denomination, new MoneyCartridge(cartridgeCapacity, amounts[i]));
        }
    }

    @Override
    public boolean deposit(int denomination, int quantity) {

        if (!checkInputDenomination(denomination)) {
            System.out.println("Указан неправильный номинал валюты.");
            return false;
        }

        MoneyCartridge cartridge = cartridges.get(denomination);

        if (cartridge.isFull() || quantity > cartridge.getCapacity()) {
            System.out.println("Невозможно добавить " + quantity
                    + " купюр(ы) номиналом " + denomination + " грн.");
            return false;
        } else {
            cartridge.put(quantity);
//                cartridges.put(denomination, cartridge);
            System.out.println("Вы успешно добавили " + quantity + " купюр(ы) номиналом "
                    + denomination + " грн. на сумму " + quantity * denomination + " грн.");

            log.info("[DEPOSIT] " + quantity * denomination
                    + ", банкноты: " + quantity + ", номинал: " + denomination);

            return true;
        }
    }

    @Override
    public boolean withdraw(int sum) {
        int initialSum = sum;
        int[] change = new int[denominations.length];
        int totalDenominationsCounter = 0;
        int availableSum = getTotalSum();

        if (sum > availableSum) { // сумму вообще нельзя выдать
            System.out.println("Невозможно выдать запрашиваемую сумму " + initialSum + " грн.");
            System.out.println("Доступная к выдаче сумма " + availableSum + " грн.");

            return false;
        } else {                  // сумму можно выдать
            for (int i = 0; i < denominations.length; i++) {

                if (getAvailableSumOfDenomination(denominations[i]) >= sum) { // сумму можно выдать из текущей кассеты
                    if (sum >= denominations[i]) {                            // сумма больше номинала
                        int withdrawalQuantity = sum / denominations[i];
                        totalDenominationsCounter += withdrawalQuantity;
                        change[i] = withdrawalQuantity;
                        sum = sum - (withdrawalQuantity * denominations[i]);
//                        withdrawDenomination(denominations[i], withdrawalQuantity);
                    } else {                                                // сумма меньше номинала
                        // no operation
                    }
                } else {                                               // сумму нельзя выдать из текущей кассеты
                    int withdrawalQuantity = sum / denominations[i];
                    change[i] = withdrawalQuantity;
                    totalDenominationsCounter += withdrawalQuantity;
                    sum -= withdrawalQuantity * denominations[i];
//                    withdrawDenomination(denominations[i], withdrawalQuantity);
                }
            }
            System.out.println("Сумма " + initialSum + " грн. будет выдана:");
            String additionalInfo = "";

            for (int i = 0; i < denominations.length; i++) {
                int counter = change[i];

                if (counter > 0) {
                    System.out.println("банкноты: " + counter + ", номинал: " + denominations[i] + " грн.");
                    additionalInfo += " банкноты: " + counter + ", номинал: " + denominations[i] + ";";
                }
            }
            System.out.println("Количество купюр: " + totalDenominationsCounter);

            log.info("[WITHDRAW] " + initialSum + "," + additionalInfo);
        }

        return true;
    }

    @Override
    public int getTotalSum() {
        int result = 0;

        for (int denomination : denominations) {
            MoneyCartridge cartridge = cartridges.get(denomination);
            int quantity = cartridge.getCurrentQuantity();
            result += quantity * denomination;
        }
        return result;
    }

//    @Override
//    public int getAvailableSumWithLimit() {
//        int result = 0;
//        int counter = 0;
//
//        for (int denomination : denominations) {
//            MoneyCartridge cartridge = cartridges.get(denomination);
//            int quantity = cartridge.getCurrentQuantity();
//
//            if (quantity > withdrawLimit) {
//                break;
//            } else {
//
//            }
//            counter += quantity;
//            result += quantity * denomination;
//        }
//        return result;
//    }

    @Override
    public int getAvailableSumOfDenomination(int denomination) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        int quantity = cartridge.getCurrentQuantity();
        return quantity * denomination;
    }

    @Override
    public int getAvailableQuantityOfDenomination(int denomination) {
        MoneyCartridge cartridge = cartridges.get(denomination);
        return cartridge.getCurrentQuantity();
    }

    @Override
    public String status() {
        String result = "";

        for (Map.Entry<Integer, MoneyCartridge> entry : cartridges.entrySet()) {
            result += "Картридж [" + entry.getKey() + "]" + entry.getValue().toString() + "\n";
        }

        return result;
    }

//    private void withdrawDenomination(int denomination, int quantity) {
//        MoneyCartridge cartridge = cartridges.get(denomination);
//        for (int j = 0; j < quantity; j++) {
//            if (cartridge.isEmpty())
//                break;
//            else {
//                cartridge.pop();
//                System.out.println("[" + denomination + "] " + cartridge.getPosition());
//            }
//        }
//    }

    private boolean checkInputDenomination(int denomination) {
        for (int i : denominations) {
            if (i == denomination) {
                return true;
            }
        }
        return false;
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