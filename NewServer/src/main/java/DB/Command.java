package DB;

import Filling.*;
import GeneralTools.Information;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PriorityQueue;
import java.util.Queue;

import static Filling.MessageHandling.UserList;

public class Command {
    static Statement statement;
    static ResultSet resultSet;
    static Queue<StudyGroup> StudyGroupPriorityQueue;

    public synchronized static void readdb() throws Exception {
        statement = Connect.connection.createStatement();
        resultSet = statement.executeQuery("select count from mytabl");
        resultSet.next();
        int size = resultSet.getInt("count");
        String sql = "SELECT * FROM mytabl";
        resultSet = statement.executeQuery(sql);
        StudyGroupPriorityQueue = new PriorityQueue<StudyGroup>(size, XMLReader.countComparator);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String count = resultSet.getString(3);
            String exp = resultSet.getString(4);
            String form = resultSet.getString(5);
            String semestr = resultSet.getString(6);
            String gA = resultSet.getString(7);
            String height = resultSet.getString(8);
            String weight = resultSet.getString(9);
            String eyeColor = resultSet.getString(10);
            String X = resultSet.getString(11);
            String Y = resultSet.getString(12);
            String User = resultSet.getString(13);
            String color = resultSet.getString(14);
            StudyGroup studyGroup = new StudyGroup(StudyGroupPriorityQueue, name, count, exp, form, semestr, gA, height, weight, eyeColor, X, Y, User, color);
            studyGroup.setId(id);
            StudyGroupPriorityQueue.add(studyGroup);
            // Filling.MessageHandling.StudyGroupPriorityQueue.add(studyGroup);
            User user = new User();
            user.login = User;
            user.UserColor = color;
            UserList.add(user);
        }
        MessageHandling.StudyGroupPriorityQueue = StudyGroupPriorityQueue;

    }

    public synchronized static String isExistingUser(String login, String password) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("SELECT * FROM logpass WHERE login = ?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String loginUser = resultSet.getString(2);
            String databasePass = resultSet.getString(1);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            byte[] passwordBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            String inputPass = new String(passwordBytes, StandardCharsets.UTF_8);
            if (inputPass.equals(databasePass)) {
                return loginUser;
            } else {
                return "NOTHING";
            }
        } else {
            return "NOTHING";
        }
    }

    public static synchronized boolean isExistingLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("SELECT * FROM logpass WHERE login = ?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return true;
        else return false;
    }

    public synchronized static void registrationUser(String login, String password) throws NoSuchAlgorithmException, SQLException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
        byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("INSERT INTO logpass VALUES ( ?, ?)");
        preparedStatement.setString(2, login);
        String hash = new String(bytes, StandardCharsets.UTF_8);
        preparedStatement.setString(1, hash);
        preparedStatement.execute();
        preparedStatement.close();

    }


    public synchronized static void add(String name, String count, String exp, String form, String semester, String gA, String height, String weight, String eyeColor, String X, String Y, Information information) throws SQLException {
        try {
            statement = Connect.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement = Connect.connection.prepareStatement("INSERT INTO mytabl VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
        preparedStatement.setInt(1, generate_id());
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, Integer.parseInt(count));
        preparedStatement.setString(4, exp);
        preparedStatement.setString(5, form);
        preparedStatement.setInt(6, Integer.parseInt(semester));
        preparedStatement.setString(7, gA);
        preparedStatement.setDouble(8, Double.parseDouble(height));
        preparedStatement.setInt(9, Integer.parseInt(weight));
        preparedStatement.setString(10, eyeColor);
        preparedStatement.setFloat(11, Float.parseFloat(X));
        preparedStatement.setDouble(12, Double.parseDouble(Y));
        preparedStatement.setString(13, information.login);
        final String[] color = new String[1];
        UserList.stream().forEach(user -> {
            if (user.login.equals(information.login)) color[0] = user.UserColor;
        });
        preparedStatement.setString(14, String.valueOf(color[0]));


        try {
            preparedStatement.executeUpdate();
            readdb();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Integer generate_id() throws SQLException {
        PreparedStatement statement = Connect.connection.prepareStatement("select nextval('generate_id') ");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public synchronized static void update(String name, String count, String exp, String form, String semester, String gA, String height, String weight, String eyeColor, String X, String Y, String id) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("UPDATE mytabl SET  name = ?, count = ?, exp = ?, " +
                "form= ?, semester = ?, admin_name = ?," +
                " height = ?, weight = ?, eye_color = ?, " +
                "x = ?, y = ? " +
                "WHERE id = ?");
        try {
            Integer.parseInt(semester);
        } catch (Exception e) {
            if (semester.trim().equals("FIFTH")) semester = "5";
            if (semester.trim().equals("SIXTH")) semester = "6";
            if (semester.trim().equals("SEVENTH")) semester = "7";
            if (semester.trim().equals("EIGHTH")) semester = "8";
        }
        if (exp.trim().equals("не отчислен")) exp = "no";
        if (exp.trim().equals("отчислен")) exp = "yes";
        if (form.trim().equals("DISTANCE_EDUCATION")) form = "Distance";
        if (form.trim().equals("FULL_TIME_EDUCATION")) form = "Full time";
        if (form.trim().equals("EVENING_CLASSES")) form = "Evening";
        System.out.println(id+","+name+","+count+","+","+exp+","+form+","+semester+","+gA+","+height+","+weight+","+eyeColor+","+X+","+Y);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, Integer.parseInt(count));
        preparedStatement.setString(3, exp);
        preparedStatement.setString(4, form);
        preparedStatement.setInt(5, Integer.parseInt(semester));
        preparedStatement.setString(6, gA);
        preparedStatement.setDouble(7, Double.parseDouble(height));
        preparedStatement.setInt(8, Integer.parseInt(weight));
        preparedStatement.setString(9, eyeColor);
        preparedStatement.setFloat(10, Float.parseFloat(X));
        preparedStatement.setDouble(11, Double.parseDouble(Y));
        preparedStatement.setInt(12, Integer.parseInt(id));
        preparedStatement.execute();
        preparedStatement.close();
    }

    public synchronized static boolean checkLogin(Information information) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("SELECT * FROM mytabl WHERE id = ?");
        preparedStatement.setInt(1, Integer.parseInt(information.idstr));
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String DBlog = resultSet.getString(13);
        if (DBlog.equals(information.login)) return true;
        else return false;
    }

    public synchronized static boolean checkLogin(Information information, Integer id) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("SELECT * FROM mytabl WHERE id = ?");
        preparedStatement.setInt(1, Integer.parseInt(String.valueOf(id)));
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String DBlog = resultSet.getString(13);
        if (DBlog.equals(information.login)) return true;
        else return false;
    }

    public synchronized static void remove_any_by_form_of_education(Information information) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("DELETE FROM mytabl WHERE  form=? AND login=?");
        preparedStatement.setString(1, information.form.trim());
        preparedStatement.setString(2, information.login);
        preparedStatement.executeUpdate();
    }

    public synchronized static void remove_lower(Information information) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("DELETE FROM mytabl WHERE count<?  AND login=?");
        preparedStatement.setInt(1, Integer.parseInt(information.count));
        preparedStatement.setString(2, information.login);
    }

    public synchronized static void remove_head(Information information) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("SELECT * FROM mytabl WHERE count = (SELECT min(count) FROM mytabl)");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Integer id = resultSet.getInt(1);
        if (checkLogin(information, id)) {
            preparedStatement = Connect.connection.prepareStatement("DELETE FROM mytabl  WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            AllCmd.answer = "Элемент удален";
        } else AllCmd.answer = "Этот элемент вам не принадлежит, вы не можете его удалить";
    }

    public synchronized static void remove_by_id(Information information) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("DELETE FROM mytabl  WHERE id=?");
        preparedStatement.setInt(1, Integer.parseInt(information.idstr));
        preparedStatement.executeUpdate();
    }

    public synchronized static void clear(Information information) throws SQLException {
        PreparedStatement preparedStatement = Connect.connection.prepareStatement("DELETE FROM mytabl where login=?");
        preparedStatement.setString(1, information.login);
        preparedStatement.executeUpdate();
    }

}
