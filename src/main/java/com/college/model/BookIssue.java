package com.college.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_issues")
public class BookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private LibraryBook book;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    @CreationTimestamp
    @Column(name = "issue_date", updatable = false)
    private LocalDateTime issueDate;


    @Column(name = "return_date")
    private LocalDateTime returnDate;


    @Column(name = "status", nullable = false)
    private String status = "ISSUED";


    public BookIssue() {
    }


    public BookIssue(LibraryBook book, Student student) {
        this.book = book;
        this.student = student;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LibraryBook getBook() { return book; }
    public void setBook(LibraryBook book) { this.book = book; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public LocalDateTime getIssueDate() { return issueDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}