import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;

public class Makes {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_7;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Makes window = new Makes();
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
	public Makes() {
		initialize();
    frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Makes");
		frame.setBounds(100, 100, 526, 407);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(317, 199, 215, 181);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnSeeMakesTables = new JButton("See Makes Tables");
		btnSeeMakesTables.setBounds(35, 162, 142, 68);
		btnSeeMakesTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame tableFrame = new JFrame("Makes Table");
				tableFrame.setBounds(100, 100, 878, 614);
				tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tableFrame.getContentPane().setLayout(null);

				JPanel panel = new JPanel();
				panel.setBounds(6, 6, 438, 266);
				tableFrame.getContentPane().add(panel);

				String[] columns = new String[]{"Reservation Number", "Customer ID"};
				Object[][] data = getMakesData();
				table = new JTable(data, columns);
				panel.add(table);
				tableFrame.setVisible(true);
			}
		});
		panel_2.add(btnSeeMakesTables);

		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setBounds(213, 6, 299, 181);
		panel.setLayout(null);

		JLabel lblRNum = new JLabel("Reservation Number:");
		lblRNum.setBounds(6, 49, 141, 16);
		panel.add(lblRNum);

		JLabel lblParty = new JLabel("Customer ID:");
		lblParty.setBounds(16, 73, 78, 16);
		panel.add(lblParty);

		textField = new JTextField();
		textField.setBounds(179, 44, 110, 26);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(179, 68, 110, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rNumText = Integer.parseInt(textField.getText());
				String customerText = textField_1.getText();

				try{
					MakesDatabase database = new MakesDatabase();
					database.insertData(rNumText, customerText);
				}
				catch(SQLException err){
					err.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(16, 129, 117, 29);
		panel.add(btnAdd);

		JLabel lblAddHotelData = new JLabel("Update/Add new Makes Data here:");
		lblAddHotelData.setBounds(16, 24, 250, 16);
		panel.add(lblAddHotelData);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(166, 129, 117, 29);
		panel.add(btnUpdate);

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setBounds(213, 199, 299, 181);
		panel_3.setLayout(null);

		JLabel lblDeleteCustomerData = new JLabel("Delete Makes Data Here:");
		lblDeleteCustomerData.setBounds(29, 17, 175, 16);
		panel_3.add(lblDeleteCustomerData);

		JLabel lblRnumDelete = new JLabel("Reservation Number:");
		lblRnumDelete.setBounds(6, 56, 140, 16);
		panel_3.add(lblRnumDelete);

		textField_7 = new JTextField();
		textField_7.setBounds(143, 51, 150, 26);
		panel_3.add(textField_7);
		textField_7.setColumns(10);

		JButton btnDelete = new JButton("Delete:");
		btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int deleteText = Integer.parseInt(textField_7.getText());
			try{
				MakesDatabase database = new MakesDatabase();
				database.delete(deleteText);

			} catch(SQLException err){
				err.printStackTrace();
			}
			textField_7.setText("");
		 }
		});
		btnDelete.setBounds(87, 110, 117, 29);
		panel_3.add(btnDelete);
	}

	private Object[][] getMakesData(){
		try{
			MakesDatabase database = new MakesDatabase();
			MakesDatabase[] reservations = database.loadAll();
			Object[][] row = new Object[20][5];
			for(int i = 0 ; i < reservations.length; i++){
				row[i][0] = (Object) reservations[i].getRNum();
				row[i][1] = (Object) reservations[i].getCID();
			}
			return row;
	  }
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
