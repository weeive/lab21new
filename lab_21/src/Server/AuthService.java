package Server;

import java.sql.Statement;

public interface AuthService {
    void start();

    String getNickByLoginPass(String login, String pass);
    void stop();

    Statement getStmt();
}
