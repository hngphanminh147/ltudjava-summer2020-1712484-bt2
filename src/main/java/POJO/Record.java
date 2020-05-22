package POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
	private String sId;
	private double midTermScore;
	private double finalScore;
	private double otherScore;
	private double grade;
}
