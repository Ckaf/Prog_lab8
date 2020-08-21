package DB;

import Filling.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class Connect {

    private static String host="pg";
    private static String host1="localhost";
    private static int port1=9000;
    private static int port=5432;
    private static String tabl="studs.public";
    private static String db="studs";
    private static String url="jdbc:postgresql://"+host1+":"+port1+"/"+db;
    private static String user="s283914";
    private static String user1="postgres";
    private static String pass="lga852";
    static Connection connection;
    public static void connection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user1, pass);
            Logger.login(Level.INFO,"Подключение к БД прошло успешно");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
