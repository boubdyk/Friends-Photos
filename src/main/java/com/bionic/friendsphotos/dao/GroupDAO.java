package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bionic.friendsphotos.entity.Group;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by c265 on 14.07.2015.
 */
public class GroupDao {

    private Session currentSession;
    private Transaction currentTransaction;
   // SessionFactory sf;

    public GroupDao() {
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Group entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }

    public Group create(Group group) {
        // Todo: create
        return group;
    }

    public Group read(Integer id) {
        openCurrentSessionWithTransaction();
        Group group = (Group) getCurrentSession().get(Group.class, id);
        closeCurrentSessionwithTransaction();
        return group;
    }

    public void update(Group entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionwithTransaction();
    }

    public void delete(Group entity) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<Group> getAll() {
        openCurrentSession();
        List<Group> groups = (List<Group>) getCurrentSession().createQuery("from Group").list();
        closeCurrentSession();
        return groups;
    }

    public void deleteAll() {
        openCurrentSessionWithTransaction();
        List<Group> entityList = getAll();
        for (Group entity : entityList) {
            delete(entity);
        }
        closeCurrentSessionwithTransaction();
    }

    /*public Group findById(Integer id) {
        openCurrentSession();
        Group group = (Group) getCurrentSession().get(Group.class, id);
        closeCurrentSession();
        return group;
    }*/

    public ArrayList<User> getUsersInGroup(Group group){

        // Todo: getUsersInGroup
        ArrayList<User> users = new ArrayList<>(Arrays.asList(
                new User("QW123QWJLK", new BigInteger(String.valueOf(11111113))),
                new User("O2341UWHEJ", new BigInteger(String.valueOf(11111113)))
        ));

        return users;
    }

    public void addUser(Group group, User user){
        //Todo
    }

    public void deleteUser(Group group, User user){
        //Todo
    }

}
