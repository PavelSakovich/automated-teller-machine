## ATM Web Application

Stores a certain amount of money in banknotes of 20, 50, 100, 200, 500;
ATM can not store more than 20 bills of the same value at the same time;
Stores values and quantities in the Properties file.
*Uses Servlets, Jetty, Log4j, JUnit, Maven.*

### Develop an algorithm for money withdraw
- The amount must be withdrawn with a minimum number of bills;
- The number of bills should not exceed 10;
- If the amount is not possible to withdraw, a message should be shown about the amount as close as possible to the requested amount;
- Write a unit test to test the algorithm.

### Put money (denomination / amount)
- When adding money, banknotes are added to the already existing amount;
- Write to the log file.

### Withdraw money (denomination / quantity)
- When withdrawing the banknote it should be removed from the system;
- Write to the log file.

### Logging
- Create one file per day;
- The file name must contain the current date;
- Logging should consist of time, type of transaction and additional information (banknote / denomination);
- The system should allow you to download all the logs in one zip archive.

![Demonstration](https://github.com/ksergey12/automated-teller-machine/blob/master/atm.gif)
