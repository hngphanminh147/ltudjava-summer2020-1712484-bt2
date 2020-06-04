package ui.studentform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class StudentFrame extends JFrame implements ActionListener{

	private final Student s;
	private JPanel contentPane = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel pnProfile;
	private JPanel pnReexamination;
	
	public StudentFrame(String sId) {
		this.s = (new StudentDAO()).get(sId);
		setProperties();
		setComponentsProperties();
		addActionEvent();
		addComponentsToContentPane();
	}

	private void setProperties() {
		setTitle("Student manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 436);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new MigLayout("", "[742px]", "[400]"));
		this.pnProfile = new ProfilePanel(s);
		this.pnReexamination = new ReexaminationnPanel(s);
		
		tabbedPane.setSize(760, 430);
		contentPane.add(tabbedPane, "cell 0 0,grow");
//		pnProfile.setLayout(new MigLayout("", "[]", "[]"));
//		pnReexamination.setLayout(new MigLayout("", "[]", "[]"));
		
		setContentPane(contentPane);
	}
	
	private void addComponentsToContentPane() {
	}
	
	private void addActionEvent() {
		
	}
	
	private void setComponentsProperties() {
		tabbedPane.addTab(Constant.PROFILE, null, pnProfile, null);
		tabbedPane.addTab(Constant.REEXAMNIMATION, null, pnReexamination, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
