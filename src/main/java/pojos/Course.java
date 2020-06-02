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
@Table(name = "MON")
public class Course  implements Serializable{
	@Id
	@Column(name = "MA_MON", length = 5)
	private String cId;
	@Column(name = "TEN_MON", length = 30)
	private String name;
}
