package pmproject.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeoffVO {
	int tm_num;
	Date tm_date;
	String tm_reason;
	String tm_ep_id;

	@Override
	public String toString() {
		return     "퇴사 번호"+ ": " +tm_num +"\n"
				 + "퇴사 날짜 "+ ": " + tm_date +"\n"
		         + "사유 "+ ": " +tm_reason +"\n"
		         + "사번 "+ ": " +tm_ep_id +"\n";
	}

	public TimeoffVO(Date tm_date, String tm_reason, String tm_ep_id) {
		this.tm_date = tm_date;
		this.tm_reason = tm_reason;
		this.tm_ep_id = tm_ep_id;
	}
}
