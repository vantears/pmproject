package pmproject.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class TransferVO {
	
	private Date tr_date;
	private String tr_reason, tr_dept, tr_ep_id;
	private int tr_dm_num;
	
	public String toString() {
		return "[�̵��� : " + getTr_date_str() + " / " + "�̵����� : " + tr_reason + " / " + "�̵��� �μ� : " + tr_dept + "]"; 
	}
	
	public String getTr_date_str() {
		if(tr_date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(tr_date);
	}
}
