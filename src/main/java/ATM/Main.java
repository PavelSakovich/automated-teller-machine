package ATM;

/**
 * Created by User on 14.06.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Atm atm = new AtmImpl();
        atm.deposit(20, 5);
        atm.deposit(50, 5);
        atm.deposit(100, 5);
        atm.deposit(200, 5);
        atm.deposit(500, 5);
        System.out.println("---------------");

        atm.withdraw(4350);
        System.out.println("---------------");

        atm.status();

        String currentUsersHomeDir = System.getProperty("user.home");
        String logFolder = currentUsersHomeDir + "/logs";
        FolderZiper.zipFolder(logFolder, currentUsersHomeDir + "/logs.zip");
    }
}
