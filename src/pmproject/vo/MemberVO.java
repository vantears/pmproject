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
				"�̸� : " + ep_name + "\n" +
				"�̸��� : " + ep_email + "\n" +
				"����ó : " + ep_phone_num + "\n" +
				"�μ� : " + ep_dept + "\n" +
				"���� : " + ep_pos + "\n" +
				"�ٹ� ���� : " + ep_st + "\n" +
				"���� ���� : "+ ep_leave + "\n" +
				"���޿�(����) : " + ep_salary + "\n";
	}

}
