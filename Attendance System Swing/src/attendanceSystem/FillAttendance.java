package attendanceSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FillAttendance implements ActionListener {

	JFrame frmAttendanceOf;
	private JButton btnArray[] = new JButton[40];
	private JButton resetBtn;
	private JLabel presentLbl;
	private JLabel absentLbl;
	private int present = 0, total = 40;
	private JButton submitBtn;
	private JButton exitBtn;

	public FillAttendance(String section) {
		initialize(section);
	}

	public JButton[] getButtonArray() {
		return btnArray;
	}

	private void initialize(String section) {
		frmAttendanceOf = new JFrame();
		frmAttendanceOf.setVisible(true);
		frmAttendanceOf.setTitle("Online Attendance");
		frmAttendanceOf.setBounds(100, 100, 830, 506);
		frmAttendanceOf.setResizable(false);
		frmAttendanceOf.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AttendanceSystem.class.getResource("/sourceImages/Icon-school.png")));
		frmAttendanceOf.setLocationRelativeTo(null);
		frmAttendanceOf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAttendanceOf.getContentPane().setLayout(null);

		JLabel sectionLbl = new JLabel("ATTENDANCE OF " + section.toUpperCase());
		sectionLbl.setForeground(Color.BLUE);
		sectionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		sectionLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		sectionLbl.setBounds(162, 16, 434, 68);
		frmAttendanceOf.getContentPane().add(sectionLbl);

		resetBtn = new JButton("RESET");
		resetBtn.addActionListener(this);
		resetBtn.setIcon(new ImageIcon(FillAttendance.class.getResource("/sourceImages/reset.png")));
		resetBtn.setForeground(Color.BLUE);
		resetBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		resetBtn.setBounds(629, 16, 141, 40);
		frmAttendanceOf.getContentPane().add(resetBtn);

		presentLbl = new JLabel("PRESENT");
		presentLbl.setForeground(Color.BLUE);
		presentLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		presentLbl.setHorizontalAlignment(SwingConstants.LEFT);
		presentLbl.setBounds(39, 327, 165, 40);
		frmAttendanceOf.getContentPane().add(presentLbl);

		absentLbl = new JLabel("ABSENT");
		absentLbl.setHorizontalAlignment(SwingConstants.LEFT);
		absentLbl.setForeground(Color.RED);
		absentLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		absentLbl.setBounds(39, 381, 165, 40);
		frmAttendanceOf.getContentPane().add(absentLbl);

		submitBtn = new JButton("SUBMIT");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (present == 0)
					return;
//				if (!OracleRelatedTasks.deletePreviousAttendance(btnArray))
//					return;
				Connection connection = OracleRelatedTasks.oracleConnection();

				for (int i = 1; i <= btnArray.length; i++) {
					int counter = 1;
					String query = "Insert into Attendance(id,status) values(?,?)";
					try {
						PreparedStatement stmt = connection.prepareStatement(query);
						stmt.setInt(counter++, Integer.parseInt(btnArray[i - 1].getText()));
						if (btnArray[i - 1].getActionCommand().equals("yes"))
							stmt.setString(counter, "P");
						else
							stmt.setString(counter, "A");

						stmt.execute();

						stmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, e2);
						break;
					}
				}
				try {
					JOptionPane.showMessageDialog(null,
							"Total Present : " + present + "\nTotal Absent  : " + (total - present));
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		submitBtn.setForeground(Color.BLUE);
		submitBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		submitBtn.setBounds(330, 357, 141, 40);
		frmAttendanceOf.getContentPane().add(submitBtn);

		exitBtn = new JButton("EXIT");
		exitBtn.setIcon(new ImageIcon(FillAttendance.class.getResource("/sourceImages/exit.png")));
		exitBtn.setForeground(Color.BLUE);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure");
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		exitBtn.setBounds(27, 23, 112, 33);
		frmAttendanceOf.getContentPane().add(exitBtn);

		fillBtnArray(section, btnArray);
		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i].addActionListener(this);
			btnArray[i].setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			btnArray[i].setActionCommand("no");

			if (i < 8)
				btnArray[i].setBounds(i * 100 + 10, 90, 90, 30);
			else if (i >= 8 && i < 16)
				btnArray[i].setBounds(i % 8 * 100 + 10, 130, 90, 30);
			else if (i >= 16 && i < 24)
				btnArray[i].setBounds(i % 16 * 100 + 10, 170, 90, 30);
			else if (i >= 24 && i < 32)
				btnArray[i].setBounds(i % 24 * 100 + 10, 210, 90, 30);
			else if (i >= 32 && i < 40)
				btnArray[i].setBounds(i % 32 * 100 + 10, 250, 90, 30);
			frmAttendanceOf.getContentPane().add(btnArray[i]);
		}
	}

	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();
		if (btn.equals(resetBtn)) {
			for (int i = 0; i < btnArray.length; i++) {
				btnArray[i].setIcon(null);
				btnArray[i].setActionCommand("no");
			}
			present = 0;
			presentLbl.setText("PRESENT : ");
			absentLbl.setText("ABSENT  : ");
			return;
		}
		for (int i = 0; i < btnArray.length; i++) {
			if (btn.equals(btnArray[i]))
				if (btn.getActionCommand().equals("no")) {
					present++;
					btnArray[i].setIcon(new ImageIcon(FillAttendance.class.getResource("/sourceImages/right.png")));
					btnArray[i].setActionCommand("yes");
					presentLbl.setText("PRESENT : " + present);
					absentLbl.setText("ABSENT  : " + (total - present));
				} else {
					present--;
					btnArray[i].setIcon(new ImageIcon(FillAttendance.class.getResource("/sourceImages/wrong.png")));
					btnArray[i].setActionCommand("no");
					presentLbl.setText("PRESENT : " + present);
					absentLbl.setText("ABSENT  : " + (total - present));
				}
		}

	}

	public static void fillBtnArray(String section, JButton[] btnArray) {
		for (int i = 0; i < btnArray.length; i++) {
			if (section.equalsIgnoreCase("section-1")) {
				if (i < 9)
					btnArray[i] = new JButton("0" + (i + 1));
				else if (i >= 9 && i < 25)
					btnArray[i] = new JButton((i + 1) + "");
				else
					btnArray[i] = new JButton((i + 1) + 75 + "");
			} else if (section.equalsIgnoreCase("section-2")) {
				if (i < 25)
					btnArray[i] = new JButton("" + (i + 26));
				else
					btnArray[i] = new JButton((i + 16) + 75 + "");
			} else if (section.equalsIgnoreCase("section-3")) {
				if (i < 25)
					btnArray[i] = new JButton("" + (i + 51));
				else
					btnArray[i] = new JButton((i + 31) + 75 + "");
			}
		}
	}

}
