package com.college.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name", nullable = false, length = 100)
    private String name;

    @Column(name = "subject_code", nullable = false, unique = true, length = 20)
    private String code;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendanceList = new ArrayList<>();


    public List<Attendance> getAttendanceList() { return attendanceList; }
    public void setAttendanceList(List<Attendance> attendanceList) { this.attendanceList = attendanceList; }
    public Subject() {
    }

    public Subject(String name, String code, Course course) {
        this.name = name;
        this.code = code;
        this.course = course;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}