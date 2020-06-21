package ui.teacherform.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import constant.Constant;
import daos.ClassCourseDetailDAO;
import daos.ClassDAO;
import daos.CourseDAO;
import daos.StudentDAO;
import pojos.Class;
import pojos.ClassCourseDetail;
import pojos.Course;
import pojos.Student;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
	private final JLabel lblRemove = new JLabel("X\u00F3a sinh vi\u00EAn kh\u1ECFi l\u1EDBp");
	private final JPanel pnlRemove = new JPanel();
	private final JLabel lblRemoveSId = new JLabel("MSSV");
	private final JTextField tfRemoveSId = new JTextField();
	private final JButton btnRemove = new JButton("X\u00F3a");
	private final JLabel lblAdd = new JLabel("Th\u00EAm sinh vi\u00EAn v\u00E0o l\u1EDBp");
	private final JPanel pnlAdd = new JPanel();
	private final JLabel lblAddClass = new JLabel("L\u1EDBp");
	private final JLabel lblAddStudent = new JLabel("Sinh vi\u00EAn");
	private final JButton btnAdd = new JButton("  Th\u00EAm   ");
	private final JComboBox<String> cbAddClass = new JComboBox<>();
	private final JLabel lblRemoveName = new JLabel("T\u00EAn");
	private final JTextField tfRemoveName = new JTextField();
	private final JComboBox<String> cbAddStudent = new JComboBox<>();
	private final JButton btnAddClassChooser = new JButton("Ch\u1ECDn l\u1EDBp");

	public ManageStudentPanel() {
		tfRemoveName.setEditable(false);
		tfRemoveName.setColumns(10);
		tfRemoveSId.setEditable(false);
		tfRemoveSId.setColumns(10);
		getClasses();
		getCourses();
		getTable();
		setProperties();
		addComponents();
		setActionListener();
	}

	private void setProperties() {
		scrollPane = new JScrollPane(this.tblStudents);
		panel.setLayout(new MigLayout("", "[][grow][200]", "[][]"));
		pnlRemove.setLayout(new MigLayout("", "[][grow][][][grow]", "[][][]"));
		pnlAdd.setLayout(new MigLayout("", "[][grow][200]", "[][][]"));
		setLayout(new MigLayout("", "[436px,grow]", "[16px][][200px][][][][]"));
	}

	private void addComponents() {
		panel.add(lblClassChooser, "cell 0 0,alignx trailing");
		panel.add(cbClasses, "cell 1 0,growx");
		panel.add(lblCourseChooser, "cell 0 1,alignx trailing");
		panel.add(cbCourses, "cell 1 1,growx");
		panel.add(btnSearch, "cell 2 1,alignx right");

		pnlRemove.add(lblRemoveSId, "cell 0 0,alignx trailing");
		pnlRemove.add(tfRemoveSId, "cell 1 0,growx");
		pnlRemove.add(lblRemoveName, "cell 3 0,alignx trailing");
		pnlRemove.add(tfRemoveName, "cell 4 0,growx");
		pnlRemove.add(btnRemove, "cell 4 1,alignx right");

		pnlAdd.add(lblAddClass, "cell 0 0,alignx trailing");
		pnlAdd.add(cbAddClass, "cell 1 0,growx");
		pnlAdd.add(btnAddClassChooser, "cell 2 0,alignx right");
		pnlAdd.add(lblAddStudent, "cell 0 2,alignx trailing");
		pnlAdd.add(cbAddStudent, "cell 1 2,growx");
		pnlAdd.add(btnAdd, "cell 2 2,alignx right");

		add(lblStudentList, "cell 0 0,alignx left,aligny top");
		add(panel, "cell 0 1,grow");
		add(scrollPane, "cell 0 2,grow");
		add(lblRemove, "cell 0 3");
		add(pnlRemove, "cell 0 4,grow");
		add(lblAdd, "cell 0 5");
		add(pnlAdd, "cell 0 6,grow");
	}

	private void setActionListener() {
		btnSearch.addMouseListener(this);
		btnRemove.addMouseListener(this);
		btnAdd.addMouseListener(this);
		tblStudents.addMouseListener(this);
		cbAddClass.addMouseListener(this);
		btnAddClassChooser.addMouseListener(this);
	}

	private void getClasses() {
		List<Class> classes = new ClassDAO().getAll();
		classes.forEach(c -> {
			cbClasses.addItem(c.getClId() + " - " + c.getName());
			cbAddClass.addItem(c.getClId() + " - " + c.getName());
		});
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

	private void removeStudent() {
		String selectedClass = String.valueOf(cbClasses.getSelectedItem()).substring(0, 5);
		String selectedCourses = (cbCourses.getSelectedIndex() == 0) ? ""
				: String.valueOf(cbCourses.getSelectedItem()).substring(0, 5);
		if (selectedCourses.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui l\u00F2ng ch\u1ECDn m\u00F4n", Constant.ALERT,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		new ClassCourseDetailDAO().delete(selectedClass, selectedCourses, String.valueOf(tfRemoveSId.getText()));

		setupTableData();
		tfRemoveSId.setText("");
		tfRemoveName.setText("");
		JOptionPane.showMessageDialog(this, "X\u00F3a sinh vi\u00EAn kh\u1ECFi l\u1EDBp th\u00E0nh c\u00F4ng", Constant.ALERT,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void addStudent() {
		String selectedClass = String.valueOf(cbClasses.getSelectedItem()).substring(0, 5);
		String selectedCourses = (cbCourses.getSelectedIndex() == 0) ? ""
				: String.valueOf(cbCourses.getSelectedItem()).substring(0, 5);
		if (selectedCourses.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui l\u00F2ng ch\u1ECDn m\u00F4n", Constant.ALERT,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String selectedSid = String.valueOf(cbAddStudent.getSelectedItem()).substring(0, 7);
		ClassCourseDetail c = new ClassCourseDetail(selectedClass, selectedCourses, selectedSid);	
		System.out.println(c);	
		
		new ClassCourseDetailDAO().save(c);
		setupTableData();
		JOptionPane.showMessageDialog(this, "Th\u00EAm sinh vi\u00EAn v\u00E0o l\u1EDBp th\u00E0nh c\u00F4ng", Constant.ALERT,
				JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void getStudent() {
		cbAddStudent.removeAllItems();
		List<Student> students = new StudentDAO()
				.getByClass(String.valueOf(cbAddClass.getSelectedItem()).substring(0, 5));
		students.forEach(s -> cbAddStudent.addItem(s.getSId() + " - " + s.getName()));
	}

	private void getSelectedRowInfo(int row) {
		tfRemoveSId.setText(String.valueOf(tblStudents.getValueAt(row, 0)));
		tfRemoveName.setText(String.valueOf(tblStudents.getValueAt(row, 1)));
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
			tfRemoveSId.setText("");
			tfRemoveName.setText("");
		}
		if (event.getSource() == btnRemove) {
			removeStudent();
		}
		if (event.getSource() == btnAdd) {
			addStudent();
		}
		if (event.getSource() == tblStudents) {
			getSelectedRowInfo(tblStudents.getSelectedRow());
		}
		if (event.getSource() == btnAddClassChooser) {

			getStudent();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}
