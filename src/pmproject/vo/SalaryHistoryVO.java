package pmproject.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class SalaryHistoryVO {
	private Date sh_payday, sh_ep_id;
	private int sh_salary;
	private String sh_pa_type, sh_type_detail;
	
	public String toString() {
		return "[지급일 : " + getSh_payday_str() + " / " + "급여항목 : " + sh_pa_type + " / " + "급여 상세 : " + sh_type_detail + " / " + "급여액 : " + sh_salary + "]"; 
	}
	
	public String getSh_payday_str() {
		if(sh_payday == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(sh_payday);
	}
}
