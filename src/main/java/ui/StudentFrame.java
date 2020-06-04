package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class StudentFrame extends JFrame implements ActionListener{

	private JPanel contentPane;

	public StudentFrame() {
		setLayout();
	}
	
	public StudentFrame(String id) {
		setLayout();
		addComponentsToContentPane();
		addActionEvent();
	}

	private void setLayout() {
		setTitle("Student manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 436);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][][]"));
		setContentPane(contentPane);
	}
	
	private void addComponentsToContentPane() {
		
	}
	
	private void addActionEvent() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrame frame = new StudentFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
