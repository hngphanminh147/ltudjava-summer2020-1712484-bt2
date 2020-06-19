package ui.teacherform.panel;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pojos.Class;
import pojos.Schedule;
import pojos.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import constant.Constant;
import daos.ClassDAO;
import daos.ScheduleDAO;
import daos.StudentDAO;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class ImportPanel extends JPanel implements MouseListener {
	private File file;
	private JLabel lblFromFile = new JLabel("Nh\u1EADp danh s\u00E1ch sinh vi\u00EAn");
	private JLabel lblFilePath = new JLabel("File \u0111\u00E3 ch\u1ECDn");
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel pnlImport = new JPanel();
	private JButton btnBrowse = new JButton("Ch\u1ECDn file");
	private JTextField tfFilePath = new JTextField();
	private JTable tblImportData = new JTable();
	private final JLabel lblSingle = new JLabel("Nh\u1EADp sinh vi\u00EAn");
	private final JLabel lblImportType = new JLabel("Ch\u1ECDn lo\u1EA1i");
	private final JComboBox<String> cbType = new JComboBox<>();
	private final JButton btnImport = new JButton("   Nh\u1EADp   ");
	private final JPanel pnlSingle = new JPanel();
	private final JLabel lblSId = new JLabel("MSSV");
	private final JTextField tfSId = new JTextField();
	private final JLabel lblClId = new JLabel("M\u00E3 l\u1EDBp");
	private final JLabel lblName = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
	private final JTextField tfName = new JTextField();
	private final JLabel lblGender = new JLabel("Gi\u1EDBi t\u00EDnh");
	private final JButton btnAdd = new JButton("Th\u00EAm sinh vi\u00EAn");
	private final JTextField tfIdentity = new JTextField();
	private final JLabel lblIdentity = new JLabel("CMND");
	private final JComboBox<String> cbClasses = new JComboBox<>();
	private final JRadioButton rbtnIsFemale = new JRadioButton("N\u1EEF");
	private final JRadioButton rbtnIsMale = new JRadioButton("Nam");

	/**
	 * Create the panel.
	 */
	public ImportPanel() {
		getClasses();
		setProperties();
		addComponents();
		addActionListener();
	}

	private void setProperties() {
		setLayout(new MigLayout("", "[grow]", "[16px][68px][200px][16px][94px]"));
		pnlImport.setLayout(new MigLayout("", "[][grow][100]", "[][]"));
		pnlSingle.setLayout(new MigLayout("", "[][grow][][][grow]", "[][][]"));
		tfFilePath.setEditable(false);
		scrollPane.setViewportView(tblImportData);
		cbType.addItem("Nh\u1EADp danh s\u00E1ch l\u1EDBp");
		cbType.addItem("Nh\u1EADp th\u1EDDi kh\u00F3a bi\u1EC3u");
		tblImportData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void addComponents() {
		pnlImport.add(lblFilePath, "cell 0 0,alignx trailing");
		pnlImport.add(tfFilePath, "cell 1 0,growx");
		pnlImport.add(btnBrowse, "cell 2 0,alignx right");
		pnlImport.add(lblImportType, "cell 0 1,alignx trailing");
		pnlImport.add(cbType, "cell 1 1,growx");
		pnlImport.add(btnImport, "cell 2 1,alignx right");

		pnlSingle.add(lblSId, "cell 0 0,alignx trailing");
		pnlSingle.add(tfSId, "cell 1 0,growx,aligny top");
		pnlSingle.add(lblClId, "cell 3 0,alignx trailing");
		pnlSingle.add(cbClasses, "cell 4 0,growx");
		pnlSingle.add(lblName, "cell 0 1,alignx trailing");
		pnlSingle.add(tfName, "cell 1 1,growx");
		pnlSingle.add(lblGender, "cell 3 1,alignx trailing");
		pnlSingle.add(rbtnIsFemale, "flowx,cell 4 1");
		pnlSingle.add(lblIdentity, "cell 0 2,alignx trailing");
		pnlSingle.add(tfIdentity, "cell 1 2,growx");
		pnlSingle.add(btnAdd, "cell 4 2,alignx right");
		pnlSingle.add(rbtnIsMale, "cell 4 1");

		add(lblFromFile, "cell 0 0,alignx left,aligny top");
		add(pnlImport, "cell 0 1,grow");
		add(scrollPane, "cell 0 2,grow");
		add(lblSingle, "cell 0 3,alignx left,aligny top");
		add(pnlSingle, "cell 0 4,grow");
	}

	private void addActionListener() {
		btnBrowse.addMouseListener(this);
		btnImport.addMouseListener(this);
		btnAdd.addMouseListener(this);
		rbtnIsFemale.addMouseListener(this);
		rbtnIsMale.addMouseListener(this);
	}

	private void chooseFile() {
		JFileChooser fileChooser = new JFileChooser("");
		fileChooser.showSaveDialog(null);
		file = fileChooser.getSelectedFile();
		tfFilePath.setText(file.getPath().toString());
	}

	private void importClass() {
		DefaultTableModel tblModel = (DefaultTableModel) tblImportData.getModel();
		tblModel.setRowCount(0);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String clId = file.getName().substring(4, 9);
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				Student s = new Student(data[0], clId, data[1], (data[2].equals("1") ? true : false), data[3]);
				tblModel.addRow(data);
				new StudentDAO().save(s);
			}
			tblModel.fireTableDataChanged();
			tblImportData.setModel(tblModel);
			tblImportData.repaint();
		} catch (Exception excecption) {
			excecption.printStackTrace();
		}
	}

	private void setupClassTableData() {
		DefaultTableModel tblClassModel = (DefaultTableModel) tblImportData.getModel();
		if (tblImportData == null) {
			tblImportData = new JTable() {
				public boolean isCellEditable(int nRow, int nCol) {
					return false;
				}
			};
		}
		String[] columnNames = { "M\u00E3 s\u1ED1 sinh vi\u00EAn", "H\u1ECD v\u00E0 t\u00EAn", "L\u1EDBp",
				"Gi\u1EDBi t\u00EDnh", "CMND" };
		tblClassModel.setColumnIdentifiers(columnNames);
	}

	private void importSchedule() {
		DefaultTableModel tblModel = (DefaultTableModel) tblImportData.getModel();
		tblModel.setRowCount(0);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String clId = file.getName().substring(4, 9);
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				Schedule s = new Schedule(clId, data[0], data[2]);
				tblModel.addRow(data);
				new ScheduleDAO().save(s);
			}
			tblModel.fireTableDataChanged();
			tblImportData.setModel(tblModel);
			tblImportData.repaint();
		} catch (Exception excecption) {
			excecption.printStackTrace();
		}
	}

	private void setupScheduleTableData() {
		DefaultTableModel tblClassModel = (DefaultTableModel) tblImportData.getModel();
		if (tblImportData == null) {
			tblImportData = new JTable() {
				public boolean isCellEditable(int nRow, int nCol) {
					return false;
				}
			};
		}
		String[] columnNames = { "M\u00E3 m\u00F4n", "T\u00EAn m\u00F4n", "Ph\u00F2ng h\u1ECDc" };
		tblClassModel.setColumnIdentifiers(columnNames);
	}

	private void getClasses() {
		List<Class> classes = new ClassDAO().getAll();
		classes.forEach(c -> cbClasses.addItem(c.getClId() + " - " + c.getName()));
	}

	private void addSingle() {
		String sId = tfSId.getText();
		String clId = String.valueOf(cbClasses.getSelectedItem()).substring(0, 5);
		String name = tfName.getText();
		boolean gender = (rbtnIsFemale.isSelected()) ? true : false;
		String identity = tfIdentity.getText();

		Student s = new Student(sId, clId, name, gender, identity);
		new StudentDAO().save(s);
		JOptionPane.showMessageDialog(this, "Th\u00EAm sinh vi\u00EAn th\u00E0nh c\u00F4ng", Constant.ALERT,
				JOptionPane.INFORMATION_MESSAGE);
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
		if (event.getSource() == btnBrowse) {
			chooseFile();
			return;
		}
		if (event.getSource() == btnImport) {
			if (cbType.getSelectedIndex() == 0) {
				setupClassTableData();
				importClass();
				return;
			}
			setupScheduleTableData();
			importSchedule();
			return;
		}
		if (event.getSource() == btnAdd) {
			addSingle();
			return;
		}
		if (event.getSource() == rbtnIsFemale) {
			rbtnIsMale.setSelected(false);
			return;
		}
		if (event.getSource() == rbtnIsMale) {
			rbtnIsFemale.setSelected(false);
			return;
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
