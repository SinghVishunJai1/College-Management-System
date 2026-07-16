package com.college.service;

import com.college.dao.StudentDAO;
import com.college.model.Address;
import com.college.model.Course;
import com.college.model.Enrollment;
import com.college.model.Student;
import java.util.List;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    public void registerStudent(Student student) {
        Student existing = studentDAO.getStudentByEmail(student.getEmail());
        if (existing != null) {
            throw new RuntimeException("Email already registered!");
        }
        studentDAO.saveStudent(student);
    }

    public void enrollStudentInCourse(Long studentId, Course course) {
        Student student = studentDAO.getStudentById(studentId);
        if (student != null) {
            Enrollment enrollment = new Enrollment(student, course);
            student.getEnrollments().add(enrollment);
            studentDAO.updateStudent(student);
        }
    }

    public void updateStudentAddress(Long studentId, String city, String state, String pincode) {
        Student student = studentDAO.getStudentById(studentId);
        if (student != null) {
            if (pincode == null || pincode.length() != 6) {
                throw new RuntimeException("Invalid pincode! Must be 6 digits.");
            }
            Address address = new Address(city, state, pincode, student);
            studentDAO.saveAddress(address);
        }
    }

    public Student findStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }

    public List<Student> findStudentsByDepartment(Long departmentId) {
        return studentDAO.getStudentsByDepartment(departmentId);
    }

    public void displayStudentDetails(Long studentId) {
        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        System.out.println("\n========= 🎓 STUDENT PROFILE =========");
        System.out.println("ID         : " + student.getId());
        System.out.println("Name       : " + student.getName());
        System.out.println("Email      : " + student.getEmail());
        System.out.println("Gender     : " + student.getGender());
        System.out.println("DOB        : " + student.getDob());

        if (student.getDepartment() != null) {
            System.out.println("Department : " + student.getDepartment().getName());
        }

        if (student.getAddress() != null) {
            Address addr = student.getAddress();
            System.out.println("Address    : " + addr.getCity() + ", " + addr.getState() + " (" + addr.getPincode() + ")");
        } else {
            System.out.println("Address    : Not Updated Yet");
        }

        System.out.println("======================================");
    }
}