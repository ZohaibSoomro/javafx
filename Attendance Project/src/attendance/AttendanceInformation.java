package attendance;

import java.awt.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AttendanceInformation {

	JFrame frame;
	JTable stdInfoTable;
	JLabel infoLbl;
	private JButton exitBtn;
	private JTextField searchTfield;

	public AttendanceInformation(JButton[] btnArray) {
		initialize(btnArray);
	}

	private void initialize(JButton[] btnArray) {
		frame = new JFrame();
		frame.setTitle("Online Attendance");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 847, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		infoLbl = new JLabel("ATTENDANCE INFORMATION");

		infoLbl.setForeground(Color.BLUE);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		infoLbl.setBounds(200, 5, 380, 51);
		frame.getContentPane().add(infoLbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 66, 670, 360);
		frame.getContentPane().add(scrollPane);

		stdInfoTable = new JTable();
		stdInfoTable.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		stdInfoTable.setGridColor(Color.pink);
		scrollPane.setViewportView(stdInfoTable);

		exitBtn = new JButton("EXIT");
		exitBtn.setIcon(new ImageIcon(AttendanceInformation.class.getResource("/resources/exit.png")));
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
		exitBtn.setBounds(630, 16, 113, 34);
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
				String query = "Select ID,STATUS FROM (Select ID,STATUS FROM Attendance order by date_time desc) where rownum<=40 AND id IN("
						+ btnArray[0].getText();
				for (int i = 1; i < btnArray.length; i++)
					query += "," + btnArray[i].getText();
				query += ") order by id";
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
					String rollNumber = searchTfield.getText();
					if (rollNumber.equals("") || rollNumber.equals("E.g: 42"))
						return;
					String query = "Select ID,STATUS FROM Attendance Where id=" + rollNumber;
					stdInfoTable.setModel(DbUtils.resultSetToTableModel(OracleRelatedTasks.executeAnyQuery(query)));
					stdInfoTable.repaint();
				}
			}
		});
		searchTfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchTfield.getText().equals("E.g: 42"))
					searchTfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchTfield.getText().equals(""))
					searchTfield.setText("E.g: 42");

			}
		});
		searchTfield.setForeground(Color.BLUE);
		searchTfield.setHorizontalAlignment(SwingConstants.CENTER);
		searchTfield.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		searchTfield.setToolTipText("ROLL NUMBER-WISE SEARCH BOX");
		searchTfield.setBounds(15, 124, 130, 34);
		frame.getContentPane().add(searchTfield);
		searchTfield.setColumns(10);

		JButton searchBtn = new JButton("SEARCH");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rollNumber = searchTfield.getText();
				if (rollNumber.equals("") || rollNumber.equals("E.g: 42"))
					return;
				String query = "Select ID,STATUS,date_time FROM Attendance Where id=" + Integer.parseInt(rollNumber);
				stdInfoTable.setModel(DbUtils.resultSetToTableModel(OracleRelatedTasks.executeAnyQuery(query)));
				stdInfoTable.repaint();
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
				String rollNumber = JOptionPane.showInputDialog("Enter roll number");
				if (rollNumber.equals(""))
					return;
				float presentOccurrences, totalOccurrences;
				try {

					presentOccurrences = OracleRelatedTasks.noOfOccurrence(
							"Select count(*) from attendance where id=" + rollNumber + " And status='P'");
					totalOccurrences = OracleRelatedTasks
							.noOfOccurrence("Select count(*) from attendance where id=" + rollNumber);
					float percentage = (presentOccurrences / totalOccurrences) * 100;
					frame.setVisible(false);
					GraphClass.main(new String[] { rollNumber, percentage + "", presentOccurrences + "",
							totalOccurrences + "" });
				} catch (Exception e1) {
					e1.printStackTrace();
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
