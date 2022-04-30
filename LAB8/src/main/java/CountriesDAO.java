import java.sql.*;
import java.util.Locale;

public class CountriesDAO implements Workable {

    @Override
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
//
        name=name.toUpperCase();
        try(PreparedStatement pstm = con.prepareStatement("insert into countries (ID, NAME) values ((?), (?))")){

            Statement stmt = con.createStatement();
            String sql="select max(id) from countries";
            ResultSet rs= stmt.executeQuery(sql);

            rs.next();
            int ids=rs.getInt("MAX(ID)");

//                String s = rs.getString("table_name");
//            System.out.println(ids);
//            System.out.println(ids+1);
            pstm.setInt(1,(ids+1));
            pstm.setString(2, name);
            //System.out.println("i'm here");
            pstm.executeUpdate();
            con.commit();


        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            con.close();
        }
    }

    public void create(String name, String continent) throws SQLException {
        Connection con = Database.getConnection();
        //fac totul sa fie uppercase -- imi simplifica treaba
        name= name.toUpperCase();
        continent=continent.toUpperCase();


        boolean ok=true;// ok= true presupun ca acel continent exista
        var continents = new ContinentDAO();
        if(continents.findByName(continent)==-1){
            ok=false;}
//        System.out.println(ok);
        con = Database.getConnection();
        try(PreparedStatement pstm = con.prepareStatement("insert into countries (ID, NAME,CONTINENT) values ((?), (?), (?))")){

            Statement stmt = con.createStatement();
            String sql="select max(id) from countries";
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
                pstm.setString(3, continent);

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
    public void create(String name, String continent, int code) throws SQLException
    {
        create(name,continent);
        Connection con = Database.getConnection();
        String sql= "UPDATE countries SET CODE = "+code+" where name ='"+name.toUpperCase()+"'";
        Statement stm = con.createStatement();
        int row= stm.executeUpdate(sql);
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
            String sql = "select id from COUNTRIES where name =\'" + name + "\' order by id";
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
            String sql = "select name from countries where id =" + id;
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

    @Override
    public void seeAll() throws SQLException {
        Connection con = Database.getConnection();

        try {
            Statement stmt = con.createStatement();
            String sql="select * from countries order by id";
            ResultSet rs= stmt.executeQuery(sql);
            System.out.println("ID\tName\t\tCode\tContinent");
            while(rs.next())
            {

                int ids=rs.getInt("ID");
                String str= rs.getString("NAME");
                int cod=rs.getInt("CODE");
                String continent=rs.getString("CONTINENT");
                System.out.println(ids+"\t"+str+"\t"+cod+"\t"+continent);

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
