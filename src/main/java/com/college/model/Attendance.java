package com.college.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;


    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @CreationTimestamp
    @Column(name = "punch_time", updatable = false)
    private LocalDateTime punchTime;




    public Attendance() {}

    public Attendance(Student student, Subject subject, String status) {
        this.student = student;
        this.subject = subject;
        this.status = status;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getPunchTime() { return punchTime; }


}