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
			return "프로젝트 번호 : " + pj_num + ", 프로젝트명 : " + pj_name + ", 시작날짜 : 미정"  + 
					", 종료날짜 : 미정 " + ", 프로젝트상태 : " + pj_state;
		}
		if(pj_end_date == null) {
			return "프로젝트 번호 : " + pj_num + ", 프로젝트명 : " + pj_name + ", 시작날짜 : " + pj_start_date + 
					", 종료날짜 : 미정 " + ", 프로젝트상태 : " + pj_state;
		}
		
		return "프로젝트 번호 : " + pj_num + ", 프로젝트명 : " + pj_name + ", 시작날짜 : " + pj_start_date + 
				", 종료날짜 : " + pj_end_date + ", 프로젝트상태 : " + pj_state;
	}

	public ProjectVO(String name) {
		this.pj_name = name;
	}

	public ProjectVO(String name, String start_date) {
		this.pj_name = name;
		this.pj_start_date = start_date;
	}

	public ProjectVO(String name, String start_date, String end_date) {
		this.pj_name = name;
		this.pj_start_date = start_date;
		this.pj_end_date = end_date;
	}

	

	
}