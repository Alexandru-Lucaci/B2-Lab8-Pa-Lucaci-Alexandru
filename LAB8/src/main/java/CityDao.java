import java.sql.*;

public class CityDao implements Workable{

    @Override
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
//
        name=name.toUpperCase();
        try(PreparedStatement pstm = con.prepareStatement("insert into cities values ((?), (?))")){



            Statement stmt = con.createStatement();
            String sql="select max(id) from cities";
            ResultSet rs= stmt.executeQuery(sql);

            rs.next();
            int ids=rs.getInt("MAX(ID)");

//                String s = rs.getString("table_name");
//            System.out.println(ids);
//            System.out.println(ids+1);
            pstm.setInt(1,(ids+1));
            pstm.setString(2, name);
//            System.out.println("i'm here");
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

    }
    public void create(String name, String country, int capital) throws SQLException {

    }
    public void create(String name, String country, int capital, float latitude, float longitude) throws SQLException {

    }

    public void create(String name, String country,float latitude, float longitude) throws SQLException {

    }
    @Override
    public Integer findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public String findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void seeAll() throws SQLException {

    }
}
