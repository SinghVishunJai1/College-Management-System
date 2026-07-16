package com.college.dao;

import com.college.model.Attendance;
import com.college.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AttendanceDAO {

    public void saveAttendance(Attendance attendance) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(attendance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Attendance> getAttendanceByStudent(Long studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Attendance WHERE student.id = :studId";
            Query<Attendance> query = session.createQuery(hql, Attendance.class);
            query.setParameter("studId", studentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Attendance> getAttendanceByStudentAndSubject(Long studentId, Long subjectId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Attendance WHERE student.id = :studId AND subject.id = :subId";
            Query<Attendance> query = session.createQuery(hql, Attendance.class);
            query.setParameter("studId", studentId);
            query.setParameter("subId", subjectId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}