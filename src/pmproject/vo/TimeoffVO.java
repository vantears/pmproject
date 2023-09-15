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
		return     "���/���� ��ȣ"+ ": " +tm_num +"\n"
				 + "���/���� ��¥ "+ ": " + tm_date +"\n"
		         + "������¥ "+ ": " +tm_return_date +"\n"
		         + "���� "+ ": " +tm_reason +"\n"
		         + "���� ��ȣ "+ ": " +tm_ep_id +"\n";
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
