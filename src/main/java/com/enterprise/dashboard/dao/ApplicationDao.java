package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ApplicationDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public Application getById(int id) {
        Session session = sessionFactory.openSession();
        Application application = session.get( Application.class, id );
        session.close();
        return application;
    }
    public List<Application> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Application> criteriaQuery = criteriaBuilder.createQuery(Application.class);
        Root<Application> root = criteriaQuery.from(Application.class);
        List<Application> applications = session.createQuery(criteriaQuery).getResultList();
        logger.debug("The list of Applications " + applications);
        session.close();
        return applications;
    }
    public void delete(Application application) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(application);
        transaction.commit();
        session.close();
    }
    public void updateOrSave(Application application) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(application);
        transaction.commit();
        session.close();
    }
    public int insert(Application application) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(application);
        transaction.commit();
        session.close();
        return id;
    }
}
