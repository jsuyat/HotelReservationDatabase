import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HotelReservationSystem {

	private JFrame frame;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelReservationSystem window = new HotelReservationSystem();
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
	public HotelReservationSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*frame = new JFrame();
		frame.setBounds(100, 100, 625, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnCustomer = new JButton("Customers");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customerWindow = new Customer();
			}
		});
		btnCustomer.setBounds(17, 56, 117, 68);
		frame.getContentPane().add(btnCustomer);

		JButton btnHotels = new JButton("Hotels");
		btnHotels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotels hotelWindow = new Hotels();
			}
		});
		btnHotels.setBounds(161, 56, 117, 68);
		frame.getContentPane().add(btnHotels);*/

		frame = new JFrame();
		frame.setBounds(100, 100, 693, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnCustomer = new JButton("Customers");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customerWindow = new Customer();
			}
		});
		btnCustomer.setBounds(17, 56, 117, 68);
		frame.getContentPane().add(btnCustomer);

		JButton btnHotels = new JButton("Hotels");
		btnHotels.setBounds(17, 121, 117, 68);
		btnHotels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotels newHotelWindow = new Hotels();
			}
		});
		frame.getContentPane().add(btnHotels);


		JButton btnMakes = new JButton("Makes");
		btnMakes.setBounds(140, 56, 117, 68);
		btnMakes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Makes makesWindow = new Makes();
			}
		});
		frame.getContentPane().add(btnMakes);

		JButton btnHas = new JButton("Has");
		btnHas.setBounds(140, 121, 117, 68);
		btnHas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Availability availableWindow = new Availability();
			}
		});
		frame.getContentPane().add(btnHas);

		JButton btnRoom = new JButton("Rooms");
		btnRoom.setBounds(260, 121, 117, 68);
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room roomWindow = new Room();
			}
		});
		frame.getContentPane().add(btnRoom);

		JButton btnReservation = new JButton("Reservation");
		btnReservation.setBounds(260, 56, 117, 68);
		frame.getContentPane().add(btnReservation);
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservation reservationWindow = new Reservation();
			}
		});

		JButton btnInfo = new JButton("Information");
		btnInfo.setBounds(76, 255, 117, 68);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info infoWindow = new Info();
			}
		});
		frame.getContentPane().add(btnInfo);

		JLabel lblClickOnOne = new JLabel("Click on one of the buttons below to view information:");
		lblClickOnOne.setBounds(17, 28, 346, 16);
		frame.getContentPane().add(lblClickOnOne);

		JLabel lblSearchDatabaseHere = new JLabel("Get your Reservation Information:");
		lblSearchDatabaseHere.setBounds(441, 28, 212, 16);
		frame.getContentPane().add(lblSearchDatabaseHere);

		JLabel lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setBounds(417, 56, 83, 16);
		frame.getContentPane().add(lblCustomerId);

		textField = new JTextField();
		textField.setBounds(512, 56, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblGetAvailabilityAnd = new JLabel("Get Availability and Prices:");
		lblGetAvailabilityAnd.setBounds(441, 173, 174, 16);
		frame.getContentPane().add(lblGetAvailabilityAnd);
	}
}
