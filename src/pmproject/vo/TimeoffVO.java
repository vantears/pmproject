package pmproject.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeoffVO {
	int tm_num;
	Date tm_date;
	Date tm_return_date;
	String tm_reason;
	int tm_ep_id;

	@Override
	public String toString() {
		return     "퇴사/휴직 번호"+ ": " +tm_num +"\n"
				 + "퇴사/휴직 날짜 "+ ": " + tm_date +"\n"
		         + "복직날짜 "+ ": " +tm_return_date +"\n"
		         + "사유 "+ ": " +tm_reason +"\n"
		         + "직원 번호 "+ ": " +tm_ep_id +"\n";
	}

	public TimeoffVO(int tm_num, Date tm_date, Date tm_return_date, String tm_reason, int tm_ep_id) {
		this.tm_num = tm_num;
		this.tm_date = tm_date;
		this.tm_return_date = tm_return_date;
		this.tm_reason = tm_reason;
		this.tm_ep_id = tm_ep_id;
	}

	public TimeoffVO(Date tm_date, Date tm_return_date, String tm_reason, int tm_ep_id) {
		this.tm_date = tm_date;
		this.tm_return_date = tm_return_date;
		this.tm_reason = tm_reason;
		this.tm_ep_id = tm_ep_id;
	}

}
