package com.bionic.friendsphotos.modelstructure;

import com.bionic.friendsphotos.model.Connector;
import javafx.scene.control.Tab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by c265 on 07.07.2015.
 */

public class Groups {
    final String DB_NAME = "sql482691";
    final String INSERT_ID = "INSERT INTO " + DB_NAME + ".groups (id, name, type, pass, creator_id) VALUES (NULL, NULL, 0, NULL, NULL);";
    final String GET_LAST_INSERT_ID = "select last_insert_id();";
    final String SELECT_ALL_FROM_TABLE_GROUPS = "SELECT * FROM groups";
    ArrayList<TableGroup> groups = new ArrayList<TableGroup>();
    Statement statement;
    ResultSet resultSet;
    Connection connection;
    TableGroup objTableGroups;
    int lastInsertId;

    public int getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(int lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    public void createGroup() {
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_ID);
            resultSet = statement.executeQuery(GET_LAST_INSERT_ID);
            while (resultSet.next()) {
                setLastInsertId(resultSet.getInt(1));
            }
            System.out.println("\n\n" + getLastInsertId() + "\n\n");
        } catch (SQLException ex) {/*NOP*/}
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {/*NOP*/}
        }
    }

    public ArrayList<TableGroup> selectAllFromTableGroups() {
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_FROM_TABLE_GROUPS);
            while (resultSet.next()) {
                objTableGroups = new TableGroup();
                objTableGroups.setId(resultSet.getInt(1));
                objTableGroups.setName(resultSet.getString(2));
                objTableGroups.setType(resultSet.getInt(3));
                objTableGroups.setPassword(resultSet.getString(4));
                objTableGroups.setCreatorId(resultSet.getString(5));
                groups.add(objTableGroups);
            }
            System.out.println("\n\n" + getLastInsertId() + "\n\n");
        } catch (SQLException ex) {/*NOP*/}
        finally {
            try {
                connection.close();
            }catch (SQLException ex) {/*NOP*/}
        }
        return groups;
    }

    public ArrayList<TableUser> getUsersInGroup(int groupId) {

        // Тут id пристроїв, які належать до групи
        // По ним ми витягуємо всю інформацію з таблиці users
        ArrayList<String> usersDeviceId = new ArrayList<String>();

        // Об'єкти всіх користувачів даної групи
        ArrayList<TableUser> users = new ArrayList<TableUser>();

        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();


            // 1) З таблиці users_in_grouos отримуємо id пристроїв, що належать групі (в масив usersDeviceId)
            resultSet = statement.executeQuery("SELECT * FROM users_in_groups WHERE group_id= " + groupId);
            while (resultSet.next()) {
                usersDeviceId.add(resultSet.getString(1));
            }

            // 2) По id пристрою з таблиці users отримуємо інформацію, створюємо об'єкт і заносимо в users
            for(String user: usersDeviceId){
                resultSet = statement.executeQuery("SELECT * FROM users WHERE id_device = '" + user +"'");
                while (resultSet.next()) {
                    users.add(new TableUser(resultSet.getString(1), resultSet.getString(2)));
                }
            }
            // TODO: Зробити одним запитом ?
            // TODO: Обробка exception
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