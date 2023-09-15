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
	Date tm_return_date;
	String tm_reason;
	String tm_ep_id;

	@Override
	public String toString() {
		return     "���/���� ��ȣ"+ ": " +tm_num +"\n"
				 + "���/���� ��¥ "+ ": " + tm_date +"\n"
		         + "������¥ "+ ": " +tm_return_date +"\n"
		         + "���� "+ ": " +tm_reason +"\n"
		         + "��� "+ ": " +tm_ep_id +"\n";
	}

	public TimeoffVO(Date tm_date, Date tm_return_date, String tm_reason, String tm_ep_id) {
		this.tm_date = tm_date;
		this.tm_return_date = tm_return_date;
		this.tm_reason = tm_reason;
		this.tm_ep_id = tm_ep_id;
	}
}
