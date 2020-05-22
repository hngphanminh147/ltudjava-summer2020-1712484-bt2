package POJO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Getter
	private String username;
	@Getter(AccessLevel.PROTECTED)
	private String password;
	private String priviledge;
}
