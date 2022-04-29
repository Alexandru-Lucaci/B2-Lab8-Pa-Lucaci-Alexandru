import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try {
            var continents = new ContinentDAO();
//            continents.create("Europa");
            continents.seeAll();
            System.out.println(continents.findByName("name"));
            System.out.println(continents.findById(10));
        }catch (Exception e)
        {
            System.out.println(e);

        }

    }
}
