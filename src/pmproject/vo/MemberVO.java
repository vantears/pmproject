package pmproject.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {

	private String ep_name, ep_email, ep_phone_num, ep_pos, ep_dept, ep_st;
	private int ep_id, ep_dm_num, ep_po_num, ep_st_num, ep_leave, ep_salary;
	
	public MemberVO(String name, String email, String phone, int salary) {
		ep_name = name;
		ep_email = email;
		ep_phone_num = phone;
		ep_salary = salary;
	}
	
	public MemberVO(int ep_id, String ep_name) {
		this.ep_id = ep_id;
		this.ep_name = ep_name;
	}
	
	public String toString() {
		
		return "\n" +
				"이름 : " + ep_name + "\n" +
				"이메일 : " + ep_email + "\n" +
				"연락처 : " + ep_phone_num + "\n" +
				"부서 : " + ep_dept + "\n" +
				"직급 : " + ep_pos + "\n" +
				"근무 상태 : " + ep_st + "\n" +
				"남은 연차 : "+ ep_leave + "\n" +
				"월급여(만원) : " + ep_salary + "\n";
	}

}
