package ui.teacherform;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.teacherform.panel.ChangePasswordPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;

public class TeacherForm extends JFrame {

	private JPanel contentPane = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel changePasswordPanel = new ChangePasswordPanel();
	private JPanel panel_1 = new JPanel();

	public TeacherForm() {
		setProperties();
		addComponents();
		setActionListener();
	}

	private void setProperties() {
		setTitle("Gi\u00E1o v\u1EE5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		setContentPane(contentPane);
	}
	
	private void addComponents() {
		contentPane.add(tabbedPane, "cell 0 0,grow");
		tabbedPane.addTab("New tab", null, changePasswordPanel, null);
		tabbedPane.addTab("New tab", null, panel_1, null);
	}
	
	private void setActionListener() {
		
	}
}
