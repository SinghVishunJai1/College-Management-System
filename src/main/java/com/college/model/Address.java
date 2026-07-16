package com.college.model;


import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    String city;
    String state;
    String pincode;

    @OneToOne
    @JoinColumn(name = "student_id",unique = true )
    private Student student;

    public Address(){}
    public Address(String city,String state,String pincode,Student student){
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.student = student;
    }

}
