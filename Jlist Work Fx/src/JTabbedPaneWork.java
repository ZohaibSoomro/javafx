import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JTabbedPaneWork {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTabbedPaneWork window = new JTabbedPaneWork();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JTabbedPaneWork() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 866, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(36, 66, 502, 282);
		frame.getContentPane().add(tabbedPane_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.addTab("Tab1", null, tabbedPane, null);
		
		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Button Tab", null, tab1, null);
		tab1.setLayout(null);
		
		JButton goto2ndTabBtn = new JButton("Go to 2nd Tab");
		goto2ndTabBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(tab1,"Pressed "+goto2ndTabBtn.getText()+" button Of tab1");
			}
		});
		goto2ndTabBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		goto2ndTabBtn.setBounds(15, 32, 173, 49);
		tab1.add(goto2ndTabBtn);
		
		JPanel tab2 = new JPanel();
		tabbedPane.addTab("Welcome Tab", null, tab2, null);
		tab2.setLayout(null);
		
		JLabel $2ndTabLbl = new JLabel("Welcome to 2nd Tab...");
		$2ndTabLbl.setHorizontalAlignment(SwingConstants.CENTER);
		$2ndTabLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		$2ndTabLbl.setForeground(Color.BLUE);
		$2ndTabLbl.setBackground(Color.CYAN);
		$2ndTabLbl.setBounds(28, 56, 391, 73);
		tab2.add($2ndTabLbl);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Tab2", null, panel, null);
	}
}
