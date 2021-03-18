package cn.catgod.jdbc;

import java.sql.*;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/18
 */
public class JDBCDemo {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/tianhe?useServerPrepStmts=true";
    private static final String user = "root";
    private static final String password = "930320";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {

//        Connection connection = DriverManager.getConnection(url, user, password);
//
//
//        System.out.println("Statement语句结果：");
//        Statement statement = connection.createStatement();
//        statement.execute("select * from person");
//
//        ResultSet resultSet = statement.getResultSet();
//        printResult(resultSet);
//        resultSet.close();
//        statement.close();
//

//        System.out.println("Statement语句结果：");
//        statement = connection.createStatement();
//        String input = "'tianhe' or 1=1";
//        statement.execute("select * from person where name=" + input);
//
//        resultSet = statement.getResultSet();
//        printResult(resultSet);
//        resultSet.close();
//        statement.close();


//        System.out.println("prepareStatement语句结果：");
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from person where name = ? ");
//        preparedStatement.setString(1, "tianhe");
//        preparedStatement.execute();
//        resultSet = preparedStatement.getResultSet();
//        printResult(resultSet);
//        preparedStatement.close();
//
//        System.out.println("prepareStatement语句结果：");
//        preparedStatement = connection.prepareStatement("select * from person where name = ? ");
//        preparedStatement.setString(1, input);
//        preparedStatement.execute();
//        resultSet = preparedStatement.getResultSet();
//        printResult(resultSet);
//        preparedStatement.close();


//        connection.close();

        updateTestAutoCommit();
    }


    private static void updateTestAutoCommit() throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);

        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();
        statement.execute("update person set name = 'test1' where id = 1");
        Savepoint savepoint1 = connection.setSavepoint("savePoint1");
        statement.execute("update person set name = 'test2' where id = 1");
        Savepoint savepoint2 = connection.setSavepoint("savePoint2");
        statement.execute("update person set name = 'test3' where id = 1");
        Savepoint savepoint3 = connection.setSavepoint("savePoint3");

        connection.rollback(savepoint1);
        connection.commit();
        connection.close();


    }

    private static void printResult(ResultSet resultSet) {
        try {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int column = metaData.getColumnCount();
            StringBuilder sb = new StringBuilder();
            while (resultSet.next()) {
                for (int i = 1; i <= column; i++) {
                    sb.append(metaData.getColumnName(i) + "=");
                    sb.append(resultSet.getString(i) + "\t");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
