import java.sql.*; //JDBC packages
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class HotelDatabase{
  //instance variables
  private String hotel_name;
  private String branch_id;
  private String city;
  private String state;
  private String zip;
  private String address;
  private String phone;

  //DB Connection properties
  private String driver = "oracle.jdbc.driver.OracleDriver";
  private String jdbc_url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";

  //login information
  private String username = "jsuyat";
  private String password = "gubohu";

  //Default constructor
  public HotelDatabase(){}

  /*Method to get the connection for the database
   * return java.squl.Connection object
   */
  private Connection getConnection(){
    try{
      Class.forName(driver);
    }
    catch(ClassNotFoundException e){
      e.printStackTrace();
    }

    Connection connection = null;
    try{
      connection = DriverManager.getConnection(jdbc_url, username, password);
    }
    catch(SQLException e){
      e.printStackTrace();
    }
    return connection;
  }

  /*
   * Method to check if a table exists
   * @param connection java.sql.Connection object
   * @return true if the table exists, false otherwise
   * @throws SQLException
   * */
  private boolean doesTableExist(Connection connection) throws SQLException{
    boolean exists = false;
    System.out.println("In doesTableExist?");
    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables("Hotel", null, null, null);
    while(rs.next()){
      exists = true;
    }
    rs.close();
    return exists;
  }

  private void createTable(Connection connection) throws SQLException{
    //create the SQL for the table
    StringBuffer sb = new StringBuffer();

    sb.append("CREATE TABLE Hotel(");
    sb.append("	hotel_name varchar2(50),");
    sb.append("	branch_id varchar2(10),");
    sb.append("	city varchar2(20),");
    sb.append("	state char(2),");
    sb.append("	zip char(5),");
    sb.append("	address varchar2(100),");
    sb.append("	phone char(12)");
    sb.append("	PRIMARY KEY(hotel_name, branch_id));");

    //create the TABLE
    Statement statement = null;
    try{
      statement = connection.createStatement();
      statement.executeUpdate(sb.toString());
    }
    catch(SQLException e){
      throw e;
    }
    finally{
      statement.close();
    }
  }

  public HotelDatabase[] loadAll() throws SQLException{
    //get the connection
    System.out.println("In Hotel.loadAll()");
    Connection connection = getConnection();
    System.out.println("Got Connection");
    //create the SELECT SQL
    StringBuffer select = new StringBuffer();
    select.append("SELECT hotel_name, branch_id, city, state, zip, address, phone FROM Hotel");

    Statement statement = null;
    ResultSet rs = null;
    ArrayList collection = new ArrayList();
    try{
      statement = connection.createStatement();
      rs = statement.executeQuery(select.toString());
      if(rs != null){
        //when the result set is not null, there are records returned
        while(rs.next()){
          HotelDatabase hotels = new HotelDatabase();
          hotels.setHotelName(rs.getString("hotel_name"));
          hotels.setBranchID(rs.getString("branch_id"));
          hotels.setCity(rs.getString("city"));
          hotels.setState(rs.getString("state"));
          hotels.setZIP(rs.getString("zip"));
          hotels.setAddress(rs.getString("address"));
          hotels.setPhone(rs.getString("phone"));
          collection.add(hotels);
        }
      }
    } catch (SQLException e)
    {
      throw e;
    }
    finally{
      statement.close();
      close(connection);
    }

    //return the array
    return (HotelDatabase[])collection.toArray(new HotelDatabase[0]);
  }

  //inserts new tuple into database
  public void insertData() throws SQLException{
    //get the connection
    Connection connection = getConnection();
    System.out.println("Got Connection");
    //check if table exists
    if(!this.doesTableExist(connection)){
      //create the TABLE
      System.out.println("Hotel Table doesn't exist. Creating it.....");
      createTable(connection);
    }

    //create the INSERT SQL
    String sql = "INSERT INTO Hotel VALUES(?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    //if SQL statement does not return any records
    try{
      //PreparedStatement pStmt = connection.prepareStatement(sql);
      //instantiate parameters with values
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setString(1, this.hotel_name);
      pStmt.setString(2, this.branch_id);
      pStmt.setString(3, this.city);
      pStmt.setString(4, this.state);
      pStmt.setString(5, this.zip);
      pStmt.setString(6, this.address);
      pStmt.setString(7, this.phone);
      int numRows = pStmt.executeUpdate();
    }
    catch(SQLException sqle){
      throw sqle;
    }
    finally{
       pStmt.close();
       close(connection);
    }
  }

  public void delete(String hotel) throws SQLException{
    Connection connection = getConnection();
    String sql = "DELETE FROM Hotels WHERE hotel_name = ?";

    PreparedStatement pStmt = connection.prepareStatement(sql);
    try{
      pStmt.clearParameters();
      pStmt.setString(1, hotel);
      pStmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally{
      pStmt.close();
      close(connection);
    }
  }

  public void update(String city, String state, String zip, String address, String hotel_name, String branch_id) throws SQLException{
    Connection connection = getConnection();
    System.out.println("Got Connection");
    String sql = "UPDATE Customer SET city = ?, state = ?, zip = ?, address = ? WHERE hotel_name = ? AND branch_id = ?";

    this.city = city;
    this.state = state;
    this.zip = zip;
    this.address = address;
    this.phone = phone;
    this.hotel_name;
    this.branch_id = branch_id;
    PreparedStatement pStmt = connection.prepareStatement(sql);
    try{
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setString(1, this.city);
      pStmt.setString(2, this.state);
      pStmt.setString(3, this.zip);
      pStmt.setString(4, this.address);
      pStmt.setString(5, this.phone);
      pStmt.setString(6, this.hotel_name);
      pStmt.setString(7, this.branch_id);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally{
      pStmt.close();
      close(connection);
    }
  }

  /**
   * @param connection
   * #throws SQLException
   ***/
  public void close(Connection connection) throws SQLException{
    try{
      connection.close();
    }
    catch(SQLException e){
      throw e;
    }
  }

  //getter and setter methods
  public String getHotelName(){
    return this.hotel_name;
  }

  public void setHotelName(String hotel_name){
    this.hotel_name = hotel_name;
  }

  public String getBranchID(){
    return this.branch_id;
  }

  public void setBranchID(String branch_id){
    this.branch_id = branch_id;
  }

  public String getCity(){
    return this.city;
  }

  public void setCity(String city){
    this.city = city;
  }

  public String getState(){
    return this.state;
  }

  public void setState(String state){
    this.state = state;
  }

  public String getZIP(){
    return this.zip;
  }

  public void setZIP(String zip){
    this.zip = zip;
  }

  public String getAddress(){
    return this.address;
  }

  public void setAddress(String address){
    this.address = address;
  }

  public String getPhone(){
    return this.phone;
  }

  public void setPhone(String phone){
    this.phone = phone;
  }
  /************
  Main Method
  *************/
  public static void main(String [] args){
    System.out.println("Insert First Customer");
    HotelDatabase cDB = new HotelDatabase();

    try{
      cDB.insertData();
    } catch(SQLException sqlException)
    {
      while(sqlException != null){
        sqlException.printStackTrace();
        sqlException = sqlException.getNextException();
      }
    } catch(Exception e)
    {
      e.printStackTrace();
    }

  } //end main()

  /*public void update(String HOTELNAME, int BID, String ROOMTYPE, int QUANTITY) throws SQLException
	{
		//get the connection
		Connection connection = getConnection();
		System.out.println("Got Connection");
		//check if table exists
		if(!this.doesTableExist(connection))
		{
			//create the TABLE
			System.out.println("HOTELROOM table doesn't exist");
		}
		else
        {
            this.HOTELNAME = HOTELNAME;
            this.BID = BID;
            this.ROOMTYPE = ROOMTYPE;
            this.QUANTITY = QUANTITY;
            //create the INSERT SQL
            String sql = "UPDATE HOTELROOM SET quantity = ? WHERE HOTELNAME = ? AND BID = ? AND ROOMTYPE = ?";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            //if SQL statement does not return any records
            try {
                //PreparedStatement pStmt = connection.prepareStatement(sql);
                //instantiate parameters with values
                System.out.println("Updating data now...");
                pStmt.clearParameters();
                pStmt.setString(2, this.HOTELNAME);
                pStmt.setInt(3, this.BID);
                pStmt.setString(4, this.ROOMTYPE);
                pStmt.setInt(1, this.QUANTITY);
                int numRows = pStmt.executeUpdate();
            } catch (SQLException sqle) {
                throw sqle;
            } finally {
                pStmt.close();
                close(connection);
            }
        }
	} */
}
