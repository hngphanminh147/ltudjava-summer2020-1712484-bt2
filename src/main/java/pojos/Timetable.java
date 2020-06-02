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
@Table(name = "THOI_KHOA_BIEU")
public class Timetable implements Serializable{
	@Id
	@Column(name = "MA_LOP", length = 5)
	private String clID;
	@Id
	@Column(name = "MA_MON", length = 5)
	private String cID;
	@Column(name = "PHONG", length = 3)
	private String room;
}
