import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Reservation {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation window = new Reservation();
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
	public Reservation() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 410);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 299, 181);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblRNum = new JLabel("Reservation Number:");
		lblRNum.setBounds(6, 49, 78, 16);
		panel.add(lblRNum);

		JLabel lblParty = new JLabel("Party Size:");
		lblParty.setBounds(16, 73, 78, 16);
		panel.add(lblParty);

		textField = new JTextField();
		textField.setBounds(95, 44, 194, 26);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(95, 68, 194, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(49, 97, 36, 16);
		panel.add(lblTotal);

		textField_2 = new JTextField();
		textField_2.setBounds(95, 91, 194, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rNumText = Integer.parseInt(textField.getText());
				int partySizeText = Integer.parseInt(textField_1.getText());
				int totalText = Integer.parseInt(textField_2.getText());

				try{
					ReservationDatabase database = new ReservationDatabase();
					database.insertData(rNumText, partySizeText, totalText);
				}
				catch(SQLException err){
					err.printStackTrace();
				}
			 }

		});
		btnAdd.setBounds(16, 129, 117, 29);
		panel.add(btnAdd);

		JLabel lblAddHotelData = new JLabel("Update/Add new Reservation Data here:");
		lblAddHotelData.setBounds(16, 23, 250, 16);
		panel.add(lblAddHotelData);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(166, 129, 117, 29);
		panel.add(btnUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(317, 6, 215, 181);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblAttribute = new JLabel("Attribute:");
		lblAttribute.setBounds(6, 52, 70, 16);
		panel_1.add(lblAttribute);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(6, 96, 61, 16);
		panel_1.add(lblData);

		textField_7 = new JTextField();
		textField_7.setBounds(70, 47, 130, 26);
		panel_1.add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setBounds(70, 91, 130, 26);
		panel_1.add(textField_8);
		textField_8.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(39, 131, 117, 29);
		panel_1.add(btnSearch);

		JLabel lblSearchHere = new JLabel("Search here:");
		lblSearchHere.setBounds(24, 24, 117, 16);
		panel_1.add(lblSearchHere);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 199, 299, 181);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblDeleteCustomerData = new JLabel("Delete Reservation Data Here:");
		lblDeleteCustomerData.setBounds(29, 17, 175, 16);
		panel_3.add(lblDeleteCustomerData);

		JLabel lblRnumDelete = new JLabel("Reservation Number:");
		lblRnumDelete.setBounds(6, 56, 89, 16);
		panel_3.add(lblRnumDelete);

		textField_7 = new JTextField();
		textField_7.setBounds(92, 51, 181, 26);
		panel_3.add(textField_7);
		textField_7.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int deleteText = Integer.parseInt(textField_7.getText());
				try{
					ReservationDatabase database = new ReservationDatabase();
					database.delete(deleteText);

				} catch(SQLException err){
					err.printStackTrace();
				}
				textField_7.setText("");
			}
		});
		btnDelete.setBounds(29, 130, 117, 29);
		panel_3.add(btnDelete);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(317, 199, 215, 181);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnSeeCustomerTable = new JButton("See Reservation Table");
		btnSeeCustomerTable.setBounds(38, 48, 141, 76);
		btnSeeCustomerTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame tableFrame = new JFrame("Reservation Table");
				tableFrame.setBounds(100, 100, 878, 614);
				tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tableFrame.getContentPane().setLayout(null);

				JPanel panel = new JPanel();
				panel.setBounds(6, 6, 438, 266);
				tableFrame.getContentPane().add(panel);

				String[] columns = new String[]{"Reservation Number", "Party Size", "Total"};
				Object[][] data = getReservationData();
				table = new JTable(data, columns);
				panel.add(table);
				tableFrame.setVisible(true);
			}
		});
		panel_2.add(btnSeeCustomerTable);
	}

	private Object[][] getReservationData(){
		try{
			ReservationDatabase database = new ReservationDatabase();
			ReservationDatabase[] reservations = database.loadAll();
			Object[][] row = new Object[20][5];
			for(int i = 0 ; i < reservations.length; i++){
				row[i][0] = reservations[i].getRNum();
				row[i][1] = reservations[i].getPartySize();
				row[i][2] = reservations[i].getTotal();
			}
			return row;
	  }
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
