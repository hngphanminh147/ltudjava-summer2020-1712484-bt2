package POJO;

import java.io.Serializable;

import lombok.Data;

//import lombok.ToString;

@Data
//@ToString(exclude = "image")
public class Student implements Serializable{
	private String sId;
	private String name;
	private String birthDate;
	private String gender;
	private String image;
}
