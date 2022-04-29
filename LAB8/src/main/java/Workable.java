import java.sql.SQLException;

public interface Workable {
    public void create(String name) throws SQLException;
    public Integer findByName(String name) throws SQLException;
    public String findById(int id) throws SQLException;

    public void seeAll() throws SQLException;
}
