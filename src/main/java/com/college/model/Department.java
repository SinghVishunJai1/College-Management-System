package com.college.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;
    @Column(name = "department_name",nullable = false,unique = true)
    private String name;
    @Column(name = "hod_name")
    private  String hod;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Faculty> faculties = new ArrayList<>();


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();
    public Department(){}
    public Department(String name,String hod){
        this.name = name;
        this.hod = hod;
    }
}
