package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String dbHost = "reservalaboratorio.c4jjygdniibq.us-east-2.rds.amazonaws.com";
    private static final String dbPort = "3306";
    private static final String dbName = "db_reservalab";
    private static final String dbUserName = "admin";
    private static final String dbPassword = "adminadmin";
    private static Connection conexao;

    private Database() {
        
    }

    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed())
                conexao = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName, dbUserName, dbPassword);
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
