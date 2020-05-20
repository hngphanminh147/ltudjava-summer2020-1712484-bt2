package POJO;

import java.io.Serializable;

import lombok.Data;

@Data
public class Course  implements Serializable{
	private String sId;
	private String name;
	private String teacher;
	private String startDate;
	private String endDate;
	private String description;
}
