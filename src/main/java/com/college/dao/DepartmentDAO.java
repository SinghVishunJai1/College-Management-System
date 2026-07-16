package com.college.dao;

import com.college.model.Department;
import com.college.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class DepartmentDAO {

    public void saveDepartment(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Department getDepartmentById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Department.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Department getDepartmentByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Department WHERE name = :deptName";
            Query<Department> query = session.createQuery(hql, Department.class);
            query.setParameter("deptName", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Department> getAllDepartments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Department";
            return session.createQuery(hql, Department.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}