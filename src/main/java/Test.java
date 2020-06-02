import java.util.List;

import daos.TimetableDAO;
import pojos.Timetable;

public class Test {
	public static void main(String[] args) {
		List<Timetable> list = new TimetableDAO().getAll();
		list.forEach(i -> System.out.println(i));
	}
}
