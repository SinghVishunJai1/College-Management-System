package com.college.dao;

import com.college.model.BookIssue;
import com.college.model.LibraryBook;
import com.college.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class LibraryBookDAO {

    public void saveBook(LibraryBook book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateBook(LibraryBook book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void saveBookIssue(BookIssue issue) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(issue);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void updateBookIssue(BookIssue issue) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(issue);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public LibraryBook getBookById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LibraryBook.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LibraryBook getBookByIsbn(String isbn) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM LibraryBook WHERE isbn = :bookIsbn";
            Query<LibraryBook> query = session.createQuery(hql, LibraryBook.class);
            query.setParameter("bookIsbn", isbn);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BookIssue getBookIssueById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(BookIssue.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LibraryBook> getAllBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM LibraryBook";
            return session.createQuery(hql, LibraryBook.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}