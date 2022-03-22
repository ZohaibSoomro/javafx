package attendanceSystem;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AttendanceSystem {

	static JFrame frame;
	private JLabel backImg;
	private ResultSet set = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceSystem window = new AttendanceSystem();
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
	public AttendanceSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Attendance System");
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AttendanceSystem.class.getResource("/sourceImages/Icon-school.png")));
		frame.setResizable(false);
		frame.setBounds(100, 50, 900, 619);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		backImg = new JLabel("");
		Image img = new ImageIcon(AttendanceSystem.class.getResource("/sourceImages/school.jpg")).getImage();
		backImg.setBounds(157, 0, 737, 546);
		backImg.setIcon(
				new ImageIcon(img.getScaledInstance(backImg.getWidth(), backImg.getHeight(), Image.SCALE_SMOOTH)));
		frame.getContentPane().add(backImg);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 224, 208));
		panel.setBounds(1, 66, 158, 480);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton exitBtn = new JButton("Exit");
		exitBtn.setBackground(new Color(64, 224, 208));
		exitBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure");
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setBounds(1, 427, 154, 37);
		panel.add(exitBtn);

		JButton stdInfoBtn = new JButton("Student Info");
		stdInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backImg.setIcon(new ImageIcon(
						img.getScaledInstance(backImg.getWidth(), backImg.getHeight(), Image.SCALE_SMOOTH)));
				frame.setVisible(false);
				Information stdInfo = new Information("Student");
				stdInfo.frame.setVisible(true);
				set = OracleConnection.oracleResultSet("Select * from student");
				stdInfo.stdInfoTable.setModel(DbUtils.resultSetToTableModel(set));
				stdInfo.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});
			}
		});
		stdInfoBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		stdInfoBtn.setBackground(new Color(64, 224, 208));
		stdInfoBtn.setBounds(1, 35, 154, 37);
		panel.add(stdInfoBtn);

		JButton tchrInfoBtn = new JButton("Teacher Info");
		tchrInfoBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				backImg.setIcon(new ImageIcon(
						img.getScaledInstance(backImg.getWidth(), backImg.getHeight(), Image.SCALE_SMOOTH)));
				frame.setVisible(false);
				Information tchrInfo = new Information("Teacher");
				tchrInfo.frame.setVisible(true);
				set = OracleConnection.oracleResultSet("Select * from teacher");
				tchrInfo.stdInfoTable.setModel(DbUtils.resultSetToTableModel(set));
				tchrInfo.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});
			}
		});
		tchrInfoBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		tchrInfoBtn.setBackground(new Color(64, 224, 208));
		tchrInfoBtn.setBounds(1, 95, 154, 37);
		panel.add(tchrInfoBtn);

		JButton updateBtn = new JButton("Update ");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backImg.setIcon(new ImageIcon(
						img.getScaledInstance(backImg.getWidth(), backImg.getHeight(), Image.SCALE_SMOOTH)));
				frame.setVisible(false);
				InsertUpdateDelete update = new InsertUpdateDelete("update");
				update.frame.setVisible(true);
				update.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});
			}
		});
		updateBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		updateBtn.setBackground(new Color(64, 224, 208));
		updateBtn.setBounds(1, 227, 154, 37);
		panel.add(updateBtn);

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backImg.setIcon(new ImageIcon(
						img.getScaledInstance(backImg.getWidth(), backImg.getHeight(), Image.SCALE_SMOOTH)));
				frame.setVisible(false);
				InsertUpdateDelete delete = new InsertUpdateDelete("delete");
				delete.frame.setVisible(true);
				delete.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});
			}
		});
		deleteBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		deleteBtn.setBackground(new Color(64, 224, 208));
		deleteBtn.setBounds(1, 289, 154, 37);
		panel.add(deleteBtn);

		JButton insertBtn = new JButton("Insert");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backImg.setIcon(new ImageIcon(
						img.getScaledInstance(backImg.getWidth(), backImg.getHeight(), Image.SCALE_SMOOTH)));
				frame.setVisible(false);
				InsertUpdateDelete insert = new InsertUpdateDelete("insert");
				insert.frame.setVisible(true);
				insert.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});
			}
		});
		insertBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		insertBtn.setBackground(new Color(64, 224, 208));
		insertBtn.setBounds(1, 163, 154, 37);
		panel.add(insertBtn);

		JButton attendanceBtn = new JButton("Attendance");
		attendanceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backImg.setIcon(new ImageIcon(
						img.getScaledInstance(backImg.getWidth(), backImg.getHeight(), Image.SCALE_SMOOTH)));
				frame.setVisible(false);
				Section section = new Section();
				section.sectionFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});

			}
		});
		attendanceBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		attendanceBtn.setBackground(new Color(64, 224, 208));
		attendanceBtn.setBounds(1, 359, 154, 37);
		panel.add(attendanceBtn);

		JLabel smallImgLbl = new JLabel("");
		smallImgLbl.setBounds(0, 0, 158, 68);
		smallImgLbl.setIcon(new ImageIcon(
				img.getScaledInstance(smallImgLbl.getWidth(), smallImgLbl.getHeight(), Image.SCALE_SMOOTH)));
		frame.getContentPane().add(smallImgLbl);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setToolTipText("About us...");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backImg.setIcon(null);
				backImg.setText("This is a attendance system developed by 19SW42, 19SW44, 19SW45 & 19SW120 \n"
						+ "as a project for the subject DBS.");

			}
		});
		aboutMenuItem.setIcon(null);
		menuBar.add(aboutMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setToolTipText("Exit the window...");
		exitMenuItem.setIcon(null);
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(exitMenuItem);
	}
}
