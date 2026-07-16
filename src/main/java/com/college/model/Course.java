package com.college.model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "course_name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "duration_years", nullable = false)
    private Integer durationYears;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Subject> subjects = new ArrayList<>();
    public Course() {
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrollment> enrollments) { this.enrollments = enrollments; }

    public Course(String name, Integer durationYears, Department department) {
        this.name = name;
        this.durationYears = durationYears;
        this.department = department;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getDurationYears() { return durationYears; }
    public void setDurationYears(Integer durationYears) { this.durationYears = durationYears; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }


}
