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
@Table(name = "lop")
public class Class implements Serializable{
	@Id
	@Column(name = "MA_LOP", length = 5, unique = true, nullable = false)
	private String clId;
	@Column(name = "TEN_LOP", length = 30)
	private String name;
}
