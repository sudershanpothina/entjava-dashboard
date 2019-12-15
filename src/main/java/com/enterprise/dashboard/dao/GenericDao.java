package com.enterprise.dashboard.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericDao<T> {
    private Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T) session.get( type, id );
        session.close();
        return entity;
    }
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
    public List<T> getAll() {

        return getEntities("", "");
    }
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }
    public List<T> getByProperty(String name, String value) {
        return getEntities(name, value);
    }

    private List<T> getEntities(String name, String value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from( type );
        if(name.length()!=0 && value.length()!=0) {
            query.select(root).where(builder.equal(root.get(name), value));
        }
        List<T> entities = session.createQuery( query ).getResultList();
        session.close();
        return entities;
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}
