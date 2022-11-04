package sampleapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDBConnection {
    private static SqliteDBConnection sqliteDBConnection;
    private final Connection connection;

    private SqliteDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection("jdbc:sqlite:D:\\pushpakumara\\Wixis\\backup\\fb_scrapping_web\\fb_scrapping_screen_shot\\SeleniumPro\\lib\\liteDBFb.db");

    }

    public static SqliteDBConnection getInstance() throws SQLException, ClassNotFoundException {
        return sqliteDBConnection = sqliteDBConnection != null ? sqliteDBConnection : new SqliteDBConnection();
    }

    public Connection getConnection(){
        return connection;
    }
}
