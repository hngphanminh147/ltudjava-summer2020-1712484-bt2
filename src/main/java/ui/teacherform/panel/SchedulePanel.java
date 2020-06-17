package ui.teacherform.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pojos.Class;
import pojos.Course;
import pojos.Schedule;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import daos.ClassDAO;
import daos.CourseDAO;
import daos.ScheduleDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class SchedulePanel extends JPanel implements MouseListener {
	private JPanel panel = new JPanel();
	private JLabel lblSchedule = new JLabel("Xem th\u1EDDi kh\u00F3a bi\u1EC3u");
	private JLabel lblClasses = new JLabel("Ch\u1ECDn l\u1EDBp");
	private JButton btnSearch = new JButton("T\u00ECm ki\u1EBFm");
	private JComboBox<String> cbClasses = new JComboBox<>();
	private JTable tblSchedule = new JTable();;
	private JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the panel.
	 */
	public SchedulePanel() {
		getClasses();
		getTable();
		setProperties();
		addComponents();
		setActionListener();
	}
	
	private void setProperties() {
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
	}
	
	private void addComponents() {
		panel.setLayout(new MigLayout("", "[][grow][200]", "[]"));
		panel.add(lblClasses, "cell 0 0,alignx trailing");
		panel.add(cbClasses, "cell 1 0,growx");
		panel.add(btnSearch, "cell 2 0,alignx right");

		scrollPane.setViewportView(tblSchedule);

		add(lblSchedule, "cell 0 0");
		add(panel, "cell 0 1,grow");
		add(scrollPane, "cell 0 2,grow");
	}
	
	private void setActionListener() {
		btnSearch.addMouseListener(this);
	}
	
	private void getClasses() {
		List<Class> classes = new ClassDAO().getAll();
		classes.forEach(c -> cbClasses.addItem(c.getClId() + " - " + c.getName()));
	}
	
	private void getTable() {
		String[] columnNames = {"M\u00E3 m\u00F4n", "T\u00EAn m\u00F4n", "Ph\u00F2ng h\u1ECDc"};
		if (tblSchedule == null) {
			tblSchedule = new JTable() {
				public boolean isCellEditable(int nRow, int nCol) {
					return false;
				}
			};
		}
		DefaultTableModel scheduleTableModel = (DefaultTableModel) tblSchedule.getModel();
		scheduleTableModel.setColumnIdentifiers(columnNames);
		tblSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void setupTableData() {
		DefaultTableModel tblScheduleModel = (DefaultTableModel) tblSchedule.getModel();
		tblScheduleModel.setRowCount(0);
		
		String selectedClass = String.valueOf(cbClasses.getSelectedItem()).substring(0, 5);
		List<Schedule> schedules = new ScheduleDAO().getByClId(selectedClass);
		schedules.forEach(s -> {
			String[] row = new String[3];
			row[0] = s.getCID();
			row[1] = new CourseDAO().getByCId(s.getCID()).getName();
			row[2] = s.getRoom();
			tblScheduleModel.addRow(row);
		});
		tblScheduleModel.fireTableDataChanged();
		tblSchedule.setModel(tblScheduleModel);
		tblSchedule.repaint();
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
