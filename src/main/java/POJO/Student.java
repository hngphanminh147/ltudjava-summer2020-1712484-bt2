package POJO;

import java.io.Serializable;

import lombok.Data;

//import lombok.ToString;

@Data
//@ToString(exclude = "image")
public class Student implements Serializable{
	//Student ID
	private String sId;
	private String name;
	private String gender;
	//Identity card NO.
	private String identity;
}
