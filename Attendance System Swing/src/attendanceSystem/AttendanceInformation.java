package attendanceSystem;

import java.awt.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AttendanceInformation {

	JFrame frame;
	JTable stdInfoTable;
	JLabel infoLbl;
	private JButton exitBtn, searchBtn;
	private JTextField searchTfield;

	public AttendanceInformation(JButton[] btnArray) {
		initialize(btnArray);
	}

	private void initialize(JButton[] btnArray) {
		frame = new JFrame();
		frame.setTitle("Online Attendance");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AttendanceSystem.class.getResource("/sourceImages/Icon-school.png")));
		frame.setBounds(100, 100, 847, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		infoLbl = new JLabel("ATTENDANCE INFORMATION");

		infoLbl.setForeground(Color.BLUE);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		infoLbl.setBounds(200, 5, 390, 51);
		frame.getContentPane().add(infoLbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 66, 670, 360);
		frame.getContentPane().add(scrollPane);

		stdInfoTable = new JTable();
		stdInfoTable.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		stdInfoTable.setGridColor(Color.pink);
		scrollPane.setViewportView(stdInfoTable);

		exitBtn = new JButton("EXIT");
		exitBtn.setIcon(new ImageIcon(AttendanceInformation.class.getResource("/sourceImages/exit.png")));
		exitBtn.setBackground(Color.WHITE);
		exitBtn.setForeground(Color.BLUE);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure");
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		exitBtn.setBounds(684, 15, 113, 30);
		frame.getContentPane().add(exitBtn);

		JComboBox sectionsCBox = new JComboBox();
		sectionsCBox.addItem("Section-X");
		sectionsCBox.addItem("Section-1");
		sectionsCBox.addItem("Section-2");
		sectionsCBox.addItem("Section-3");
		sectionsCBox.setSelectedIndex(0);
		sectionsCBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JComboBox cBox = (JComboBox) e.getSource();
				String section = cBox.getSelectedItem().toString();
				FillAttendance.fillBtnArray(section, btnArray);
				String query = "select id,(select Count(*) from attendance where status='P' And id=a.id)  as Attended ,Count(*) As Total,"
						+ "((select Count(*) from attendance where status='P' And id=a.id)/Count(*))*100 As \"Percentage%\" FROM Attendance a Where id IN("
						+ btnArray[0].getText();
				;
				for (int i = 1; i < btnArray.length; i++)
					query += "," + btnArray[i].getText();
				query += ") group by id order by id";
				stdInfoTable.setModel(DbUtils.resultSetToTableModel(OracleRelatedTasks.executeAnyQuery(query)));
				stdInfoTable.repaint();
			}
		});
		sectionsCBox.setForeground(Color.BLUE);
		sectionsCBox.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		sectionsCBox.setBounds(15, 23, 129, 34);
		frame.getContentPane().add(sectionsCBox);

		searchTfield = new JTextField("E.g: 42");
		searchTfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) { // enter key
					sectionsCBox.setSelectedIndex(0);
					String rollNumber = searchTfield.getText();
					int num = Integer.parseInt(rollNumber), index = 0;
					if ((num >= 1 && num <= 25) || (num >= 101 && num <= 115))
						index = 1;
					if ((num >= 26 && num <= 50) || (num >= 116 && num <= 130))
						index = 2;
					if ((num >= 51 && num <= 75) || (num >= 131 && num <= 145))
						index = 3;
					sectionsCBox.setSelectedIndex(index);

					if (rollNumber.equals("") || rollNumber.equals("E.g: 42"))
						return;
					String query = "Select Unique ID,(Select Count(*) from attendance where status='P' AND ID="
							+ rollNumber + ") As \"Attended Classes\",(Select Count(*) from attendance where ID="
							+ rollNumber
							+ ") As \"Total Classes\",((Select Count(*) from attendance where status='P' AND ID="
							+ rollNumber + ")/(Select Count(*) from attendance where ID=" + rollNumber
							+ "))*100 AS \"Percentage%\" FROM Attendance Where id=" + rollNumber;
					stdInfoTable.setModel(DbUtils.resultSetToTableModel(OracleRelatedTasks.executeAnyQuery(query)));
					stdInfoTable.repaint();
				}
			}
		});
		searchTfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchTfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchTfield.getText().equals("") || searchBtn.getActionCommand().equals("Yes")) {
					searchTfield.setText("E.g: 42");
					searchBtn.setActionCommand("No");
				} else
					searchBtn.setActionCommand("Yes");
			}
		});
		searchTfield.setForeground(Color.BLUE);
		searchTfield.setHorizontalAlignment(SwingConstants.CENTER);
		searchTfield.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		searchTfield.setToolTipText("ROLL NUMBER-WISE SEARCH BOX");
		searchTfield.setBounds(15, 124, 130, 34);
		frame.getContentPane().add(searchTfield);
		searchTfield.setColumns(10);

		searchBtn = new JButton("SEARCH");
		searchBtn.setActionCommand("No");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = searchBtn.getActionCommand();
				String rollNumber = searchTfield.getText();
				if (rollNumber.equals("") || rollNumber.equals("E.g: 42"))
					return;
				int num = Integer.parseInt(rollNumber), index = 0;
				if ((num >= 1 && num <= 25) || (num >= 101 && num <= 115))
					index = 1;
				if ((num >= 26 && num <= 50) || (num >= 116 && num <= 130))
					index = 2;
				if ((num >= 51 && num <= 75) || (num >= 131 && num <= 145))
					index = 3;
				sectionsCBox.setSelectedIndex(index);

				if (rollNumber.equals("") || rollNumber.equals("E.g: 42"))
					return;
				String query = "Select Unique ID,(Select Count(*) from attendance where status='P' AND ID=" + rollNumber
						+ ") As \"Attended Classes\",(Select Count(*) from attendance where ID=" + rollNumber
						+ ") As \"Total Classes\",((Select Count(*) from attendance where status='P' AND ID="
						+ rollNumber + ")/(Select Count(*) from attendance where ID=" + rollNumber
						+ "))*100 AS \"Percentage%\" FROM Attendance Where id=" + rollNumber;
				stdInfoTable.setModel(DbUtils.resultSetToTableModel(OracleRelatedTasks.executeAnyQuery(query)));
				stdInfoTable.repaint();
				if (cmd.equals("No"))
					searchBtn.setActionCommand("Yes");
				else
					searchBtn.setActionCommand("No");

			}
		});
		searchBtn.setForeground(Color.BLUE);
		searchBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		searchBtn.setBackground(Color.WHITE);
		searchBtn.setBounds(25, 174, 107, 34);
		frame.getContentPane().add(searchBtn);

		JLabel rollNumLbl = new JLabel("ROLL NO#");
		rollNumLbl.setHorizontalAlignment(SwingConstants.LEFT);
		rollNumLbl.setForeground(Color.BLUE);
		rollNumLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		rollNumLbl.setBounds(15, 93, 107, 28);
		frame.getContentPane().add(rollNumLbl);

		JButton graphBtn = new JButton("GRAPH");
		graphBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rollNumber = JOptionPane.showInputDialog("Enter roll number");
					if (rollNumber == null | "".equals(rollNumber))
						return;
					float presentOccurrences, totalOccurrences;

					presentOccurrences = OracleRelatedTasks.noOfOccurrence(
							"Select count(*) from attendance where id=" + rollNumber + " And status='P'");
					totalOccurrences = OracleRelatedTasks
							.noOfOccurrence("Select count(*) from attendance where id=" + rollNumber);
					float percentage = (presentOccurrences / totalOccurrences) * 100;
					frame.setVisible(false);
					GraphClass.main(new String[] { rollNumber, percentage + "", presentOccurrences + "",
							totalOccurrences + "" });
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1);
					frame.setVisible(true);
				}

			}
		});
		graphBtn.setForeground(Color.BLUE);
		graphBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		graphBtn.setBackground(Color.WHITE);
		graphBtn.setBounds(25, 224, 107, 34);
		frame.getContentPane().add(graphBtn);
	}
}
