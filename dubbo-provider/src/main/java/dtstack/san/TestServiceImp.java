package dtstack.san;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

/**
 * @description:
 * @author: 33
 * @time: 2019/10/23 21:09
 */

@Service
public class TestServiceImp implements TestService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void test() {
        System.out.println("---TestServiceImp test() --- 调用");
    }

    public String testString(String string) {
        System.out.println("---TestServiceImp test(+ " + string + ") --- 调用");
        return "输出：" + string;
    }

    public String getUser(int id) {

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        String sql = "select * from test_user where id = ?";
        if (jdbcTemplate != null) {
            List<User> query = jdbcTemplate.query(sql, rowMapper, 1);
            return query.toString();
        } else {
            return "jdbcTemplate is null and druidDataSource:";
        }

    }

    public List<User> getUsers() {
        return null;
    }

    @Override
    public String getUsersWithoutSchema(int id) {

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        String sql = "select * from tmp where id = ?";
        List<User> query = jdbcTemplate.query(sql, rowMapper,1);
        return query.toString();
    }

    @Override
    public String getUsersWithSchema(int id) {
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        String sql = "select * from test.user where id = ?";
        List<User> query = jdbcTemplate.query(sql, rowMapper,1);
        return query.toString();
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
