package POJO;

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
@Table(name = "PHUCKHAO")
public class Reexamine {
	@Id
	private String rId;
	private String sId;
	private String cId;
	private double expectScore;
	private int type;
	private String reason;
}
