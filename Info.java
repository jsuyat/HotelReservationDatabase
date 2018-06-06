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

public class Info {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info window = new Info();
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
	public Info() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 506);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(317, 199, 215, 181);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnSeeMakesTables = new JButton("See Info Tables");
		btnSeeMakesTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame tableFrame = new JFrame("Info Table");
				tableFrame.setBounds(100, 100, 878, 614);
				tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tableFrame.getContentPane().setLayout(null);

				JPanel panel = new JPanel();
				panel.setBounds(6, 6, 438, 266);
				tableFrame.getContentPane().add(panel);

				String[] columns = new String[]{"Date", "Hotel Name", "Branch ID", "Room Type", "Price", "Number Available"};
				Object[][] data = getInfoData();
				table = new JTable(data, columns);
				panel.add(table);
				tableFrame.setVisible(true);
			}
		});
		btnSeeMakesTables.setBounds(35, 162, 142, 68);
		panel_2.add(btnSeeMakesTables);

		JPanel panel = new JPanel();
		panel_2.add(panel);
		panel.setBounds(213, 6, 349, 279);
		panel.setLayout(null);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(30, 244, 117, 29);
		panel.add(btnAdd);

		JLabel lblAddHotelData = new JLabel("Update/Add new Makes Data here:");
		lblAddHotelData.setBounds(16, 24, 250, 16);
		panel.add(lblAddHotelData);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(166, 244, 117, 29);
		panel.add(btnUpdate);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(16, 54, 61, 16);
		panel.add(lblDate);

		JLabel lblHotelName = new JLabel("Hotel Name:");
		lblHotelName.setBounds(16, 82, 78, 16);
		panel.add(lblHotelName);

		JLabel lblBranchId = new JLabel("Branch ID:");
		lblBranchId.setBounds(16, 110, 78, 16);
		panel.add(lblBranchId);

		JLabel lblRoomType = new JLabel("Room Type:");
		lblRoomType.setBounds(16, 138, 78, 16);
		panel.add(lblRoomType);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(16, 166, 61, 16);
		panel.add(lblPrice);

		JLabel lblAvailability = new JLabel("Availability:");
		lblAvailability.setBounds(16, 201, 78, 16);
		panel.add(lblAvailability);

		textField_1 = new JTextField();
		textField_1.setBounds(111, 52, 214, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(121, 77, 204, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(121, 105, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(121, 133, 130, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(121, 161, 130, 26);
		panel.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(121, 196, 130, 26);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setBounds(213, 297, 349, 181);
		panel_3.setLayout(null);

		JLabel lblDeleteCustomerData = new JLabel("Delete Info Data Here:");
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
			}
		});
		btnDelete.setBounds(87, 110, 117, 29);
		panel_3.add(btnDelete);

		textField = new JTextField();
		textField.setBounds(142, 51, 130, 26);
		panel_3.add(textField);
		textField.setColumns(10);
	}

	private Object[][] getInfoData(){
		try{
			InfoDatabase database = new InfoDatabase();
			InfoDatabase[] info = database.loadAll();
			Object[][] row = new Object[20][6];
			for(int i = 0 ; i < info.length; i++){
				row[i][0] = (Object) info[i].getDate();
				row[i][1] = (Object) info[i].getHotelName();
				row[i][2] = (Object) info[i].getBranchID();
				row[i][3] = (Object) info[i].getRoomType();
				row[i][4] = (Object) info[i].getPrice();
				row[i][5] = (Object) info[i].getNumAvail();
			}
			return row;
	  }
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
