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
import ui.studentform.panel.ProfilePanel;
import ui.studentform.panel.ReexaminationnPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class StudentForm extends JFrame implements ActionListener{

	private final Student s;
	private JPanel contentPane = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel pnProfile;
	private JPanel pnReexamination;
	
	public StudentForm(String sId) {
		this.s = (new StudentDAO()).getBySId(sId);
		this.pnProfile = new ProfilePanel(s);
		this.pnReexamination = new ReexaminationnPanel(s);
		setProperties();
		setComponentsProperties();
		addActionEvent();
		addComponentsToContentPane();
	}

	private void setProperties() {
		setTitle("Sinh vi\u00EAn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 436);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[742px]", "[400]"));
		
		tabbedPane.setSize(760, 430);
		
		setContentPane(contentPane);
	}
	
	private void addComponentsToContentPane() {
		contentPane.add(tabbedPane, "cell 0 0,grow");
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
