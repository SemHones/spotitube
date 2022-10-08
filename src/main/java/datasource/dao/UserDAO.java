package datasource.dao;

import datasource.objects.User;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserDAO extends DefaultDAO{
    private String selectUsers =  "select * from spotitube.user";

    private User user;

    public UserDAO(){ super(); }

    public User getSingleUser(String username, String password){
        user = new User(0, "", "", "");
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(selectUsers + " where username = '" + username + "' and password = '" + password + "'");
            rs = pstmt.executeQuery();
            while (rs.next()){
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("token"),
                    rs.getString("password")
                );
                this.user = user;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
        return this.user;
    }

    public User getSingleUserByToken(String token){
        user = new User(0, "", "", "");
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(selectUsers + " where token = '" + token + "'");
            rs = pstmt.executeQuery();
            while (rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("token"),
                        rs.getString("password")
                );
                this.user = user;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
        return this.user;
    }


}
