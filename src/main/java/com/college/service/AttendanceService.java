package com.college.service;

import com.college.dao.AttendanceDAO;
import com.college.model.Attendance;
import java.util.List;

public class AttendanceService {

    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public void recordAttendance(Attendance attendance) {
        String status = attendance.getStatus();
        if (status == null || (!status.equals("PRESENT") && !status.equals("ABSENT"))) {
            throw new RuntimeException("Invalid Attendance Status! Must be PRESENT or ABSENT.");
        }
        attendanceDAO.saveAttendance(attendance);
    }

    public List<Attendance> getStudentAttendanceReport(Long studentId) {
        return attendanceDAO.getAttendanceByStudent(studentId);
    }

    public double calculateSubjectAttendancePercentage(Long studentId, Long subjectId) {
        List<Attendance> records = attendanceDAO.getAttendanceByStudentAndSubject(studentId, subjectId);
        if (records == null || records.isEmpty()) {
            return 0.0;
        }

        long presentCount = records.stream()
                .filter(r -> "PRESENT".equals(r.getStatus()))
                .count();

        return ((double) presentCount / records.size()) * 100;
    }
}