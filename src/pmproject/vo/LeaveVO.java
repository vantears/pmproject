package pmproject.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveVO {
	
	public LeaveVO(String le_start_date, String le_end_date, String le_type, String le_ep_id) {
		this.le_start_date = le_start_date;
		this.le_end_date = le_end_date;
		this.le_type = le_type;
		this.le_ep_id = le_ep_id;
	}
	private int le_num;
	private String le_start_date, le_end_date;;
	private String le_type, le_ep_id;
	
	@Override
	public String toString() {
		return     "휴가 번호 : " +le_num +"\n"
				 + "휴가 시작 날짜 : " + le_start_date +"\n"
		         + "휴가 복귀 날짜 : " + le_end_date +"\n"
		         + "휴가 사유 : " + le_type +"\n"
		         + "사번 : " + le_ep_id + "\n";
	}
}