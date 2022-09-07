
import com.bankapp.Menu.CustomerMenu;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
        If want to create table through JDBC using Java then use the below method by providing the Table name
        and start the server for the first time after that Do Comment the method call so that it won't create problems
         */
//        AccountManager.createTable("customer");
        CustomerMenu.displayMenu();

    }
}