import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Database {
    private static final String url="jdbc:oracle:thin:@//localhost:1521/XE";
    private static final String user="LAB8";
    private static final String password="PAROLA";
    private static Connection con=null;
    private Database(){}

    public static void createConnection()
    {
        try{
            con = DriverManager. getConnection(url,user,password);
            con.setAutoCommit(false);
//            System.out.println("connection done");

        }catch ( Exception e){
            System.out.println(e);
        }
    }
    public static Connection getConnection(){
        closeConnection();
        createConnection();
        return con;
    }

    public static void closeConnection(){
        if(con!=null)
        {
            try{
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
        }
        }
    }

}
