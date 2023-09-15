package pmproject.service;

public interface AttendanceService {
    void recordAttendance(int employeeId);
    void recordLeave(int employeeId);
    String viewAttendance(int employeeId);
    void changeAttendance(int employeeId, String newStatus);
    void applyLeave(int employeeId, String leaveType, String startDate, String endDate);
    String viewLeave(int employeeId);
}