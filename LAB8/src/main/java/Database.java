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
//            Statement stmt = con.createStatement();
//            String sql="select table_name from user_tables";
//            ResultSet rs= stmt.executeQuery(sql);
//            System.out.println(rs.toString());
//            while(rs.next())
//            {
//                String s = rs.getString("table_name");
//                System.out.println(s);
//            }

        }catch ( Exception e){
            System.out.println(e);
        }
    }
    public static Connection getConnection(){
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
