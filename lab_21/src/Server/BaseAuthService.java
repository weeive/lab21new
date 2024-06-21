package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseAuthService implements AuthService {
    final static String connectionUrl = "jdbc:sqlite:main.sqlite";
    private static Connection connection;
    private static Statement stmt;

    @Override
    public void start() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionUrl);
            stmt = connection.createStatement();
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }

    @Override
    public synchronized String getNickByLoginPass (String login, String pass) {
        String nickReturn = null;
        try {
            String query = "select * from user where login='" + login + "'and password='" + pass + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                nickReturn = rs.getString("name");
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (nickReturn.equals(" "))
            return null;
        return nickReturn;
    }

    public Statement getStmt () {
        return stmt;
    }
}
