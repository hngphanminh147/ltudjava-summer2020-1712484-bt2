package pojos;

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
@Table(name = "TAI_KHOAN")
public class Member {
	@Id
	@Column(name = "MA_SV", length = 7, unique = true, nullable = false)
	private String username;
	@Column(name = "MK", length = 16, nullable = false)
	private String password;
}
