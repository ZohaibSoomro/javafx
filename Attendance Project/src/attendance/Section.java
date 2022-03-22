package attendance;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import javafx.application.Platform;

public class Section {

    private JFrame sectionFrame;
	private JButton section1Btn, section2Btn, section3Btn;
	private FillAttendance attendance;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Section window = new Section();
					window.sectionFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Section() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		sectionFrame = new JFrame();
		sectionFrame.setTitle("Online Attendance");
		sectionFrame.setBounds(100, 100, 830, 480);
		sectionFrame.setLocationRelativeTo(null);
		sectionFrame.setResizable(false);
		sectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sectionFrame.getContentPane().setLayout(null);

		JLabel batch19Lbl = new JLabel("19 BATCH ATTENDANCE");
		batch19Lbl.setForeground(Color.BLUE);
		batch19Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		batch19Lbl.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		batch19Lbl.setBounds(192, 16, 363, 56);
		sectionFrame.getContentPane().add(batch19Lbl);

		section1Btn = new JButton("SECTION-1");
		section1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sectionFrame.setVisible(false);
				attendance = new FillAttendance("section-1");
				attendance.frmAttendanceOf.setVisible(true);
				attendance.frmAttendanceOf.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						sectionFrame.setVisible(true);
					}
				});
			}
		});
		section1Btn.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		section1Btn.setForeground(Color.BLUE);
		section1Btn.setBounds(312, 101, 185, 48);
		sectionFrame.getContentPane().add(section1Btn);

		section2Btn = new JButton("SECTION-2");
		section2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sectionFrame.setVisible(false);
				attendance = new FillAttendance("section-2");
				attendance.frmAttendanceOf.setVisible(true);
				attendance.frmAttendanceOf.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						sectionFrame.setVisible(true);
					}
				});
			}
		});
		section2Btn.setForeground(Color.BLUE);
		section2Btn.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		section2Btn.setBounds(312, 184, 185, 48);
		sectionFrame.getContentPane().add(section2Btn);

		section3Btn = new JButton("SECTION-3");
		section3Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sectionFrame.setVisible(false);
				attendance = new FillAttendance("section-3");
				attendance.frmAttendanceOf.setVisible(true);
				attendance.frmAttendanceOf.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						sectionFrame.setVisible(true);
					}
				});
			}
		});
		section3Btn.setForeground(Color.BLUE);
		section3Btn.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		section3Btn.setBounds(312, 269, 185, 48);
		sectionFrame.getContentPane().add(section3Btn);

		JButton checkAttendBtn = new JButton("CHECK ATTENDANCE");
		checkAttendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sectionFrame.setVisible(false);
				AttendanceInformation info;
				if (attendance != null)
					info = new AttendanceInformation(attendance.getButtonArray());
				else
					info = new AttendanceInformation(new JButton[40]);
				info.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						Platform.exit();
						System.exit(0);
					}
				});
			}
		});
		checkAttendBtn.setForeground(Color.BLUE);
		checkAttendBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		checkAttendBtn.setBounds(32, 349, 244, 39);
		sectionFrame.getContentPane().add(checkAttendBtn);
	}
};