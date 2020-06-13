package ui.teacherform.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pojos.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import constant.Constant;
import daos.MemberDAO;

public class ChangePasswordPanel extends JPanel implements MouseListener {
	private Member t;

	private final JLabel lblCurrentPassword = new JLabel("M\u1EADt kh\u1EA9u hi\u1EC7n t\u1EA1i");
	private final JLabel lblNewPassword = new JLabel("M\u1EADt kh\u1EA9u m\u1EDBi");
	private final JLabel lblReEnterNewPass = new JLabel("Nh\u1EADp l\u1EA1i m\u1EADt kh\u1EA9u m\u1EDBi");

	private JPasswordField pfCurrentPassword = new JPasswordField();
	private JPasswordField pfNewPassword = new JPasswordField();
	private JPasswordField pfReEnterNewPass = new JPasswordField();

	private JButton btnPasswordSubmit = new JButton("\u0110\u1ED5i m\u1EADt kh\u1EA9u");

	public ChangePasswordPanel(Member t) {
		this.t = t;
		setProperties();
		addComponents();
		setActionListener();
	}

	private void setProperties() {
		setLayout(new MigLayout("", "[][grow][200]", "[][][][][]"));
	}

	private void addComponents() {
		add(lblCurrentPassword, "cell 0 1,alignx left");
		add(pfCurrentPassword, "cell 1 1,growx");
		add(lblNewPassword, "cell 0 2,alignx left");
		add(pfNewPassword, "cell 1 2,growx");
		add(lblReEnterNewPass, "cell 0 3,alignx left");
		add(pfReEnterNewPass, "cell 1 3,growx");
		add(btnPasswordSubmit, "cell 1 4,alignx right");
	}

	private void setActionListener() {
		btnPasswordSubmit.addMouseListener(this);
	}

	private void changePassword() {
		String currentPassword = String.valueOf(pfCurrentPassword.getPassword()).replaceAll("\\s+$", "");
		String dataPass = t.getPassword();
		if (!currentPassword.equals(dataPass)) {
			pfCurrentPassword.setText("");
			JOptionPane.showMessageDialog(this, "M\u1EADt kh\u1EA9u hi\u1EC7n t\u1EA1i kh\u00F4ng \u0111\u00FAng",
					Constant.ALERT, JOptionPane.WARNING_MESSAGE);
			return;
		}
		String newPassword = String.valueOf(pfNewPassword.getPassword()).replaceAll("\\s+$", "");
		String reEnterNewPass = String.valueOf(pfReEnterNewPass.getPassword()).replaceAll("\\s+$", "");
		if (!newPassword.equals(reEnterNewPass)) {
			pfNewPassword.setText("");
			pfReEnterNewPass.setText("");
			JOptionPane.showMessageDialog(this, "2 m\u1EADt kh\u1EA9u kh\u00F4ng kh\u1EDBp", Constant.ALERT,
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		new MemberDAO().updatePassword(this.t.getUsername(), newPassword);	
		
		pfCurrentPassword.setText("");
		pfNewPassword.setText("");
		pfReEnterNewPass.setText("");
		JOptionPane.showMessageDialog(this, "\u0110\u1ED5i m\u1EADt kh\u1EA9u th\u00E0nh c\u00F4ng", Constant.ALERT, JOptionPane.INFORMATION_MESSAGE);
		
		String username = t.getUsername();
		t = new MemberDAO().getByUsername(username);
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
		// TODO Auto-generated method stub
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
