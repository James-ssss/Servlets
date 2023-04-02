package org.example;

import jakarta.persistence.PersistenceException;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.Optional;

public class Userdao {

    public User get(String login) {
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user = currentSession.byNaturalId(User.class).using("login", login).load();
        transaction.commit();
        currentSession.close();
        return user;
    }

    public boolean save(User user) {
        boolean isSave = true;
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        try {
            currentSession.persist(user);
        } catch (PersistentObjectException e) {
            isSave = false;
        }
        transaction.commit();
        currentSession.close();

        return isSave;
    }

    public boolean update(User user) {
        boolean isSave = true;
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        try {
            currentSession.merge(user);
        } catch (PersistenceException e) {
            isSave = false;
        }

        transaction.commit();
        currentSession.close();

        return isSave;
    }

    public boolean delete(User user) {
        boolean isSave = true;
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        try {
            currentSession.remove(user);
        } catch (PersistentObjectException e) {
            isSave = false;
        }

        transaction.commit();
        currentSession.close();

        return isSave;
    }
}
