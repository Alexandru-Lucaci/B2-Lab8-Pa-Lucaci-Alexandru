import java.sql.*;
import java.util.concurrent.ExecutionException;

public class ContinentDAO implements Workable{

    @Override
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
//
        try(PreparedStatement pstm = con.prepareStatement("insert into continents values ((?), (?))")){



            Statement stmt = con.createStatement();
            String sql="select max(id) from continents";
            ResultSet rs= stmt.executeQuery(sql);

            rs.next();
            int ids=rs.getInt("MAX(ID)");

//                String s = rs.getString("table_name");
            System.out.println(ids);
            System.out.println(ids+1);
            pstm.setInt(1,(ids+1));
            pstm.setString(2, name);
            System.out.println("i'm here");
            pstm.executeUpdate();
            con.commit();


        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            con.close();
        }
    }

    @Override
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        int ids=-1;
        try {
            Statement stmt = con.createStatement();
            String sql = "select id from continents where name =\'" + name + "\' order by id";
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
            String sql = "select name from continents where id =" + id;
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
            String sql="select * from continents order by id";
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

