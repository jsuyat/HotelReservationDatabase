import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hotels {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
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
					Hotels window = new Hotels();
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
	public Hotels() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 573, 669);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(230, 6, 335, 267);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblHotelName = new JLabel("Hotel Name:");
		lblHotelName.setBounds(6, 49, 78, 16);
		panel.add(lblHotelName);

		JLabel lblBranchId = new JLabel("Branch ID:");
		lblBranchId.setBounds(16, 73, 78, 16);
		panel.add(lblBranchId);

		textField = new JTextField();
		textField.setBounds(95, 44, 194, 26);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(95, 68, 194, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(49, 97, 36, 16);
		panel.add(lblCity);

		textField_2 = new JTextField();
		textField_2.setBounds(95, 91, 194, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblState = new JLabel("State:");
		lblState.setBounds(41, 119, 43, 16);
		panel.add(lblState);

		textField_3 = new JTextField();
		textField_3.setBounds(95, 114, 194, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblZip = new JLabel("ZIP:");
		lblZip.setBounds(58, 142, 36, 16);
		panel.add(lblZip);

		textField_4 = new JTextField();
		textField_4.setBounds(95, 137, 194, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(33, 163, 61, 16);
		panel.add(lblAddress);

		textField_5 = new JTextField();
		textField_5.setBounds(95, 158, 194, 26);
		panel.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(49, 191, 48, 16);
		panel.add(lblPhone);

		textField_6 = new JTextField();
		textField_6.setBounds(95, 186, 194, 26);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAdd.setBounds(16, 232, 117, 29);
		panel.add(btnAdd);

		JLabel lblAddHotelData = new JLabel("Add Hotel data here:");
		lblAddHotelData.setBounds(95, 24, 170, 16);
		panel.add(lblAddHotelData);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(172, 232, 117, 29);
		panel.add(btnUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 279, 215, 267);
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
		btnSearch.setBounds(70, 232, 117, 29);
		panel_1.add(btnSearch);

		JLabel lblSearchHere = new JLabel("Search here:");
		lblSearchHere.setBounds(24, 24, 117, 16);
		panel_1.add(lblSearchHere);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(273, 279, 245, 267);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblDeleteCustomerData = new JLabel("Delete Hotel Data Here:");
		lblDeleteCustomerData.setBounds(29, 17, 175, 16);
		panel_3.add(lblDeleteCustomerData);

		JLabel lblCustomerId = new JLabel("Hotel Name:");
		lblCustomerId.setBounds(6, 56, 89, 16);
		panel_3.add(lblCustomerId);

		textField_7 = new JTextField();
		textField_7.setBounds(92, 51, 130, 26);
		panel_3.add(textField_7);
		textField_7.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(69, 232, 117, 29);
		panel_3.add(btnDelete);

		JLabel lblBranchId_1 = new JLabel("Branch ID:");
		lblBranchId_1.setBounds(6, 84, 75, 16);
		panel_3.add(lblBranchId_1);

		textField_9 = new JTextField();
		textField_9.setBounds(92, 79, 130, 26);
		panel_3.add(textField_9);
		textField_9.setColumns(10);

		JButton btnSeeHotelTable = new JButton("See Hotel Table");
		btnSeeHotelTable.setBounds(52, 105, 117, 71);
		btnSeeHotelTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFrame tableFrame = new JFrame("Hotel Table");
				tableFrame.setBounds(100, 100, 878, 614);
				tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tableFrame.getContentPane().setLayout(null);

				JPanel panel = new JPanel();
				panel.setBounds(6, 6, 438, 266);
				tableFrame.getContentPane().add(panel);

				String[] columns = new String[]{"Hotels", "Branch ID", "City", "State", "ZIP", "Address", "Phone"};
				Object[][] data = getHotelData();
				table = new JTable(data, columns);
				panel.add(table);
				tableFrame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnSeeHotelTable);
	}

	private Object[][] getHotelData(){
		try{
			HotelDatabase database = new HotelDatabase();
			HotelDatabase[] hotel = database.loadAll();
			Object[][] row = new Object[20][7];
			for(int i = 0 ; i < hotel.length; i++){
				row[i][0] = (Object) hotel[i].getHotelName();
				row[i][1] = (Object) hotel[i].getBranchID();
				row[i][2] = (Object) hotel[i].getCity();
				row[i][3] = (Object) hotel[i].getState();
				row[i][4] = (Object) hotel[i].getZIP();
				row[i][5] = (Object) hotel[i].getAddress();
				row[i][6] = (Object) hotel[i].getPhone();

			}
			return row;
	  }
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
