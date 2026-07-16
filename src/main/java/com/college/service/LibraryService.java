package com.college.service;

import com.college.dao.LibraryBookDAO;
import com.college.model.BookIssue;
import com.college.model.LibraryBook;
import com.college.model.Student;
import java.time.LocalDateTime;
import java.util.List;

public class LibraryService {

    private LibraryBookDAO bookDAO = new LibraryBookDAO();

    public void registerNewBook(LibraryBook book) {
        LibraryBook existing = bookDAO.getBookByIsbn(book.getIsbn());
        if (existing != null) {
            throw new RuntimeException("Book with this ISBN already exists!");
        }
        bookDAO.saveBook(book);
    }

    public void issueBookToStudent(Long bookId, Student student) {
        LibraryBook book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found!");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is currently out of stock!");
        }

        BookIssue issue = new BookIssue(book, student);
        book.getIssueHistory().add(issue);
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        bookDAO.saveBookIssue(issue);
        bookDAO.updateBook(book);
    }

    public void returnBookFromStudent(Long issueId) {
        BookIssue issue = bookDAO.getBookIssueById(issueId);
        if (issue == null) {
            throw new RuntimeException("Issue record not found!");
        }
        if (!"ISSUED".equals(issue.getStatus())) {
            throw new RuntimeException("Book has already been returned!");
        }

        issue.setStatus("RETURNED");
        issue.setReturnDate(LocalDateTime.now());

        LibraryBook book = issue.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookDAO.updateBookIssue(issue);
        bookDAO.updateBook(book);
    }

    public LibraryBook findBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    public List<LibraryBook> findAllBooks() {
        return bookDAO.getAllBooks();
    }
}