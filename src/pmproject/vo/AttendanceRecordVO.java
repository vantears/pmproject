package pmproject.vo;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.Data;

@Data
public class AttendanceRecordVO {
	private int ar_ad_num;
	private LocalTime ar_st_time, ar_end_time;
	
	public String getAr_st_time_str() {
		if(ar_st_time == null) {
			return "";
		}
		String formattedDateTime2 = ar_st_time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		return formattedDateTime2;
	}
	
	public String getAr_end_time_str() {
		if(ar_end_time == null) {
			return "";
		}
		String formattedDateTime2 = ar_end_time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		return formattedDateTime2;
	}
}
