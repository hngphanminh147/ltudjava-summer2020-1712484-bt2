package ui.studentform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constant.Constant;
import daos.StudentDAO;

import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import pojos.Student;
import ui.LoginForm;
import ui.studentform.panel.ProfilePanel;
import ui.studentform.panel.ReexaminationnPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class StudentForm extends JFrame implements MouseListener{

	private Student s;
	private JPanel contentPane = new JPanel();
	private JPanel contentPane_1;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel pnProfile;
	private JPanel pnReexamination;
	private JLabel lblStudent;
	private JButton btnLogOut = new JButton("\u0110\u0103ng xu\u1EA5t");
	
	public StudentForm(String sId) {
		System.out.println("1 args cons");
		this.s = (new StudentDAO()).getBySId(sId);
		this.pnProfile = new ProfilePanel(s);
		this.pnReexamination = new ReexaminationnPanel(s);
		this.lblStudent = new JLabel("Ch\u00E0o " + s.getName());
		setProperties();
		setComponentsProperties();
		addActionEvent();
		addComponentsToContentPane();
	}

	private void setProperties() {
		setTitle("Sinh vi\u00EAn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 440);
		setResizable(false);

		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		tabbedPane.setSize(760, 430);
		
		setContentPane(contentPane_1);
	}
	
	private void addComponentsToContentPane() {
		contentPane_1.setLayout(new MigLayout("", "[98px][4px][642px]", "[25px][351px]"));
		contentPane_1.add(lblStudent, "cell 0 0,alignx left,aligny center");
		contentPane_1.add(tabbedPane, "cell 0 1 3 1,grow");
		contentPane_1.add(btnLogOut, "cell 2 0,alignx right,aligny top");
	}
	
	private void addActionEvent() {
		btnLogOut.addMouseListener(this);
	}
	
	private void setComponentsProperties() {
		tabbedPane.addTab(Constant.PROFILE, null, pnProfile, null);
		tabbedPane.addTab(Constant.REEXAMNIMATION, null, pnReexamination, null);
	}	
	
	private void logOut() {
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
		dispose();
		return;
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
		if (event.getSource() == btnLogOut) {
			logOut();
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
