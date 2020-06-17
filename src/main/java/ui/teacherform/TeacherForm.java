package ui.teacherform;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.teacherform.panel.ChangePasswordPanel;
import ui.teacherform.panel.GradePanel;
import ui.teacherform.panel.ManageStudentPanel;
import ui.teacherform.panel.SchedulePanel;
import net.miginfocom.swing.MigLayout;
import pojos.Member;

import javax.swing.JTabbedPane;

public class TeacherForm extends JFrame {
	private Member t;

	private JPanel contentPane = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel changePasswordPanel;
	private JPanel manageStudentPanel;
	private JPanel schedulePanel;
	private JPanel recordPanel;

	public TeacherForm(Member t) {
		this.t = t;
		this.changePasswordPanel = new ChangePasswordPanel(t);
		this.manageStudentPanel = new ManageStudentPanel();
		this.schedulePanel = new SchedulePanel();
		this.recordPanel = new GradePanel();
		setProperties();
		addComponents();
		setActionListener();
	}

	private void setProperties() {
		setTitle("Gi\u00E1o v\u1EE5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 440);
		setResizable(false);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		setContentPane(contentPane);
	}
	
	private void addComponents() {
		contentPane.add(tabbedPane, "cell 0 0,grow");
		tabbedPane.addTab("\u0110\u1ED5i m\u1EADt kh\u1EA9u", null, changePasswordPanel, null);
		tabbedPane.addTab("Qu\u1EA3n l\u00FD sinh vi\u00EAn", null, manageStudentPanel, null);
//		panel_1.setLayout(new MigLayout("", "[]", "[]"));
		tabbedPane.addTab("Qu\u1EA3n l\u00FD m\u00F4n h\u1ECDc", null, schedulePanel, null);
		tabbedPane.addTab("Qu\u1EA3n l\u00FD \u0111i\u1EC3m", null, recordPanel, null);
	}
	
	private void setActionListener() {
		
	}
}
