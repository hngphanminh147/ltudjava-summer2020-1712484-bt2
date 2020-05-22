package POJO;

import java.io.Serializable;

import lombok.Data;

@Data
public class Course  implements Serializable{
	//Course ID
	private String cId;
	private String name;
	private String room;
}
