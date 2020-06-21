package ui.teacherform;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.LoginForm;
import ui.teacherform.panel.ChangePasswordPanel;
import ui.teacherform.panel.GradePanel;
import ui.teacherform.panel.ImportPanel;
import ui.teacherform.panel.ManageStudentPanel;
import ui.teacherform.panel.SchedulePanel;
import net.miginfocom.swing.MigLayout;
import pojos.Member;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TeacherForm extends JFrame implements MouseListener{
	private Member t;

	private JPanel contentPane = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel changePasswordPanel;
	private JPanel manageStudentPanel;
	private JPanel schedulePanel;
	private JPanel recordPanel;
	private JPanel importPanel;
	private JPanel panel = new JPanel();
	private JLabel lblHello = new JLabel("Ch\u00E0o gi\u00E1o v\u1EE5");;
	private JButton btnLogOut = new JButton("\u0110\u0103ng xu\u1EA5t");;

	public TeacherForm(Member t) {
		this.t = t;
		this.changePasswordPanel = new ChangePasswordPanel(t);
		this.manageStudentPanel = new ManageStudentPanel();
		this.schedulePanel = new SchedulePanel();
		this.recordPanel = new GradePanel();
		this.importPanel = new ImportPanel();
		
		setProperties();
		addComponents();
		setActionListener();
	}

	private void setProperties() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[742px,grow]", "[grow][520px]"));
		panel.setLayout(new MigLayout("", "[74px,grow][]", "[25px]"));
		
		setTitle("Gi\u00E1o v\u1EE5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 600);
		setResizable(false);
		setContentPane(contentPane);
	}
	
	private void addComponents() {
		tabbedPane.addTab("\u0110\u1ED5i m\u1EADt kh\u1EA9u", null, changePasswordPanel, null);
		tabbedPane.addTab("Qu\u1EA3n l\u00FD sinh vi\u00EAn", null, manageStudentPanel, null);
		tabbedPane.addTab("Qu\u1EA3n l\u00FD m\u00F4n h\u1ECDc", null, schedulePanel, null);
		tabbedPane.addTab("Qu\u1EA3n l\u00FD \u0111i\u1EC3m", null, recordPanel, null);
		tabbedPane.addTab("Nh\u1EADp th\u00F4ng tin", null, importPanel, null);
		contentPane.add(panel, "cell 0 0,grow");
		contentPane.add(tabbedPane, "cell 0 1,grow");
		panel.add(lblHello, "cell 0 0,alignx left,aligny center");
		panel.add(btnLogOut, "cell 1 0,alignx left,aligny top");
	}
	
	private void setActionListener() {
		btnLogOut.addMouseListener(this);
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
