package com.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.attendance.entity.Attendance;
import com.attendance.entity.Employee;
import com.attendance.repo.AttendanceRepo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepository;
    
    

    @Autowired
    private RestTemplate restTemplate;

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByEmployeeId(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
    
    public List<Attendance> getAllAttendance() {
		return attendanceRepository.findAll();
	}

    public Employee getEmployeeById(Long employeeId) {
        return restTemplate.getForObject("http://employee-service/employee/" + employeeId, Employee.class);
    }


    public Attendance fetchAttendanceById(Long id) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        return optionalAttendance.orElse(null);
    }

    public List<Attendance> fetchAllAttendance() {
        return attendanceRepository.findAll();
    }

    public int getTotalAttendanceForMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        return attendanceRepository.countByTodaysDateBetween(startDate, endDate);
    }
    
   

    public Map<String, Long> getTotalHoursByEmployee() {
        List<Attendance> attendances = attendanceRepository.findAll();
        Map<String, Long> totalHoursByEmployee = new HashMap<>();
        
        for (Attendance attendance : attendances) {
            Employee employee = restTemplate.getForObject("http://employee-service/employee/" + attendance.getEmployeeId(), Employee.class);
            long hours = Duration.between(attendance.getInTime(), attendance.getOutTime()).toHours();
            
            // Use employee.getEmpName() as the key
            totalHoursByEmployee.merge(employee.getEmpName(), hours, Long::sum);
        }
        
        return totalHoursByEmployee;
    }

//    public Map<String, Long> getTotalHoursByEmployee() {
//        List<Attendance> attendances = attendanceRepository.findAll();
//        Map<String, Long> totalHoursByEmployee = new HashMap<>();
//        
//        for (Attendance attendance : attendances) {
//            Employee employee = restTemplate.getForObject("http://employee-service/employee/" + attendance.getEmployeeId(), Employee.class);
//            long hours = Duration.between(attendance.getInTime(), attendance.getOutTime()).toHours();
//            
//            // Use employee.getId().toString() as the key
//            totalHoursByEmployee.merge(employee.getId().toString(), hours, Long::sum);
//        }
//        
//        return totalHoursByEmployee;
//    }

   

    private long calculateHoursWorked(Attendance attendance) {
        if (attendance.getInTime() != null && attendance.getOutTime() != null) {
            Duration duration = Duration.between(attendance.getInTime(), attendance.getOutTime());
            return duration.toHours();
        }
        return 0;
    }

//    private long calculateHoursWorked(Attendance attendance) {
//        // Implement the logic to calculate hours worked
//        // This is a placeholder example, you need to adapt it to your logic
//        return attendance.getOutTime().toSecondOfDay() - attendance.getInTime().toSecondOfDay();
//    }

    public String updateAttendance(Long id, Attendance updatedAttendance) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        if (optionalAttendance.isPresent()) {
            Attendance existingAttendance = optionalAttendance.get();
            // Update fields only if they are not null in the updatedAttendance
            
            if (updatedAttendance.getInTime() != null) {
                existingAttendance.setInTime(updatedAttendance.getInTime());
            }
            if (updatedAttendance.getOutTime() != null) {
                existingAttendance.setOutTime(updatedAttendance.getOutTime());
            }
            if (updatedAttendance.getTodaysDate() != null) {
                existingAttendance.setTodaysDate(updatedAttendance.getTodaysDate());
            }
            attendanceRepository.save(existingAttendance);
            return "Updated successfully";
        } else {
            return "Record not found";
        }
    }

    public String deleteAttendance(Long id) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        if (optionalAttendance.isPresent()) {
            attendanceRepository.deleteById(id);
            return "Deleted successfully";
        } else {
            return "Record not found";
        }
    }

    
	public Attendance save(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	
}

//to calculate total hours
//    public Map<String, Long> getTotalHoursByEmployee() {
//        List<Attendance> attendanceList = attendanceRepository.findAll();
//        Map<String, Long> totalHoursByEmployee = new HashMap<>();
//
//        for (Attendance attendance : attendanceList) {
//            long hoursWorked = calculateHoursWorked(attendance);
//            totalHoursByEmployee.merge(attendance.getEmpName(), hoursWorked, Long::sum);
//        }
//
//        return totalHoursByEmployee;
//    }
//
//    private long calculateHoursWorked(Attendance attendance) {
//        if (attendance.getInTime() != null && attendance.getOutTime() != null) {
//            Duration duration = Duration.between(attendance.getInTime(), attendance.getOutTime());
//            return duration.toHours();
//        }
//        return 0;
//    }

