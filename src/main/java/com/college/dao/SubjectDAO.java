package com.college.dao;

import com.college.model.Subject;
import com.college.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class SubjectDAO {

    public void saveSubject(Subject subject) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(subject);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Subject getSubjectById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Subject.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Subject getSubjectByCode(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Subject WHERE code = :subCode";
            Query<Subject> query = session.createQuery(hql, Subject.class);
            query.setParameter("subCode", code);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Subject> getSubjectsByCourse(Long courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Subject WHERE course.id = :cId";
            Query<Subject> query = session.createQuery(hql, Subject.class);
            query.setParameter("cId", courseId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}