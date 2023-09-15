package pmproject.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AttendanceServiceImp implements AttendanceService {
    private Map<Integer, String> attendanceStatusMap = new HashMap<>();
    private Map<Integer, String> leaveStatusMap = new HashMap<>();

    @Override
    public void recordAttendance(int employeeId) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        attendanceStatusMap.put(employeeId, "출근 기록: " + formattedDateTime);
    }

    @Override
    public void recordLeave(int employeeId) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        attendanceStatusMap.put(employeeId, "퇴근 기록: " + formattedDateTime);
    }

    @Override
    public String viewAttendance(int employeeId) {
        String attendanceStatus = attendanceStatusMap.get(employeeId);
        if (attendanceStatus != null) {
            return attendanceStatus;
        } else {
            return "출결 기록이 없습니다.";
        }
    }

    @Override
    public void changeAttendance(int employeeId, String newStatus) {
        attendanceStatusMap.put(employeeId, newStatus);
    }

    @Override
    public void applyLeave(int employeeId, String leaveType, String startDate, String endDate) {
        leaveStatusMap.put(employeeId, "휴가 신청: " + leaveType + " (" + startDate + " ~ " + endDate + ")");
    }

    @Override
    public String viewLeave(int employeeId) {
        String leaveStatus = leaveStatusMap.get(employeeId);
        if (leaveStatus != null) {
            return leaveStatus;
        } else {
            return "휴가 신청 내역이 없습니다.";
        }
    }
}