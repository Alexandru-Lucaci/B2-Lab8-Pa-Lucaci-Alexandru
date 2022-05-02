package Bonus;

import au.com.bytecode.opencsv.CSVReader;
import javax.sql.DataSource;
import java.sql.*;
import java.io.*;
import java.util.Locale;

import static Bonus.Main.dataSource;

//import au.com.bytecode.opencsv.CSVReader;
public class CityBonusDAO {


    public void create(String name) throws SQLException {
        Connection con = dataSource.getConnection();

//
        name=name.toUpperCase();
        try(PreparedStatement pstm = con.prepareStatement("insert into cityBonus (ID, NAME) values ((?), (?))")){
            Statement stmt = con.createStatement();
            String sql="select max(id) from cityBonus";
            ResultSet rs= stmt.executeQuery(sql);
            rs.next();
            int ids=rs.getInt("MAX(ID)");
            pstm.setInt(1,(ids+1));
            pstm.setString(2, name);
            pstm.executeUpdate();
            con.commit();


        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            con.close();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = dataSource.getConnection();
        name=name.toUpperCase();
        int ids=-1;
        try {
            Statement stmt = con.createStatement();
            String sql = "select id from cityBonus where name =\'" + name + "\' order by id";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

            ids = rs.getInt("ID");

            con.close();



        }catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            con.close();
            return ids;
        }
    }


    public String findById(int id) throws SQLException {
        Connection con = dataSource.getConnection();
        String ids=null;
        try {
            Statement stmt = con.createStatement();
            String sql = "select name from cityBonus where id =" + id;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            ids = rs.getString("NAME");
            con.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            con.close();
            return ids;
        }

    }


    public void seeAll() throws SQLException {
        Connection con = dataSource.getConnection();

        try {
            Statement stmt = con.createStatement();
            String sql="select * from cityBonus order by id";
            ResultSet rs= stmt.executeQuery(sql);
            System.out.println("ID\tName");
            while(rs.next())
            {

                int ids=rs.getInt("ID");
                String str= rs.getString("NAME");

                System.out.println(ids+"\t"+str);

            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            con.close();
        }
    }


}
