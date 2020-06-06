package ui.studentform.panel;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pojos.Member;
import pojos.Student;

import javax.swing.JTextField;

import constant.Constant;
import daos.MemberDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ProfilePanel extends JPanel implements MouseListener{
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
	
	private final JLabel lblPassword = new JLabel("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
	private final JPanel panel = new JPanel();
	private final JLabel lblCurrenPassword = new JLabel("M\u1EADt kh\u1EA9u hi\u1EC7n t\u1EA1i");
	private final JLabel lblNewPassword = new JLabel("M\u1EADt kh\u1EA9u m\u1EDBi");
	private final JLabel lblReEnterNewPass = new JLabel("Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u m\u1EDBi");
	
	private JPasswordField pfCurrenPassword = new JPasswordField();
	private JPasswordField pfNewPassword = new JPasswordField();
	private JPasswordField pfReEnterNewPass = new JPasswordField();
	private JButton btnPasswordSubmit = new JButton("\u0110\u1ED5i m\u1EADt kh\u1EA9u");

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
		setLayout(new MigLayout("", "[grow]", "[][][][grow]"));
		panel.setLayout(new MigLayout("", "[][grow][200]", "[][][][]"));
		
		tfSid.setText(s.getSId());
		tfSid.setEditable(false);
		
		tfName.setText(s.getName());
		tfName.setEditable(false);
		
		tfClass.setText(s.getClID());
		tfClass.setEditable(false);
		
		tfGender.setText((s.isGender())? "N\u1EEF" : "Nam");
		tfGender.setEditable(false);
		
		btnPasswordSubmit.addMouseListener(this);
	}
	
	private void addComponentsToContentPane() {
		
		profile.setLayout(new MigLayout("", "[][grow][200]", "[][][][]"));
		
		profile.add(lblSid, "cell 0 0,alignx trailing");
		profile.add(lblName, "cell 0 1,alignx left");
		profile.add(lblClass, "cell 0 2,alignx left");
		profile.add(lblGender, "cell 0 3,alignx left");
		
		profile.add(tfSid, "cell 1 0,growx");
		profile.add(tfName, "cell 1 1,growx");		
		profile.add(tfClass, "cell 1 2,growx");
		profile.add(tfGender, "cell 1 3,growx");

		panel.add(lblCurrenPassword, "cell 0 0,alignx trailing");
		panel.add(pfCurrenPassword, "cell 1 0,growx");
		panel.add(lblNewPassword, "cell 0 1,alignx trailing");
		panel.add(pfNewPassword, "cell 1 1,growx");
		panel.add(lblReEnterNewPass, "cell 0 2,alignx trailing");
		panel.add(pfReEnterNewPass, "cell 1 2,growx");
		panel.add(btnPasswordSubmit, "cell 1 3,alignx right");
		
		add(lblProfile, "cell 0 0");
		add(profile, "cell 0 1,grow");		
		add(lblPassword, "cell 0 2");
		add(panel, "cell 0 3,grow");
	}
	
	private void changePassword() {
		String currentPassword = String.valueOf(pfCurrenPassword.getPassword()).replaceAll("\\s+$", "");
		String dataPass = new MemberDAO().getByUsername(this.s.getSId()).getPassword();
		if (!currentPassword.equals(dataPass)) {
			pfCurrenPassword.setText("");
			JOptionPane.showMessageDialog(this, "M\u1EADt kh\u1EA9u hi\u1EC7n t\u1EA1i kh\u00F4ng \u0111\u00FAng", Constant.ALERT, JOptionPane.WARNING_MESSAGE);
			return;
		}
		String newPassword = String.valueOf(pfNewPassword.getPassword()).replaceAll("\\s+$", "");
		String reEnterNewPass = String.valueOf(pfReEnterNewPass.getPassword()).replaceAll("\\s+$", "");
		if (!newPassword.equals(reEnterNewPass)) {
			pfNewPassword.setText("");
			pfReEnterNewPass.setText("");
			JOptionPane.showMessageDialog(this, "2 m\u1EADt kh\u1EA9u kh\u00F4ng kh\u1EDBp", Constant.ALERT, JOptionPane.WARNING_MESSAGE);
			return;
		}
		new MemberDAO().updatePassword(this.s.getSId(), newPassword);
		
		pfCurrenPassword.setText("");
		pfNewPassword.setText("");
		pfReEnterNewPass.setText("");
		JOptionPane.showMessageDialog(this, "\u0110\u1ED5i m\u1EADt kh\u1EA9u th\u00E0nh c\u00F4ng", Constant.ALERT, JOptionPane.INFORMATION_MESSAGE);
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
		if (event.getSource() == btnPasswordSubmit) {
			changePassword();
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
