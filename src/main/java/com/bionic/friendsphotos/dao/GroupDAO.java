package com.bionic.friendsphotos.dao;

import com.bionic.friendsphotos.entitie.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bionic.friendsphotos.entitie.Group;

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
        getCurrentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Group> getAll() {
        List<Group> groups = (List<Group>) getCurrentSession().createQuery("from Group").list();
        return groups;
    }

    public void deleteAll() {
        List<Group> entityList = getAll();
        for (Group entity : entityList) {
            delete(entity);
        }
    }

    public Group create(Group group) {
        // Todo: create
        return group;
    }

    public Group read(String id) {
        Group book = (Group) getCurrentSession().get(Group.class, id);
        return book;
    }

    public void update(Group group) {
        getCurrentSession().update(group);
    }

    public void delete(Group entity) {
        getCurrentSession().delete(entity);
    }

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
