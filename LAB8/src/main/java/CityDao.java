import au.com.bytecode.opencsv.CSVReader;

import java.sql.*;
import java.io.*;
//import au.com.bytecode.opencsv.CSVReader;
public class CityDao implements Workable{

    @Override
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
//
        name=name.toUpperCase();
        try(PreparedStatement pstm = con.prepareStatement("insert into cities (ID, NAME) values ((?), (?))")){
            Statement stmt = con.createStatement();
            String sql="select max(id) from cities";
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
    public void create(String name, String country) throws SQLException {
        Connection con = Database.getConnection();
        //fac totul sa fie uppercase -- imi simplifica treaba
        name= name.toUpperCase();

        country=country.toUpperCase();


        boolean ok=true;// ok= true presupun ca acel continent exista
        var countries = new CountriesDAO();
        if(countries.findByName(country)==-1){
            ok=false;}
//        System.out.println(ok);
        con = Database.getConnection();

        try(PreparedStatement pstm = con.prepareStatement("insert into cities (ID, NAME,COUNTRY) values ((?), (?), (?))")){

            Statement stmt = con.createStatement();
            String sql="select max(id) from CITIES";
            ResultSet rs= stmt.executeQuery(sql);

            rs.next();
            int ids=rs.getInt("MAX(ID)");

//                String s = rs.getString("table_name");
//            System.out.println(ids);
//            System.out.println(ids+1);
            pstm.setInt(1,(ids+1));
            pstm.setString(2, name);

            if(ok==true) {
//                System.out.println("here");
                pstm.setString(3, country);

            }
            else
            {
                pstm.setString(3,null);
            }
            pstm.executeUpdate();

            con.commit();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            con.close();
        }
    }
    public void create(String name, String country, int capital) throws SQLException {
        create(name,country);
        Connection con = Database.getConnection();
        String sql= "UPDATE cities SET CAPITAL = "+capital+" where name ='"+name.toUpperCase()+"'";
        Statement stm = con.createStatement();
        int row= stm.executeUpdate(sql);
        con.commit();
        con.close();


    }
    public void create(String name, String country, int capital, float latitude, float longitude) throws SQLException {
        create(name,country,capital);
        Connection con = Database.getConnection();
        String sql= "UPDATE cities SET latitude = "+latitude+" where name ='"+name.toUpperCase()+"'";
        Statement stm = con.createStatement();
        int row= stm.executeUpdate(sql);
        sql="UPDATE cities SET longitude = "+longitude+" where name ='"+name.toUpperCase()+"'";
        row= stm.executeUpdate(sql);
        con.commit();
        con.close();
    }

    public void create(String name, String country,float latitude, float longitude) throws SQLException {
        create(name,country);
        Connection con = Database.getConnection();
        String sql= "UPDATE cities SET latitude = "+latitude+" where name ='"+name.toUpperCase()+"'";
        Statement stm = con.createStatement();
        int row= stm.executeUpdate(sql);
        sql="UPDATE cities SET longitude = "+longitude+" where name ='"+name.toUpperCase()+"'";
        row= stm.executeUpdate(sql);
        con.commit();
        con.close();
    }
    @Override
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        name=name.toUpperCase();
        int ids=-1;
        try {
            Statement stmt = con.createStatement();
            String sql = "select id from cities where name =\'" + name + "\' order by id";
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

    @Override
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        String ids=null;
        try {
            Statement stmt = con.createStatement();
            String sql = "select name from cities where id =" + id;
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
    public void getData()throws  Exception{
        Connection con = Database.getConnection();
        try{
             Statement stmt = con.createStatement();
            String sql1="select max(id) from countries";

            ResultSet rs= stmt.executeQuery(sql1);

            rs.next();
            int ids=rs.getInt("MAX(ID)");

            String jdbc_insert_sql = "INSERT INTO cities (NAME, COUNTRY, CAPITAL,LATITUDE,ID,LONGITUDE) VALUES ((?), (?),(?),(?),(?),(?))";
            PreparedStatement sql= con.prepareStatement(jdbc_insert_sql);
            String inputCSVFile="D:\\git\\B2-Lab8-Pa-Lucaci-Alexandru\\LAB8\\src\\main\\java\\concap.csv";
            CSVReader reader=new CSVReader(new FileReader(inputCSVFile));
            String [] nextLine;
            nextLine=reader.readNext();
            int lnNum=0;
            while((nextLine=reader.readNext())!=null){
                lnNum++;
                sql.setString(1,nextLine[1]);
                sql.setString(2,nextLine[0]);
                sql.setInt(3,1);
//                System.out.println("heer");
                sql.setDouble(4,Double.parseDouble(nextLine[2]));
//                System.out.println("heeere");
                sql.setInt(5,(ids+1));
                ids++;
                sql.setFloat(6,Float.parseFloat(nextLine[3]));
//                System.out.println("heeeere");
                sql.executeUpdate();

//                System.out.println("heeeere");
            }
            sql.close();
            con.commit();
//            con.close();


        }catch (Exception e){
            System.out.println(e);

        }
        finally {
            con.close();
        }


    }

    @Override
    public void seeAll() throws SQLException {
        Connection con = Database.getConnection();

        try {
            Statement stmt = con.createStatement();
            String sql="select * from cities order by id";
            ResultSet rs= stmt.executeQuery(sql);
            System.out.println("ID\tName\t\tCapital\tLatitude\tLongitude");
            while(rs.next())
            {

                int ids=rs.getInt("ID");
                String str= rs.getString("NAME");

                int capitala=rs.getInt("CAPITAL");
                float latitudine = rs.getFloat("LATITUDE");
                float longitudine = rs.getFloat("LONGITUDE");
                System.out.println(ids+"\t"+str+"\t"+capitala+"\t"+latitudine+"\t"+longitudine);

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
