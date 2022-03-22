package attendanceSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.*;

public class Data {

	JFrame frame;
	private JTextField idTField;
	private JTextField nameTField;
	private JTextField surnameTfield;
	private JTextField ageTField;
	private JTextField emailTField;
	private JTextField cityTField;
	private JTextField dateTField;
	JLabel record, mandatorLbl, idLabel, nameLbl, surnameLbl, ageLbl, emailLbl, cityLbl, dateLbl;
	JButton actionBtn;
	Connection connection = null;
	private JButton exitBtn;

	
	public Data(String arg, String arg2) {
		initialize(arg, arg2);
	}


	private void initialize(String arg, String arg2) {
		frame = new JFrame();
		frame.setTitle("Attendance System");
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AttendanceSystem.class.getResource("/sourceImages/Icon-school.png")));
		frame.setResizable(false);
		frame.setBounds(2, 10, 605, 522);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		record = new JLabel("RECORD");
		if (arg.equalsIgnoreCase("update"))
			record.setText("UPDATE A RECORD");
		else if (arg.equalsIgnoreCase("delete"))
			record.setText("DELETE A RECORD");
		else if (arg.equalsIgnoreCase("insert"))
			record.setText("INSERT NEW RECORD");
		record.setForeground(new Color(0, 0, 255));
		record.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		record.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(record);
		record.setBounds(96, 16, 382, 37);

		JButton resetBtn = new JButton("RESET");
		resetBtn.setBackground(Color.WHITE);
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idTField.setText("");
				nameTField.setText("");
				surnameTfield.setText("");
				ageTField.setText("");
				emailTField.setText("");
				cityTField.setText("");
				dateTField.setText("");
				JOptionPane.showMessageDialog(null, "Reset Done!");
				idTField.requestFocus();
			}
		});
		resetBtn.setIcon(new ImageIcon(Data.class.getResource("/sourceImages/reset.png")));
		resetBtn.setForeground(Color.BLUE);
		resetBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		resetBtn.setBounds(467, 24, 117, 37);
		frame.getContentPane().add(resetBtn);

		exitBtn = new JButton("EXIT");
		exitBtn.setBackground(Color.WHITE);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure");
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setIcon(new ImageIcon(Data.class.getResource("/sourceImages/exit.png")));
		exitBtn.setForeground(Color.BLUE);
		exitBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		exitBtn.setBounds(467, 69, 117, 37);
		frame.getContentPane().add(exitBtn);

		idLabel = new JLabel("ID*");
		idLabel.setForeground(Color.BLUE);
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		idLabel.setBounds(15, 79, 113, 27);

		nameLbl = new JLabel("NAME");
		nameLbl.setForeground(Color.BLUE);
		nameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLbl.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		nameLbl.setBounds(15, 122, 113, 27);

		surnameLbl = new JLabel("SURNAME");
		surnameLbl.setForeground(Color.BLUE);
		surnameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		surnameLbl.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		surnameLbl.setBounds(15, 165, 113, 27);

		ageLbl = new JLabel("AGE");
		ageLbl.setForeground(Color.BLUE);
		ageLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ageLbl.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		ageLbl.setBounds(15, 208, 113, 27);

		emailLbl = new JLabel("EMAIL");
		emailLbl.setForeground(Color.BLUE);
		emailLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLbl.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		emailLbl.setBounds(15, 251, 113, 27);

		cityLbl = new JLabel("CITY");
		cityLbl.setForeground(Color.BLUE);
		cityLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		cityLbl.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		cityLbl.setBounds(15, 294, 113, 27);

		dateLbl = new JLabel("DATE(YYYY-MM-DD)");
		dateLbl.setForeground(Color.BLUE);
		dateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLbl.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		dateLbl.setBounds(2, 337, 160, 27);

		idTField = new JTextField();
		idTField.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		idTField.setBounds(180, 79, 282, 27);
		idTField.setColumns(10);

		nameTField = new JTextField();
		nameTField.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		nameTField.setColumns(10);
		nameTField.setBounds(180, 122, 282, 27);

		surnameTfield = new JTextField();
		surnameTfield.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		surnameTfield.setColumns(10);
		surnameTfield.setBounds(180, 165, 282, 27);

		ageTField = new JTextField();
		ageTField.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		ageTField.setColumns(10);
		ageTField.setBounds(180, 208, 282, 27);

		emailTField = new JTextField();
		emailTField.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		emailTField.setColumns(10);
		emailTField.setBounds(180, 251, 282, 27);

		cityTField = new JTextField();
		cityTField.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		cityTField.setColumns(10);
		cityTField.setBounds(180, 294, 282, 27);

		dateTField = new JTextField();
		dateTField.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		dateTField.setColumns(10);
		dateTField.setBounds(180, 337, 282, 27);

		actionBtn = new JButton(arg.toUpperCase());
		actionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idTField.getText());
				String name = nameTField.getText();
				String surname, age, email, city;
				Date date = null;
				PreparedStatement stmt = null;
				try {
					connection = OracleConnection.oracleConnection();
					if (arg.equalsIgnoreCase("delete")) {

						if (!name.equals("")) {
							stmt = connection.prepareStatement(
									"delete from " + arg2 + " Where id=" + id + " And name Like('" + name + "')");
							stmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Deletion Done!\nName:" + name + "\nID:" + id);
						} else {
							JOptionPane.showMessageDialog(null, "Insert All Data!");
						}
					} else if (arg.equalsIgnoreCase("insert")) {
						age = ageTField.getText();
						surname = surnameTfield.getText();
						email = emailTField.getText();
						city = cityTField.getText();
						date = new SimpleDateFormat("yyyy-MM-dd").parse(dateTField.getText());
						if (!name.equals("") || !surname.equals("") || !email.equals("") || !city.equals("")
								|| !age.equals("") || !(date == null)) {
							stmt = connection.prepareStatement("insert into " + arg2 + " values(?,?,?,?,?,?,?)");
							stmt.setInt(1, id);
							stmt.setString(2, name);
							stmt.setString(3, surname);
							stmt.setInt(4, Integer.parseInt(age));
							stmt.setString(5, email);
							stmt.setString(6, city);
							stmt.setDate(7, new java.sql.Date(date.getTime()));
							stmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Insertion Done!\nName:" + name + "\nID:" + id);
						} else {
							JOptionPane.showMessageDialog(null, "Insert All Data!");
						}

					} else if (arg.equalsIgnoreCase("update")) {
						age = ageTField.getText();
						surname = surnameTfield.getText();
						email = emailTField.getText();
						city = cityTField.getText();

						String query = "update " + arg2 + " set ";
						if (!name.equals(""))
							query += "name='" + name + "' ";
						if (!surname.equals("")) {
							query = checkComma(query, arg2);
							query += "surname='" + surname + "' ";
						}
						if (!age.equals("")) {
							query = checkComma(query, arg2);
							query += "age=" + Integer.parseInt(age) + " ";
						}
						if (!email.equals("")) {
							query = checkComma(query, arg2);
							query += "email='" + email + "' ";
						}
						if (!city.equals("")) {
							query = checkComma(query, arg2);
							query += "city='" + city + "' ";
						}
						query += " Where id=" + id;
						System.out.println(query);
						stmt = connection.prepareStatement(query);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Updation Done!" + "\nID:" + id);

					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		actionBtn.setForeground(Color.BLUE);
		actionBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		actionBtn.setBounds(219, 386, 153, 37);

		mandatorLbl = new JLabel("Field having (*) is mandatory. ");
		mandatorLbl.setForeground(Color.RED);
		mandatorLbl.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mandatorLbl.setBounds(15, 439, 276, 27);

		// adding components
		if (arg.equalsIgnoreCase("delete")) {
			actionBtn.setBounds(219, 170, 153, 37);
			mandatorLbl.setBounds(15, 230, 276, 27);
			frame.setSize(new Dimension(605, 320));
			frame.setLocationRelativeTo(null);
			addDeleteComponents();
		}

		else if (arg.equalsIgnoreCase("insert") || arg.equalsIgnoreCase("update")) {
			actionBtn.setBounds(219, 386, 153, 37);
			mandatorLbl.setBounds(15, 439, 276, 27);
			addInsertUpdateComponents(arg);
		}

	}

	public void addInsertUpdateComponents(String arg) {
		frame.getContentPane().add(idLabel);
		frame.getContentPane().add(idTField);
		frame.getContentPane().add(nameLbl);
		frame.getContentPane().add(nameTField);
		frame.getContentPane().add(actionBtn);
		frame.getContentPane().add(mandatorLbl);
		frame.getContentPane().add(surnameLbl);
		frame.getContentPane().add(surnameTfield);
		frame.getContentPane().add(ageLbl);
		frame.getContentPane().add(ageTField);
		frame.getContentPane().add(emailLbl);
		frame.getContentPane().add(emailTField);
		frame.getContentPane().add(cityLbl);
		frame.getContentPane().add(cityTField);
		if (!arg.equals("update")) {
			frame.getContentPane().add(dateLbl);
			frame.getContentPane().add(dateTField);
		}
	}

	public void addDeleteComponents() {
		frame.getContentPane().add(idLabel);
		frame.getContentPane().add(idTField);
		frame.getContentPane().add(nameLbl);
		frame.getContentPane().add(nameTField);
		frame.getContentPane().add(actionBtn);
		frame.getContentPane().add(mandatorLbl);
	}

	public String checkComma(String q, String table) {
		if (!q.equalsIgnoreCase("update " + table + " set "))
			q += ", ";
		return q;
	}
}
