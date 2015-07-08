package com.bionic.friendsphotos.model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by c265 on 07.07.2015.
 */
public class FriendsPhotoDB {
    final String INSERT_ID = "INSERT INTO sql482691.groups (id, name, type, pass, creator_id) VALUES (NULL, NULL, 0, NULL, NULL);";
    final  String GET_LAST_INSERT_ID = "select last_insert_id();";
    final String SELECT_ALL_FROM_TABLE_GROUPS = "SELECT * FROM groups";
    ArrayList<TableGroups> groups = new ArrayList<TableGroups>();
    Statement statement;
    ResultSet resultSet;
    Connection connection;
    TableGroups objTableGroups;
    int lastInsertId;

    public int getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(int lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    void createGroup() {
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

    ArrayList<TableGroups> selectAllFromTableGroups() {
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_FROM_TABLE_GROUPS);
            while (resultSet.next()) {
                objTableGroups = new TableGroups();
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
}