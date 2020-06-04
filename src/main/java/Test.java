import java.util.List;

import daos.ClassCourseDetailDAO;
import pojos.ClassCourseDetail;

public class Test {
	public static void main(String[] args) {
		List<ClassCourseDetail> list = (new ClassCourseDetailDAO()).getAll();
		list.forEach((c) -> {System.out.println(c);});
	}
}
