package com.college;

import com.college.service.CourseService;
import com.college.service.StudentService;
import com.college.service.LibraryService;
import com.college.service.AttendanceService;
import com.college.model.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        LibraryService libraryService = new LibraryService();
        AttendanceService attendanceService = new AttendanceService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("=============================================");
        System.out.println(" College Management System ");
        System.out.println("=============================================");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Academic Management");
            System.out.println("2. Student Management");
            System.out.println("3. Library Management");
            System.out.println("4. Attendance Management");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\n1. Add Dept | 2. Add Course | 3. Add Faculty | 4. View Faculty Details | 5. View All Departments");
                        int acadChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (acadChoice == 1) {
                            System.out.print("Dept Name: ");
                            String name = scanner.nextLine();
                            System.out.print("HOD Name: ");
                            String hod = scanner.nextLine();
                            courseService.createDepartment(new Department(name, hod));
                            System.out.println("Success!");
                        } else if (acadChoice == 2) {
                            System.out.print("Course Name: ");
                            String cName = scanner.nextLine();
                            System.out.print("Duration (Years): ");
                            int duration = scanner.nextInt();
                            courseService.addNewCourse(new Course(cName, duration, null));
                            System.out.println("Success!");
                        } else if (acadChoice == 3) {
                            System.out.print("Faculty Name: ");
                            String fName = scanner.nextLine();
                            System.out.print("Salary: ");
                            double salary = scanner.nextDouble();
                            courseService.hireFaculty(new Faculty(fName, salary, null));
                            System.out.println("Success!");
                        } else if (acadChoice == 4) {
                            System.out.print("Enter Faculty ID: ");
                            Long fId = scanner.nextLong();
                            scanner.nextLine();
                            courseService.displayFacultyDetails(fId);
                        } else if (acadChoice == 5) {
                            List<Department> depts = courseService.findAllDepartments();
                            if (depts == null || depts.isEmpty()) {
                                System.out.println("No Departments Found!");
                            } else {
                                System.out.println("\n--- Registered Departments ---");
                                for (Department d : depts) {
                                    System.out.println("ID: " + d.getId() + " | Name: " + d.getName() + " | HOD: " + d.getHod());
                                }
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n1. Register Student | 2. Update Address | 3. View Student Details | 4. View All Departments");
                        int studChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (studChoice == 1) {
                            System.out.println("\n--- Available Departments in System ---");
                            List<Department> depts = courseService.findAllDepartments();
                            if (depts == null || depts.isEmpty()) {
                                System.out.println("(No departments available! Please create one first.)");
                            } else {
                                for (Department d : depts) {
                                    System.out.println("- " + d.getName());
                                }
                            }
                            System.out.println("---------------------------------------");

                            System.out.print("Name: ");
                            String sName = scanner.nextLine();
                            System.out.print("Email: ");
                            String email = scanner.nextLine();

                            System.out.print("Enter DOB (yyyy-MM-dd): ");
                            String dobInput = scanner.nextLine();
                            Date dobDate;
                            try {
                                dobDate = new SimpleDateFormat("yyyy-MM-dd").parse(dobInput);
                            } catch (Exception e) {
                                throw new RuntimeException("Invalid Date Format! Use yyyy-MM-dd (e.g., 2004-12-15).");
                            }

                            System.out.print("Gender (MALE/FEMALE/OTHER): ");
                            String genderInput = scanner.nextLine().toUpperCase();
                            Gender selectedGender;
                            try {
                                selectedGender = Gender.valueOf(genderInput);
                            } catch (IllegalArgumentException e) {
                                throw new RuntimeException("Invalid Gender! Must be MALE, FEMALE, or OTHER.");
                            }

                            System.out.print("Enter Department Name from list above: ");
                            String deptName = scanner.nextLine();
                            Department dept = courseService.findDepartmentByName(deptName);

                            if (dept != null) {
                                Student newStudent = new Student(sName, email, dobDate, selectedGender, dept);

                                System.out.println("\n--- Enter Student Address ---");
                                System.out.print("City: ");
                                String city = scanner.nextLine();
                                System.out.print("State: ");
                                String state = scanner.nextLine();
                                System.out.print("Pincode: ");
                                String pin = scanner.nextLine();

                                Address address = new Address(city, state, pin, newStudent);
                                newStudent.setAddress(address);

                                studentService.registerStudent(newStudent);
                                System.out.println("Success! Student Registered with Profile and Address.");
                            } else {
                                System.out.println("Department Not Found!");
                            }
                        } else if (studChoice == 2) {
                            System.out.print("Student ID: ");
                            Long sId = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("City: ");
                            String city = scanner.nextLine();
                            System.out.print("State: ");
                            String state = scanner.nextLine();
                            System.out.print("Pincode: ");
                            String pin = scanner.nextLine();
                            studentService.updateStudentAddress(sId, city, state, pin);
                            System.out.println("Success!");
                        } else if (studChoice == 3) {
                            System.out.print("Enter Student ID: ");
                            Long sId = scanner.nextLong();
                            scanner.nextLine();
                            studentService.displayStudentDetails(sId);
                        } else if (studChoice == 4) {
                            List<Department> depts = courseService.findAllDepartments();
                            if (depts == null || depts.isEmpty()) {
                                System.out.println("No Departments Found!");
                            } else {
                                System.out.println("\n--- Registered Departments ---");
                                for (Department d : depts) {
                                    System.out.println("- " + d.getName());
                                }
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n1. Add Book | 2. Issue Book");
                        int libChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (libChoice == 1) {
                            System.out.print("Title: "); String title = scanner.nextLine();
                            System.out.print("Author: "); String author = scanner.nextLine();
                            System.out.print("ISBN: "); String isbn = scanner.nextLine();
                            System.out.print("Copies: "); int copies = scanner.nextInt();
                            libraryService.registerNewBook(new LibraryBook(title, author, isbn, copies, copies));
                            System.out.println("Success!");
                        } else if (libChoice == 2) {
                            System.out.print("Book ID: ");
                            Long bId = scanner.nextLong();
                            System.out.print("Student Email: ");
                            scanner.nextLine();
                            String email = scanner.nextLine();
                            Student stud = studentService.findStudentByEmail(email);
                            if (stud != null) {
                                libraryService.issueBookToStudent(bId, stud);
                                System.out.println("Success!");
                            } else {
                                System.out.println("Student Not Found!");
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n1. Punch Attendance | 2. View Percentage");
                        int attChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (attChoice == 1) {
                            System.out.print("Status (PRESENT/ABSENT): ");
                            String status = scanner.nextLine();
                            attendanceService.recordAttendance(new Attendance(null, null, status));
                            System.out.println("Success!");
                        } else if (attChoice == 2) {
                            System.out.print("Student ID: "); Long sId = scanner.nextLong();
                            System.out.print("Subject ID: "); Long subId = scanner.nextLong();
                            double pct = attendanceService.calculateSubjectAttendancePercentage(sId, subId);
                            System.out.println("Attendance: " + pct + "%");
                        }
                        break;

                    case 5:
                        System.out.println("Exiting System...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("---------------------------------------------");
        }
    }
}