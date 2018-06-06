import java.awt.EventQueue;
import java.sql.*; //JDBC packages
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Customer {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;
	private JTable table_1;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
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
	public Customer() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 553, 614);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(268, 6, 276, 204);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer ID:");
		lblNewLabel.setBounds(6, 34, 83, 16);
		panel.add(lblNewLabel);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(16, 61, 75, 16);
		panel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(14, 89, 75, 16);
		panel.add(lblLastName);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(36, 109, 53, 16);
		panel.add(lblGender);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(53, 136, 36, 16);
		panel.add(lblAge);

		textField = new JTextField();
		textField.setBounds(93, 29, 177, 26);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(93, 56, 177, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(93, 84, 177, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(93, 104, 177, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(94, 131, 177, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerText = textField.getText();
				String firstText = textField_1.getText();
				String lastText = textField_2.getText();
				String genderText = textField_3.getText();
				int ageText = Integer.parseInt(textField_4.getText());

				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");

				try{
					CustomerDatabase database = new CustomerDatabase();
					database.insertData(customerText, firstText, lastText, genderText, ageText);
				}
				catch(SQLException err){
					err.printStackTrace();
				}

				frame = new JFrame();
	    	frame.setBounds(100, 100, 258, 143);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().setLayout(null);

				JLabel lblInsertSuccessful = new JLabel("Insert Successful!");
				lblInsertSuccessful.setBounds(66, 28, 111, 16);
				frame.getContentPane().add(lblInsertSuccessful);

				JButton btnOk = new JButton("OK");
				btnOk.setBounds(60, 77, 117, 29);
				frame.getContentPane().add(btnOk);
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(6, 169, 117, 29);
		panel.add(btnNewButton);

		JLabel lblAddNewCustomer = new JLabel("Update/Add new Customer Data here");
		lblAddNewCustomer.setBounds(25, 6, 245, 16);
		panel.add(lblAddNewCustomer);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerText = textField.getText();
				String firstText = textField_1.getText();
				String lastText = textField_2.getText();
				String genderText = textField_3.getText();
				int ageText = Integer.parseInt(textField_4.getText());

				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");

				try{
					CustomerDatabase database = new CustomerDatabase();
					//if(customerText == ""){customerText = null;}
					database.update(customerText, firstText, lastText, genderText, ageText);
				}
				catch(SQLException err){
					err.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(134, 169, 117, 29);
		panel.add(btnUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 219, 250, 204);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Search for Customer Data here");
		lblNewLabel_1.setBounds(29, 20, 204, 16);
		panel_1.add(lblNewLabel_1);

		JLabel lblAttribute = new JLabel("Attribute:");
		lblAttribute.setBounds(6, 48, 61, 16);
		panel_1.add(lblAttribute);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(29, 76, 39, 16);
		panel_1.add(lblData);

		textField_5 = new JTextField();
		textField_5.setBounds(70, 43, 130, 26);
		panel_1.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(70, 71, 130, 26);
		panel_1.add(textField_6);
		textField_6.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(57, 155, 117, 29);
		panel_1.add(btnSearch);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(268, 219, 276, 204);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblDeleteCustomerData = new JLabel("Delete Customer Data Here:");
		lblDeleteCustomerData.setBounds(29, 17, 175, 16);
		panel_3.add(lblDeleteCustomerData);

		JLabel lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setBounds(6, 56, 89, 16);
		panel_3.add(lblCustomerId);

		textField_7 = new JTextField();
		textField_7.setBounds(92, 51, 178, 26);
		panel_3.add(textField_7);
		textField_7.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerText = textField_7.getText();
				try{
					CustomerDatabase database = new CustomerDatabase();
					database.delete(customerText);

				} catch(SQLException err){
					err.printStackTrace();
				}
				textField_7.setText("");
			}
		});
		btnDelete.setBounds(87, 120, 117, 29);
		panel_3.add(btnDelete);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 6, 250, 204);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnSeeCustomers = new JButton("See Customer Table");
		btnSeeCustomers.setBounds(54, 60, 135, 89);
		btnSeeCustomers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFrame tableFrame = new JFrame("Customer Table");
				tableFrame.setBounds(100, 100, 878, 614);
				tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tableFrame.getContentPane().setLayout(null);

				JPanel panel = new JPanel();
				panel.setBounds(6, 6, 438, 266);
				tableFrame.getContentPane().add(panel);

				String[] columns = new String[]{"Customer ID", "First Name", "Last Name", "Gender", "Age"};
				Object[][] data = getCustomerData();
				table = new JTable(data, columns);
				panel.add(table);
				tableFrame.setVisible(true);
			}
		});
		panel_2.add(btnSeeCustomers);
	}



	private Object[][] getCustomerData(){
		try{
			CustomerDatabase database = new CustomerDatabase();
			CustomerDatabase[] customers = database.loadAll();
			Object[][] row = new Object[20][5];
			for(int i = 0 ; i < customers.length; i++){
				row[i][0] = customers[i].getCID();
				row[i][1] = customers[i].getFName();
				row[i][2] = customers[i].getLName();
				row[i][3] = customers[i].getGender();
				row[i][4] = customers[i].getAge();

			}
			return row;
	  }
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
