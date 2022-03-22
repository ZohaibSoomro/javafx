import java.awt.*;
import java.util.*;
import javax.swing.*;

public class DigitalClock {

	private JFrame frame;
	private JLabel clockLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitalClock window = new DigitalClock();
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
	public DigitalClock() {
		initialize();
		clockData();
	}

	public void clockData() {
		
		Thread t1 = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(;;) {
					Calendar calendar=new GregorianCalendar();
					int day   = calendar.get(Calendar.DAY_OF_MONTH);
					int month = calendar.get(Calendar.MONTH);
					int year  = calendar.get(Calendar.YEAR);

					int second = calendar.get(Calendar.SECOND);
					int minute = calendar.get(Calendar.MINUTE);
					int hour   = calendar.get(Calendar.HOUR);
					
					if (hour==0) {
						hour=12;
					}
					clockLbl.setText("Time " + hour + ":" + minute + ":" + second + " Date " + day + "/" + (month+1) + "/" + year);
				}
			}
		};
		t1.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		clockLbl = new JLabel("Clock");
		clockLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		clockLbl.setBounds(28, 62, 734, 293);
		frame.getContentPane().add(clockLbl);
	}

}
