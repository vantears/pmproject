package pmproject.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalaryVO {

	private String pa_type;
	
	public SalaryVO(String salaryType) {
		pa_type = salaryType;
	}
}
