package ui.teacherform.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import constant.Constant;
import daos.ClassDAO;
import daos.CourseDAO;
import daos.StudentDAO;
import pojos.Class;
import pojos.Course;
import pojos.Student;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageStudentPanel extends JPanel implements MouseListener {
	private JLabel lblStudentList = new JLabel("Xem danh s\u00E1ch sinh vi\u00EAn");
	private JLabel lblClassChooser = new JLabel("Ch\u1ECDn l\u1EDBp");
	private JLabel lblCourseChooser = new JLabel("Ch\u1ECDn m\u00F4n");
	private JComboBox<String> cbClasses = new JComboBox<>();
	private JComboBox<String> cbCourses = new JComboBox<>();
	private JPanel panel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tblStudents;
	private final JButton btnSearch = new JButton("T\u00ECm ki\u1EBFm");

	public ManageStudentPanel() {
		getClasses();
		getCourses();
		getTable();
		setProperties();
		addComponents();
	}

	private void setProperties() {
		scrollPane = new JScrollPane(this.tblStudents);

		panel.setLayout(new MigLayout("", "[][grow][200]", "[][]"));

		setLayout(new MigLayout("", "[436px,grow]", "[16px][][266px]"));
	}

	private void addComponents() {
		panel.add(lblClassChooser, "cell 0 0,alignx trailing");
		panel.add(cbClasses, "cell 1 0,growx");
		panel.add(lblCourseChooser, "cell 0 1,alignx trailing");
		panel.add(cbCourses, "cell 1 1,growx");
		panel.add(btnSearch, "cell 2 1,alignx right");

		btnSearch.addMouseListener(this);

		add(lblStudentList, "cell 0 0,alignx left,aligny top");
		add(panel, "cell 0 1,grow");

		add(scrollPane, "cell 0 2,grow");
	}

	private void getClasses() {
		List<Class> classes = new ClassDAO().getAll();
		classes.forEach(c -> cbClasses.addItem(c.getClId() + " - " + c.getName()));
	}

	private void getCourses() {
		List<Course> courses = new CourseDAO().getAll();
		cbCourses.addItem(Constant.TEST);
		courses.forEach(c -> cbCourses.addItem(c.getCId() + " - " + c.getName()));
	}

	private void getTable() {
		String[] tblColumnNames = { "M\u00E3 s\u1ED1 sinh vi\u00EAn", "H\u1ECD v\u00E0 t\u00EAn", "L\u1EDBp",
				"Gi\u1EDBi t\u00EDnh" };
		if (tblStudents == null) {
			tblStudents = new JTable() {
				public boolean isCellEditable(int nRow, int nCol) {
					return false;
				}
			};
		}
		DefaultTableModel studentTableModel = (DefaultTableModel) tblStudents.getModel();
		studentTableModel.setColumnIdentifiers(tblColumnNames);
		tblStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void setupTableData() {
		DefaultTableModel tblStudentModel = (DefaultTableModel) tblStudents.getModel();
		tblStudentModel.setRowCount(0);

		String selectedClass = String.valueOf(cbClasses.getSelectedItem()).substring(0, 5);
		String selectedCourses = (cbCourses.getSelectedIndex() == 0) ? ""
				: String.valueOf(cbCourses.getSelectedItem()).substring(0, 5);
		List<Student> students = new ArrayList<>();
		if (selectedCourses.equals("")) {
//		if (selectedCourses.equals(Constant.NO)) {
			students = new StudentDAO().getByClass(selectedClass);
		} else {
			students = new StudentDAO().getByClassCourse(selectedClass, selectedCourses);
		}
		students.forEach(s -> {
			String[] row = new String[4];
			row[0] = s.getSId();
			row[1] = s.getName();
			row[2] = s.getClID();
			row[3] = (s.isGender()) ? "N\u1EEF" : "Nam";
			tblStudentModel.addRow(row);
		});
		tblStudentModel.fireTableDataChanged();
		tblStudents.setModel(tblStudentModel);
		tblStudents.repaint();
		tblStudentModel.fireTableDataChanged();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if (event.getSource() == btnSearch) {
			setupTableData();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
