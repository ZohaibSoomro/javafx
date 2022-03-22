package attendanceSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class InsertUpdateDelete {

	JFrame frame;
	public InsertUpdateDelete(String arg) {
		initialize(arg);
	}

	private void initialize(String arg) {
		frame = new JFrame();
		frame.setTitle("Attendance System");
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AttendanceSystem.class.getResource("/sourceImages/Icon-school.png")));
		frame.setResizable(false);
		frame.setBounds(2, 10, 688, 455);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton stdRecord = new JButton(arg.toUpperCase() + " STUDENT'S RECORD");
		stdRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Data student = new Data(arg, "student");
				student.frame.setVisible(true);
				student.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});
			}
		});
		stdRecord.setForeground(Color.BLUE);
		stdRecord.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		stdRecord.setBounds(130, 86, 397, 70);
		frame.getContentPane().add(stdRecord);

		JButton tchrRecord = new JButton(arg.toUpperCase() + " TEACHER'S RECORD");
		tchrRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Data teacher = new Data(arg, "teacher");
				teacher.frame.setVisible(true);
				teacher.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						frame.setVisible(true);
					}
				});
			}
		});
		tchrRecord.setForeground(Color.BLUE);
		tchrRecord.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		tchrRecord.setBounds(130, 189, 397, 70);
		frame.getContentPane().add(tchrRecord);

		JButton exitBtn = new JButton("EXIT");
		exitBtn.setBackground(Color.WHITE);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure");
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setIcon(new ImageIcon(InsertUpdateDelete.class.getResource("/sourceImages/exit.png")));
		exitBtn.setForeground(Color.BLUE);
		exitBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		exitBtn.setBounds(553, 16, 98, 37);
		frame.getContentPane().add(exitBtn);

	}
}
