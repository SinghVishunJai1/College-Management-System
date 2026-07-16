package com.college.dao;

import com.college.model.Course;
import com.college.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class CourseDAO {

    public void saveCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Course getCourseById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Course.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Course getCourseByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Course WHERE name = :courseName";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("courseName", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> getCoursesByDepartment(Long departmentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Course WHERE department.id = :deptId";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("deptId", departmentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}