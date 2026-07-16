package com.college.dao;

import com.college.model.Address;
import com.college.model.Student;
import com.college.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class StudentDAO {

    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void saveAddress(Address address) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(address);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Student getStudentById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student getStudentByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Student WHERE email = :studEmail";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studEmail", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> getStudentsByDepartment(Long departmentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Student WHERE department.id = :deptId";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("deptId", departmentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}