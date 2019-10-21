package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.ErrorData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ErrorDataDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get Book by id
     */
    public ErrorData getById(int id) {
        Session session = sessionFactory.openSession();
        ErrorData errorData = session.get( ErrorData.class, id );
        session.close();
        return errorData;
    }

    /**
     * update ErrorData
     * @param errorData  Book to be inserted or updated
     */
    public void saveOrUpdate(ErrorData errorData) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(errorData);
        transaction.commit();
        session.close();
    }

    /**
     * insert ErrorData
     * @param errorData  ErrorData to be inserted
     */
    public int insert(ErrorData errorData) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(errorData);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a ErrorData
     * @param errorData ErrorData to be deleted
     */
    public void delete(ErrorData errorData) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(errorData);
        transaction.commit();
        session.close();
    }


    /** Return a list of all Books
     *
     * @return All Books
     */
    public List<ErrorData> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ErrorData> query = builder.createQuery( ErrorData.class );
        Root<ErrorData> root = query.from( ErrorData.class );
        List<ErrorData> errors = session.createQuery( query ).getResultList();

        logger.debug("The list of ErrorData " + errors);
        session.close();

        return errors;
    }

}
