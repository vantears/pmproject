package pmproject.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class AttendanceVO {
	private int ad_num, ad_at_num;
	private Date ad_date;
	private String ad_ep_id;
	
	public String getAd_date_str() {
		if(ad_date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(ad_date);
	}
	
}
