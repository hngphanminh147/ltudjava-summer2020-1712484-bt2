package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constant.Constant;
import net.miginfocom.swing.MigLayout;
import pojos.Member;
import pojos.Student;
import ui.studentform.StudentForm;
import ui.teacherform.TeacherForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import daos.MemberDAO;
import daos.StudentDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JLabel lblUsername = new JLabel("T\u00E0i kho\u1EA3n");
	private JLabel lblPassword = new JLabel("M\u1EADt kh\u1EA9u");
	private JTextField tfUsername = new JTextField();
	private JPasswordField tfPassword = new JPasswordField();
	private JButton btnSubmit = new JButton("\u0110\u0103ng nh\u1EADp");

	public LoginForm() {
		setProperties();
		addActionEvent();
		addComponentsToContentPane();
	}

	private void setProperties() {
		setTitle("\u0110\u0103ng nh\u1EADp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(240, 150);
		contentPane.setLayout(new MigLayout("", "[50][56px][161px]", "[22px][22px][25px][][][]"));
		setContentPane(contentPane);
	}

	private void addComponentsToContentPane() {
		contentPane.add(lblUsername, "cell 1 1,alignx left,aligny center");
		contentPane.add(tfUsername, "cell 2 1,growx,aligny top");
		contentPane.add(lblPassword, "cell 1 2,alignx left,aligny center");
		contentPane.add(tfPassword, "cell 2 2,growx,aligny top");
		contentPane.add(btnSubmit, "cell 2 4,growx,aligny top");
	}
	
	private void addActionEvent() {
		btnSubmit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnSubmit) {
			String username = tfUsername.getText().replaceAll("\\s+$", "");
			String password = String.valueOf(tfPassword.getPassword()).replaceAll("\\s+$", "");
			
			tfUsername.setText("");
			tfPassword.setText("");

			if ((username.equals("giaovu")) && (password.equals("giaovu"))) {
				// TeacherFrame
				TeacherForm teacherFrame = new TeacherForm();
				teacherFrame.setVisible(true);
				dispose();
				return;
			}
			try {
				Member m = new MemberDAO().getByUsername(username);
				System.out.println(m);
				if (m.getPassword().equals(password)) {
					
					// StudentFrame	
					StudentForm studentFrame = new StudentForm(username);
					studentFrame .setVisible(true);
					dispose();
					return;
				}
				JOptionPane.showMessageDialog(this, Constant.LOGIN_FAILED, Constant.ALERT, JOptionPane.ERROR_MESSAGE);
			} catch (Exception exception) {
				exception.printStackTrace();
				// TODO: handle exception
			}
		}
	}
}
