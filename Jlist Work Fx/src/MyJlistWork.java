import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class MyJlistWork {

	private JFrame frame;
	private Connection connection = null;
	private JList listVar;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField searchTextField;
	private JComboBox comboBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyJlistWork window = new MyJlistWork();
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
	public MyJlistWork() {
		initialize();
		try {
			// Class.forName("org.sqlite.JDBC");
			JOptionPane.showMessageDialog(null, "Driver Loaded...");
			connection = DriverManager.getConnection("jdbc:sqlite:F:My SQLite Database work/StudentDb.db");
			JOptionPane.showMessageDialog(null, "Connection established...");
		} /*
			 * catch (ClassNotFoundException e) {
			 * 
			 * JOptionPane.showMessageDialog(null,"Driver not Loaded..."); }
			 */catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e);
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton loadDataBtn = new JButton("Load Names");
		loadDataBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		loadDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "Select * from Student";

				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet rSet = preparedStatement.executeQuery();

					DefaultListModel listModel = new DefaultListModel();
					while (rSet.next()) {
						listModel.addElement(rSet.getString("Name"));
					}

					listVar.setModel(listModel);

					rSet = preparedStatement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rSet));

					preparedStatement.close();
					rSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		loadDataBtn.setBounds(15, 27, 160, 50);
		frame.getContentPane().add(loadDataBtn);

		listVar = new JList();
		listVar.setBounds(25, 105, 177, 284);
		frame.getContentPane().add(listVar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 105, 580, 284);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel searchBtn = new JLabel("Search Result");
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchBtn.setHorizontalAlignment(SwingConstants.LEFT);
		searchBtn.setBounds(420, 33, 132, 38);
		frame.getContentPane().add(searchBtn);

		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String query = "select * from Student where " + comboBox.getSelectedItem() + "=?";
					PreparedStatement pStatement = connection.prepareStatement(query);
					pStatement.setString(1, searchTextField.getText());
					ResultSet rSet = pStatement.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rSet));

					rSet.close();
					pStatement.close();

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		searchTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchTextField.setBounds(567, 27, 243, 51);
		frame.getContentPane().add(searchTextField);
		searchTextField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ID", "Rollnum", "Name", "Address" }));
		comboBox.setBounds(217, 27, 177, 50);
		frame.getContentPane().add(comboBox);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		fileMenu.setIcon(new ImageIcon("C:\\Users\\Zohaib Hassan Soomro\\Desktop\\zohaib1.jpg"));
		menuBar.add(fileMenu);

		JMenu newMenu = new JMenu("New");
		fileMenu.add(newMenu);

		JMenuItem javaProjectMenuItem = new JMenuItem("Java Project");
		newMenu.add(javaProjectMenuItem);

		JMenuItem projectMenuItem = new JMenuItem("Project");
		newMenu.add(projectMenuItem);

		JRadioButtonMenuItem maleRadioMenuItem = new JRadioButtonMenuItem("Male");
		maleRadioMenuItem.setIcon(new ImageIcon("C:\\Users\\Zohaib Hassan Soomro\\Desktop\\zohaib1.jpg"));
		buttonGroup.add(maleRadioMenuItem);
		newMenu.add(maleRadioMenuItem);

		JRadioButtonMenuItem femaleRadioMenuItem = new JRadioButtonMenuItem("Female");
		buttonGroup.add(femaleRadioMenuItem);
		newMenu.add(femaleRadioMenuItem);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.RED);
		newMenu.add(separator);

		JMenuItem openProjectMenuItem = new JMenuItem("Open Project");
		fileMenu.add(openProjectMenuItem);

		JSeparator separator2 = new JSeparator();
		separator2.setBackground(Color.GREEN);
		fileMenu.add(separator2);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you wanna exit?", "Confirm",
																JOptionPane.YES_NO_OPTION);
				
				if (response==0) {
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		fileMenu.add(exitMenuItem);

		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem("Checkbox");
		editMenu.add(checkbox);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.CYAN);
		separator_1.setForeground(Color.YELLOW);
		editMenu.add(separator_1);
		
		JRadioButtonMenuItem radioBtn = new JRadioButtonMenuItem("Radio Button");
		editMenu.add(radioBtn);

		JMenu sourceMenu = new JMenu("Source");
		menuBar.add(sourceMenu);
	}
}
