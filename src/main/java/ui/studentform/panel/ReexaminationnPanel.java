package ui.studentform.panel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pojos.Record;
import pojos.Reexamination;
import pojos.Student;
import net.miginfocom.swing.MigLayout;

import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import daos.ClassDAO;
import daos.CourseDAO;
import daos.RecordDAO;
import daos.ReexaminationDAO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.JTextField;

import com.mysql.cj.Constants;

import constant.Constant;

import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DropMode;

public class ReexaminationnPanel extends JPanel implements MouseListener {
	private Student s;

	private final JLabel lblClasses = new JLabel("C\u00E1c m\u00F4n \u0111\u00E3 c\u00F3 \u0111i\u1EC3m");
	private final JLabel lblReexamination = new JLabel();
	private final JPanel panel = new JPanel();
	private final JLabel lblCourse = new JLabel("M\u00F4n h\u1ECDc");
	private final JLabel lblSelected = new JLabel("\u0110i\u1EC3m c\u1EA7n ph\u00FAc kh\u1EA3o");
	private final JLabel lblExpecting = new JLabel("\u0110i\u1EC3m mong mu\u1ED1n");
	private final JLabel lblReason = new JLabel("L\u00FD do");
	private final JLabel lblClass = new JLabel("L\u1EDBp h\u1ECDc");

	private JTable tblRecords = new JTable();
	private Vector<String> columnNames = new Vector<>();
	private Vector<Vector> rowData = new Vector<>();
	private JScrollPane scrollPane;

	private JTextField tfCourse = new JTextField();
	private JTextField tfExpecting = new JTextField();
	private JComboBox<String> cbSelected = new JComboBox<String>();
	private JTextArea taReason = new JTextArea();
	private JButton btnSubmit = new JButton("G\u1EEDi");
	private JTextField tfClass = new JTextField();

	public ReexaminationnPanel(Student s) {
		this.s = s;
		getRecords();
		this.tblRecords = new JTable(rowData, columnNames);
		this.scrollPane = new JScrollPane(this.tblRecords);
		setProperties();
		addComponents();
		addActionListener();
	}

	private void setProperties() {
		setLayout(new MigLayout("", "[grow]", "[][200][][grow]"));
		panel.setLayout(new MigLayout("", "[][grow][]", "[][][][][grow][][][]"));
		
		lblReexamination.setText("Ph\u00FAc kh\u1EA3o");

		tfCourse.setEditable(false);
		
		tfClass.setEditable(false);
		
		taReason.setWrapStyleWord(true);
		taReason.setLineWrap(true);

	}

	private void addComponents() {
		add(lblClasses, "cell 0 0");
		add(scrollPane, "cell 0 1,grow");
		add(lblReexamination, "flowx,cell 0 2");
		add(panel, "cell 0 3,grow");
		
		panel.add(lblCourse, "cell 0 0,alignx left");
		panel.add(tfCourse, "cell 1 0,growx");
		panel.add(lblClass, "cell 0 1,alignx left");
		panel.add(tfClass, "cell 1 1,growx");
		panel.add(lblSelected, "cell 0 2,alignx trailing");
		panel.add(cbSelected, "cell 1 2,growx");
		panel.add(lblExpecting, "cell 0 3,alignx left");
		panel.add(tfExpecting, "cell 1 3,growx");
		panel.add(lblReason, "cell 0 4,alignx left,aligny top");
		panel.add(taReason, "cell 1 4,grow");
		panel.add(btnSubmit, "cell 1 5,alignx right");
	}
	
	private void addActionListener() {		
		tblRecords.addMouseListener(this);
		cbSelected.addMouseListener(this);
		btnSubmit.addMouseListener(this);
	}

	private void getRecords() {
		columnNames.addElement("M\u00F4n h\u1ECDc");
		columnNames.addElement("L\u1EDBp h\u1ECDc");
		columnNames.addElement("\u0110i\u1EC3m gi\u1EEFa k\u1EF3");
		columnNames.addElement("\u0110i\u1EC3m cu\u1ED1i k\u1EF3");
		columnNames.addElement("\u0110i\u1EC3m kh\u00E1c");
		columnNames.addElement("\u0110i\u1EC3m t\u1ED5ng k\u1EBFt");

		List<Record> records = new RecordDAO().getBySId(s.getSId());
		for (Record r : records) {
			Vector<Object> row = new Vector<>();
			String courseName = new CourseDAO().getByCId(r.getCID()).getName();
			String className = new ClassDAO().getByClId(r.getClID()).getName();
			row.addElement(courseName);
			row.addElement(className);
			row.addElement(r.getMidTerm());
			row.addElement(r.getFinalTerm());
			row.addElement(r.getOther());
			row.addElement(r.getGrade());
			rowData.addElement(row);
		}
	}

	private void displayRow(int row) {
		String courseName = String.valueOf(tblRecords.getValueAt(row, 0));
		String className = String.valueOf(tblRecords.getValueAt(row, 1));
		tfCourse.setText(courseName);
		tfClass.setText(className);
		cbSelected.removeAllItems();
		cbSelected.addItem(this.columnNames.get(2) + " - " + String.valueOf(tblRecords.getValueAt(row, 2)));
		cbSelected.addItem(this.columnNames.get(3) + " - " + String.valueOf(tblRecords.getValueAt(row, 3)));
		cbSelected.addItem(this.columnNames.get(4) + " - " + String.valueOf(tblRecords.getValueAt(row, 4)));
		cbSelected.setEditable(false);
	}
	
	private void createReexamination() {
		try {
			String clId = new ClassDAO().getByName(tfClass.getText()).getClId();
//			System.out.println(tblRecords.getComponentAt(tblRecords.getSelectedRow(), tblRecords.getSelectedColumn()).toString());
			String cId = new CourseDAO().getByName(tfCourse.getText()).getCId();
			String sId = this.s.getSId();
			int type = cbSelected.getSelectedIndex();
			int score = Integer.parseInt(tfExpecting.getText());
			String reason = taReason.getText();
			Reexamination r = new Reexamination(clId, cId, sId, type, score, reason, 0);
//			System.out.println(r);
			new ReexaminationDAO().save(r);
			JOptionPane.showMessageDialog(this, "T\u1EA1o ph\u00FAc kh\u1EA3o th\u00E0nh c\u00F4ng", Constant.ALERT, JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException exception) {
			exception.printStackTrace();
			tfExpecting.setText("");
			JOptionPane.showMessageDialog(this, "\u0110i\u1EC3m kh\u00F4ng h\u1EE3p l\u1EC7", Constant.ALERT, JOptionPane.ERROR_MESSAGE);
			// TODO: handle exception
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == tblRecords) {
			int row = tblRecords.getSelectedRow();
			displayRow(row);			
		}
		if (event.getSource() == btnSubmit) {
			createReexamination();
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
