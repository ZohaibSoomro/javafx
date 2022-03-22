package attendanceSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Information {

	JFrame frame;
	JTable stdInfoTable;
	JLabel infoLbl;
	private JButton exitBtn;

	public Information(String arg) {
		initialize(arg);
	}


	private void initialize(String arg) {
		frame = new JFrame();
		frame.setTitle("Attendance System");
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AttendanceSystem.class.getResource("/sourceImages/Icon-school.png")));
		frame.setResizable(false);
		frame.setBounds(2, 10, 1280, 615);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		if (arg.equalsIgnoreCase("student"))
			infoLbl = new JLabel("STUDENTS INFORMATION");
		else if (arg.equalsIgnoreCase("teacher"))
			infoLbl = new JLabel("TEACHERS INFORMATION");
		else
			throw new IllegalArgumentException("Illegal Argument!");
		infoLbl.setForeground(Color.BLUE);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		infoLbl.setBounds(400, 5, 456, 51);
		frame.getContentPane().add(infoLbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 66, 1255, 477);
		frame.getContentPane().add(scrollPane);

		stdInfoTable = new JTable();
		scrollPane.setViewportView(stdInfoTable);

		exitBtn = new JButton("EXIT");
		exitBtn.setBackground(Color.WHITE);
		exitBtn.setForeground(Color.BLUE);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure");
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setIcon(new ImageIcon(Information.class.getResource("/sourceImages/exit.png")));
		exitBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		exitBtn.setBounds(1116, 16, 102, 34);
		frame.getContentPane().add(exitBtn);
	}
}
