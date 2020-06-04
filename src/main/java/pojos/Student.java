package pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SINH_VIEN")
public class Student implements Serializable {
	@Id
	@Column(name = "MA_SV", length = 7, unique = true, nullable = false)
	private String sId;
	@Column(name = "MA_LOP", length = 5)
	private String clID;
	@Column(name = "TEN_SV", length = 20)
	private String name;
	@Column(name = "GIOI_TINH")
	private boolean gender;
	@Column(name = "CMND", length = 9)
	private String identity;
	@Column(name = "MK", length = 16)
	private String password;
}
