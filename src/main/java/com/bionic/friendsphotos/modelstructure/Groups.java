package com.bionic.friendsphotos.modelstructure;

import com.bionic.friendsphotos.model.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Created by c265 on 07.07.2015.
 */

public class Groups {
    final String DB_NAME = "sql482691";
    Statement statement;
    ResultSet resultSet;
    Connection connection;

    public int createGroup() {
        int groupId = 0;
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO " + DB_NAME + ".groups (id, name, type, pass, creator_id) VALUES (NULL, NULL, 0, NULL, NULL);"
            );
            resultSet = statement.executeQuery("SELECT last_insert_id()");
            while (resultSet.next()) {
                groupId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groupId;
    }

    public void saveGroup(int groupId, String groupName, String creatorId){
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE " + DB_NAME + ".groups SET name = '" + groupName + "'," +
                    "creator_id =  '" + creatorId + "' WHERE  groups.id = " + groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPassword(int groupId, String password){
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE " + DB_NAME + ".groups SET type = 1," +
                    "pass =  '" + password + "' WHERE  groups.id = " + groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TableGroup> getAllGroups() {
        ArrayList<TableGroup> groups = new ArrayList<TableGroup>();
        TableGroup objTableGroups;
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM groups");
            while (resultSet.next()) {
                groups.add(new TableGroup(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getByte(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groups;
    }

    public ArrayList<TableUser> getUsersInGroup(int groupId) {
        ArrayList<String> usersDeviceId = new ArrayList<String>();
        ArrayList<TableUser> users = new ArrayList<TableUser>();

        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM users_in_groups WHERE group_id= " + groupId);
            while (resultSet.next()) {
                usersDeviceId.add(resultSet.getString(1));
            }

            for(String user: usersDeviceId){
                resultSet = statement.executeQuery("SELECT * FROM users WHERE id_device = '" + user +"'");
                while (resultSet.next()) {
                    users.add(new TableUser(resultSet.getString(1), resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}