package cn.catgod.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/9
 */
public class HikariDemo {


    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource db;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/configTest");
        config.setUsername("root");
        config.setPassword("930320");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        db = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return db.getConnection();
    }

    @Test
    public void name() {

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from CLE_PAY_ORDER_NEW");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String payOrderNo = resultSet.getString("PAY_ORDER_NO");
                int bizId = resultSet.getInt("BUSINESS_TYPE_ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
