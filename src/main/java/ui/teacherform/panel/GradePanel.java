package ui.teacherform.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pojos.Class;
import pojos.Course;
import pojos.Record;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import constant.Constant;
import daos.ClassDAO;
import daos.CourseDAO;
import daos.RecordDAO;
import daos.StudentDAO;

public class GradePanel extends JPanel implements MouseListener {
	JLabel lblRecord = new JLabel("Xem \u0111i\u1EC3m");
	JLabel lblClasses = new JLabel("Ch\u1ECDn l\u1EDBp");
	JLabel lblCourses = new JLabel("Ch\u1ECDn m\u00F4n");
	JLabel lblUpdateGrade = new JLabel("C\u1EADp nh\u1EADp \u0111i\u1EC3m");
	JLabel lblSid = new JLabel("M\u00E3 sinh vi\u00EAn");
	JLabel lblMid = new JLabel("\u0110i\u1EC3m gi\u1EEFa k\u1EF3");
	JLabel lblOther = new JLabel("\u0110i\u1EC3m kh\u00E1c");
	JLabel lblFinal = new JLabel("\u0110i\u1EC3m cu\u1ED1i k\u1EF3");
	JComboBox<String> cbCourses = new JComboBox<>();
	JComboBox<String> cbClasses = new JComboBox<>();
	JButton btnSearch = new JButton("T\u00ECm ki\u1EBFm");
	JButton btnComfirm = new JButton("\u0110\u1ED5i \u0111i\u1EC3m");
	JScrollPane scrollPane = new JScrollPane();
	JPanel pnlRecord = new JPanel();
	JPanel pnlUpdate = new JPanel();
	private JTable tblRecords = new JTable();
	private JTextField tfSid = new JTextField();
	private JTextField tfMid = new JTextField();
	private JTextField tfFinal = new JTextField();
	private JTextField tfOther = new JTextField();
	private final JPanel panel = new JPanel();
	private final JLabel lblPass = new JLabel("S\u1ED1 l\u01B0\u1EE3ng \u0111\u1EADu");
	private final JLabel lblFail = new JLabel("S\u1ED1 l\u01B0\u1EE3ng r\u1EDBt");
	private final JTextField tfPassPercent = new JTextField();
	private final JTextField tfFailPercent = new JTextField();

	/**
	 * Create the panel.
	 */
	public GradePanel() {
		tfFailPercent.setEditable(false);
		tfFailPercent.setColumns(10);
		tfPassPercent.setEditable(false);
		tfPassPercent.setColumns(10);
		getClasses();
		getCourses();
		getTable();
		setProperties();
		addComponents();
		addActionListeners();
	}

	private void setProperties() {
		setLayout(new MigLayout("", "[grow]", "[][][200][grow][][]"));
		panel.setLayout(new MigLayout("", "[][grow][200]", "[][]"));
		pnlUpdate.setLayout(new MigLayout("", "[][grow][][][grow]", "[][][][]"));
		pnlRecord.setLayout(new MigLayout("", "[][grow][200]", "[][]"));
		scrollPane.setViewportView(tblRecords);
		tfSid.setEnabled(false);
		tfSid.setEditable(false);
	}

	private void addComponents() {
		pnlRecord.add(lblClasses, "cell 0 0,alignx trailing");
		pnlRecord.add(cbClasses, "cell 1 0,growx");
		pnlRecord.add(lblCourses, "cell 0 1,alignx trailing");
		pnlRecord.add(cbCourses, "cell 1 1,growx");
		pnlRecord.add(btnSearch, "cell 2 1,alignx right");

		pnlUpdate.add(lblSid, "cell 0 0,alignx trailing");
		pnlUpdate.add(tfSid, "cell 1 0,growx");
		pnlUpdate.add(lblMid, "cell 3 0,alignx trailing");
		pnlUpdate.add(tfMid, "cell 4 0,growx");
		pnlUpdate.add(lblOther, "cell 0 1,alignx trailing");
		pnlUpdate.add(tfOther, "cell 1 1,growx");
		pnlUpdate.add(lblFinal, "cell 3 1,alignx trailing");
		pnlUpdate.add(tfFinal, "cell 4 1,growx");
		pnlUpdate.add(btnComfirm, "cell 4 2,alignx right");

		panel.add(lblPass, "cell 0 0,alignx trailing");
		panel.add(tfPassPercent, "cell 1 0,growx");
		panel.add(lblFail, "cell 0 1");
		panel.add(tfFailPercent, "cell 1 1,growx");
		
		add(lblRecord, "cell 0 0");
		add(pnlRecord, "cell 0 1,grow");
		add(scrollPane, "cell 0 2,grow");
		add(panel, "cell 0 3,grow");
		add(lblUpdateGrade, "cell 0 4");
		add(pnlUpdate, "cell 0 5,grow");
	}

	private void addActionListeners() {
		btnSearch.addMouseListener(this);
		btnComfirm.addMouseListener(this);
		tblRecords.addMouseListener(this);
	}

	private void getClasses() {
		List<Class> classes = new ClassDAO().getAll();
		classes.forEach(c -> cbClasses.addItem(c.getClId() + " - " + c.getName()));
	}

	private void getCourses() {
		List<Course> courses = new CourseDAO().getAll();
		courses.forEach(c -> cbCourses.addItem(c.getCId() + " - " + c.getName()));
	}

	private void getTable() {
		String[] tblColumnNames = { "MSSV", "T\u00EAn sinh vi\u00EAn", "\u0111i\u1EC3m gi\u1EEFa k\u1EF3",
				"\u0111i\u1EC3m cu\u1ED1i k\u1EF3", "\u0111i\u1EC3m kh\u00E1c", "\u0111i\u1EC3m t\u1ED5ng k\u1EBFt" , "\u0110\u1EADu"};
		if (tblRecords == null) {
			tblRecords = new JTable() {
				public boolean isCellEditable(int nRow, int nCol) {
					return false;
				}
			};
		}
		DefaultTableModel gradeTableModel = (DefaultTableModel) tblRecords.getModel();
		gradeTableModel.setColumnIdentifiers(tblColumnNames);
		tblRecords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void setupTableData() {
		DefaultTableModel tblRecordModel = (DefaultTableModel) tblRecords.getModel();
		tblRecordModel.setRowCount(0);

		String selectedClass = String.valueOf(cbClasses.getSelectedItem()).substring(0, 5);
		String selectedCourses = String.valueOf(cbCourses.getSelectedItem()).substring(0, 5);
		List<Record> records = new RecordDAO().getByClassCourse(selectedClass, selectedCourses);
		records.forEach(r -> {
			String[] row = new String[7];
			row[0] = r.getSID();
			row[1] = new StudentDAO().getBySId(r.getSID()).getName();
			row[2] = String.valueOf(r.getMidTerm());
			row[3] = String.valueOf(r.getFinalTerm());
			row[4] = String.valueOf(r.getOther());
			row[5] = String.valueOf(r.getGrade());
			row[6] = (r.getGrade() > 5)? "\u0110\u1EADu" : "R\u1EDBt";
			tblRecordModel.addRow(row);
		});
		tblRecordModel.fireTableDataChanged();
		tblRecords.repaint();
		
		if (tblRecords.getRowCount() == 0) {
			return;
		}
		int pass = 0;
		int num = tblRecords.getRowCount();
		for (int i = 0; i < num; i++) {
			pass += (tblRecords.getValueAt(i, 6).equals("\u0110\u1EADu")) ? 1 : 0;
		}
		tfPassPercent.setText(String.valueOf(pass) + " (" + String.valueOf(new BigDecimal(100.0*pass/num).setScale(2, RoundingMode.HALF_UP)) + "%)");
		tfFailPercent.setText(String.valueOf(num - pass) + " (" + String.valueOf(new BigDecimal(100.0*(num - pass)/num).setScale(2, RoundingMode.HALF_UP)) + "%)");
	}
	
	private void displayRow(int row) {
		tfSid.setText(String.valueOf(tblRecords.getValueAt(row, 0)));
		tfMid.setText(String.valueOf(tblRecords.getValueAt(row, 2)));
		tfFinal.setText(String.valueOf(tblRecords.getValueAt(row, 3)));
		tfOther.setText(String.valueOf(tblRecords.getValueAt(row, 4)));	
	}
	
	private void updateRecord() {
		try {
			String clID = String.valueOf(cbClasses.getSelectedItem()).substring(0, 5);
			String cID = String.valueOf(cbCourses.getSelectedItem()).substring(0, 5);
			String sID = tfSid.getText();
			int midR = new BigDecimal(tfMid.getText()).intValue();
			int finalR = new BigDecimal(tfFinal.getText()).intValue();
			int otherR = new BigDecimal(tfOther.getText()).intValue();
			double grade = 0.3*midR + 0.5*finalR + 0.2*otherR;
			new RecordDAO().update(clID, cID, sID, midR, finalR, otherR, grade);
			
			JOptionPane.showMessageDialog(this, "C\u1EADp nh\u1EADp \u0111i\u1EC3m th\u00E0nh c\u00F4ng", Constant.ALERT, JOptionPane.INFORMATION_MESSAGE);
			setupTableData();
		} catch (Exception exeption) {
			exeption.printStackTrace();
			JOptionPane.showMessageDialog(this, "C\u1EADp nh\u1EADp \u0111i\u1EC3m th\u1EA5t b\u1EA1i", Constant.ALERT, JOptionPane.ERROR_MESSAGE);
		}
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
		if (event.getSource() == btnComfirm) {
			updateRecord();
		}
		if (event.getSource() == tblRecords) {
			int row = tblRecords.getSelectedRow();
			displayRow(row);
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
