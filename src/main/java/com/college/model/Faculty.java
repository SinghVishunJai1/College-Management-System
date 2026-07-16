package com.college.model;

import jakarta.persistence.*;

@Entity
@Table(name = "faculties")
public class Faculty {
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faculty_name",nullable = false)
    private String name;

    private Double salary;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @PrePersist
    public void validateBeforeSaving(){
        System.out.println(">>> Hibernate LifeCycle: Saving Faculty -> "+this.name);
        if(this.salary == null || this.salary < 0){
            this.salary = 35000.0;
        }
    }


    public Faculty(){}
    public  Faculty(String name,Double salary,Department department){
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
}
