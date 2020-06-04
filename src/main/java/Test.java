import java.util.List;

import daos.ClassCourseDetailDAO;
import daos.RecordDAO;
import daos.StudentDAO;
import pojos.ClassCourseDetail;
import pojos.Record;
import pojos.Student;

public class Test {
	public static void main(String[] args) {
		List<Record> records = new RecordDAO().gets("1712811");
		records.forEach(r -> System.out.println(r));
		
		Student s = new StudentDAO().get("1712811");
		System.out.println(s);
	}
}
