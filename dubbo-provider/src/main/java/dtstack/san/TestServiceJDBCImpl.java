package dtstack.san;

import java.sql.*;
import java.util.List;

/**
 * <p>
 *
 * @author Threeboys33
 * @version 1.0.0
 **/
public class TestServiceJDBCImpl implements TestService {

    private String url = "jdbc:postgresql://rm-3ica36cjwo30na43t.ppas.rds.ops.xagjyx.com:3433/yaoku";
    private String username = "yaoku";
    private String password = "yaoku20191213";
    private Connection connection = null;

    public Connection getConn() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    //@Autowired
    //private JdbcTemplate jdbcTemplate;
    public void test() {
        System.out.println("---TestServiceImp test() --- 调用");
    }

    public String testString(String string) {
        System.out.println("---TestServiceImp test(+ " + string + ") --- 调用");
        return "输出：" + string;
    }

    public String getUser(int id) {

        //RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        String sql = "select * from test_user where id = ?";
        PreparedStatement ps = null;
        connection = getConn();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int num = resultSet.getInt(1);
                String name = resultSet.getString(2);
                return num + "," + name;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<User> getUsers() {
        return null;
    }

    @Override
    public String getUsersWithoutSchema(int id) {
        return null;
    }

    @Override
    public String getUsersWithSchema(int id) {

        return null;

    }

}
