package pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHUC_KHAO")
public class Reexamine implements Serializable{
	@Id
	@Column(name = "MA_LOP", length = 5)
	private String clID;
	@Id
	@Column(name = "MA_MON", length = 5)
	private String cID;
	@Id
	@Column(name = "MA_SV", length = 7)
	private String sID;
	@Column(name = "COT_DIEM")
	private int type;
	@Column(name = "DMM")
	private int expectScore;
	@Column(name = "LY_DO", length = 30)
	private String reason;
}
