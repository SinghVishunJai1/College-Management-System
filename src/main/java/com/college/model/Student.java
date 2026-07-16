package com.college.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Transient
    private String tempOtp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookIssue> issuedBooks = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Attendance> attendanceList = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String email, Date dob, Gender gender, Department department) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.department = department;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public String getTempOtp() { return tempOtp; }
    public void setTempOtp(String tempOtp) { this.tempOtp = tempOtp; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrollment> enrollments) { this.enrollments = enrollments; }

    public List<BookIssue> getIssuedBooks() { return issuedBooks; }
    public void setIssuedBooks(List<BookIssue> issuedBooks) { this.issuedBooks = issuedBooks; }

    public List<Attendance> getAttendanceList() { return attendanceList; }
    public void setAttendanceList(List<Attendance> attendanceList) { this.attendanceList = attendanceList; }
}