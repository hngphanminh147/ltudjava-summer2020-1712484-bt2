package ui.studentform;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pojos.Student;

import javax.swing.JTextField;

public class ProfilePanel extends JPanel {
	private Student s;
	private final JLabel lblSid = new JLabel("M\u00E3 s\u1ED1 sinh vi\u00EAn");
	private final JLabel lblName = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
	private final JLabel lblClass = new JLabel("L\u1EDBp");
	private final JLabel lblGender = new JLabel("Gi\u1EDBi t\u00EDnh");
	private final JPanel profile = new JPanel();
	private final JLabel lblProfile = new JLabel(constant.Constant.PROFILE);
	private JTextField tfSid = new JTextField();
	private JTextField tfName = new JTextField();
	private JTextField tfClass = new JTextField();
	private JTextField tfGender = new JTextField();

	public ProfilePanel(Student s) {
		tfGender.setColumns(10);
		tfClass.setColumns(10);
		tfName.setColumns(10);
		tfSid.setColumns(10);
		this.s = s;
		setProperties();
		addComponentsToContentPane();
	}
	
	private void setProperties() {
		setLayout(new MigLayout("", "[grow]", "[][][][]"));
		
		tfSid.setText(s.getSId());
		tfSid.setEditable(false);
		
		tfName.setText(s.getName());
		tfName.setEditable(false);
		
		tfClass.setText(s.getClID());
		tfClass.setEditable(false);
		
		tfGender.setText((s.isGender())? "\"N\\u1EEF\"" : "Nam");
		tfGender.setEditable(false);
	}
	
	private void addComponentsToContentPane() {
		
		add(lblProfile, "cell 0 0");
		add(profile, "cell 0 1,grow");
		profile.setLayout(new MigLayout("", "[][grow][200]", "[][][][]"));
		
		profile.add(lblSid, "cell 0 0,alignx trailing");
		profile.add(lblName, "cell 0 1,alignx left");
		profile.add(lblClass, "cell 0 2,alignx left");
		profile.add(lblGender, "cell 0 3,alignx left");
		
		profile.add(tfSid, "cell 1 0,growx");
		profile.add(tfName, "cell 1 1,growx");		
		profile.add(tfClass, "cell 1 2,growx");
		profile.add(tfGender, "cell 1 3,growx");
	}
}
