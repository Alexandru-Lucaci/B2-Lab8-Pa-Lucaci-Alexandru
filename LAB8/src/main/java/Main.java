import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try {
            var continents = new ContinentDAO();
            var countries = new CountriesDAO();

            continents.seeAll();
            System.out.println(continents.findByName("europa"));
//            continents.create("Europa");
//            continents.create("Asia");
//            continents.create("America");
//            continents.create("Africa");
            System.out.println(continents.findById(1));
//
//            countries.create("Germania", "Europa");
//            countries.create("Elvetia");
//            countries.create("luxemburg","europa");
//            countries.create("cehia","Europa",123);
//            countries.create("Moldova","EUROPA");
//            countries.create("bulgaria","europa",1234);
            System.out.println(countries.findByName("cehia"));
            System.out.println(countries.findById(4));
            countries.seeAll();

        }catch (Exception e)
        {
            System.out.println(e);

        }

    }
}
