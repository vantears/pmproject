package pmproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project_infoVO {

	private int pi_num; 
	private String pi_ep_id; 
	private int pi_pj_num; 
	private String pi_role;
	@Override
	public String toString() {
		return "직원ID : " + pi_ep_id + ", 역할 : " + pi_role;
	}
	public Project_infoVO(String id, int num, String role) {
		this.pi_ep_id = id;
		this.pi_pj_num = num;
		this.pi_role = role;
	}
}
