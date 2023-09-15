package pmproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectVO {
	private int pj_num;
	private String pj_name; 
	private String pj_start_date; 
	private String pj_end_date; 
	private String pj_state;
	
	@Override
	public String toString() {
		
		
		if(pj_start_date == null) {
			return "������Ʈ�� : " + pj_name + ", ���۳�¥ : ����"  + 
					", ���ᳯ¥ : ���� " + ", ������Ʈ���� : " + pj_state;
		}
		if(pj_end_date == null) {
			return "������Ʈ�� : " + pj_name + ", ���۳�¥ : " + pj_start_date + 
					", ���ᳯ¥ : ���� " + ", ������Ʈ���� : " + pj_state;
		}
		
		return "������Ʈ�� : " + pj_name + ", ���۳�¥ : " + pj_start_date + 
				", ���ᳯ¥ : " + pj_end_date + ", ������Ʈ���� : " + pj_state;
	}

	public ProjectVO(String name, String start_date, String end_date, String state ) {
		
		this.pj_name = name;
		this.pj_start_date = start_date;
		this.pj_end_date = end_date;
		this.pj_state = state;
	}

	

	
}


