package com.college.dao;

import com.college.model.Faculty;
import com.college.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class FacultyDAO {

    public void saveFaculty(Faculty faculty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(faculty);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateFaculty(Faculty faculty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(faculty);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Faculty getFacultyById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Faculty.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Faculty> getFacultiesByDepartment(Long departmentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Faculty WHERE department.id = :deptId";
            Query<Faculty> query = session.createQuery(hql, Faculty.class);
            query.setParameter("deptId", departmentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}