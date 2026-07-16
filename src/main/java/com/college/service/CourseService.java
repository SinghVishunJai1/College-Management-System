package com.college.service;

import com.college.dao.SubjectDAO;
import com.college.dao.FacultyDAO;
import com.college.dao.DepartmentDAO;
import com.college.dao.CourseDAO;
import com.college.model.Subject;
import com.college.model.Faculty;
import com.college.model.Department;
import com.college.model.Course;
import java.util.List;

public class CourseService {

    private SubjectDAO subjectDAO = new SubjectDAO();
    private FacultyDAO facultyDAO = new FacultyDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private CourseDAO courseDAO = new CourseDAO();

    // --- Course Management Methods ---

    public void addNewCourse(Course course) {
        Course existing = courseDAO.getCourseByName(course.getName());
        if (existing != null) {
            throw new RuntimeException("Course with this name already exists!");
        }
        if (course.getDurationYears() != null && course.getDurationYears() <= 0) {
            throw new RuntimeException("Course duration must be greater than 0 years!");
        }
        courseDAO.saveCourse(course);
    }

    public Course findCourseById(Long id) {
        return courseDAO.getCourseById(id);
    }

    public List<Course> findCoursesByDepartment(Long departmentId) {
        return courseDAO.getCoursesByDepartment(departmentId);
    }

    // --- Department Management Methods ---

    public void createDepartment(Department department) {
        Department existing = departmentDAO.getDepartmentByName(department.getName());
        if (existing != null) {
            throw new RuntimeException("Department with this name already exists!");
        }
        departmentDAO.saveDepartment(department);
    }

    public Department findDepartmentByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }

    public List<Department> findAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    // --- Subject Management Methods ---

    public void addSubject(Subject subject) {
        Subject existing = subjectDAO.getSubjectByCode(subject.getCode());
        if (existing != null) {
            throw new RuntimeException("Subject code already exists!");
        }
        subjectDAO.saveSubject(subject);
    }

    public Subject findSubjectByCode(String code) {
        return subjectDAO.getSubjectByCode(code);
    }

    public List<Subject> findSubjectsInCourse(Long courseId) {
        return subjectDAO.getSubjectsByCourse(courseId);
    }

    // --- Faculty Management Methods ---

    public void hireFaculty(Faculty faculty) {
        if (faculty.getSalary() != null && faculty.getSalary() < 0) {
            throw new RuntimeException("Salary cannot be negative!");
        }
        facultyDAO.saveFaculty(faculty);
    }

    public void updateFacultySalary(Long facultyId, Double newSalary) {
        if (newSalary == null || newSalary < 0) {
            throw new RuntimeException("Invalid salary amount!");
        }
        Faculty faculty = facultyDAO.getFacultyById(facultyId);
        if (faculty != null) {
            faculty.setSalary(newSalary);
            facultyDAO.updateFaculty(faculty);
        } else {
            throw new RuntimeException("Faculty member not found!");
        }
    }

    public List<Faculty> findFacultiesByDepartment(Long departmentId) {
        return facultyDAO.getFacultiesByDepartment(departmentId);
    }

    public void displayFacultyDetails(Long facultyId) {
        Faculty faculty = facultyDAO.getFacultyById(facultyId);

        if (faculty == null) {
            throw new RuntimeException("Faculty member not found with ID: " + facultyId);
        }

        System.out.println("\n========= 👨‍🏫 FACULTY PROFILE =========");
        System.out.println("ID         : " + faculty.getId());
        System.out.println("Name       : " + faculty.getName());
        System.out.println("Salary     : " + faculty.getSalary());

        if (faculty.getDepartment() != null) {
            System.out.println("Department : " + faculty.getDepartment().getName());
            System.out.println("HOD of Dept: " + faculty.getDepartment().getHod());
        } else {
            System.out.println("Department : Not Assigned");
        }
        System.out.println("=======================================");
    }
}