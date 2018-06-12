import java.sql.*; //JDBC packages
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class AvailabilityDatabase{
  //instance variables
  private String hotel_name;
  private String branch_id;
  private String room_type;
  private int quantity;

  //DB Connection properties
  private String driver = "oracle.jdbc.driver.OracleDriver";
  private String jdbc_url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";

  //login information
  private String username = "***";
  private String password = "***";

  //Default constructor
  public AvailabilityDatabase(){}

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
      return connection;
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

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rs = dmd.getTables("Has", null, null, null);
    while(rs.next()){
      exists = true;
    }
    rs.close();
    return exists;
  }

  private void createTable(Connection connection) throws SQLException{
    //create the SQL for the table
    StringBuffer sb = new StringBuffer();
    sb.append(" CREATE TABLE Has(");
    sb.append("    hotel_name varchar2(50),");
  	sb.append("    branch_id varchar2(10),");
  	sb.append("    room_type varchar2(20),");
  	sb.append("    quantity integer,");
  	sb.append("    PRIMARY KEY(hotel_name, branch_id, room_type),");
  	sb.append("    FOREIGN KEY(hotel_name, branch_id) REFERENCES Hotel(hotel_name, branch_id),");
  	sb.append("    FOREIGN KEY(room_type) REFERENCES Room(room_type));");


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

  public AvailabilityDatabase[] loadAll() throws SQLException{
    //get the connection
    Connection connection = getConnection();

    //create the SELECT SQL
    StringBuffer select = new StringBuffer();
    select.append("SELECT hotel_name, branch_id, room_type, quantity FROM Has");

    Statement statement = null;
    ResultSet rs = null;
    ArrayList collection = new ArrayList();

    try{
      statement = connection.createStatement();
      rs = statement.executeQuery(select.toString());
      if(rs != null){
        //when the result set is not null, there are records returned
        while(rs.next()){
          AvailabilityDatabase availability = new AvailabilityDatabase();
          availability.setHotelName(rs.getString("hotel_name"));
          availability.setBranchID(rs.getString("branch_id"));
          availability.setRoomType(rs.getString("room_type"));
          availability.setQuantity(rs.getInt("quantity"));
          collection.add(availability);
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
    return (AvailabilityDatabase[])collection.toArray(new AvailabilityDatabase[0]);
  }

  //inserts new tuple into database
  public void insertData(String hotel_name, String branch_id, String room_type, int quantity) throws SQLException{
    //get the connection
    Connection connection = getConnection();
    System.out.println("Got Connection");
    //check if table exists
    if(!this.doesTableExist(connection)){
      //create the TABLE
      System.out.println("Has Table doesn't exist. Creating it.....");
      createTable(connection);
    }

    this.hotel_name = hotel_name;
    this.branch_id = branch_id;
    this.room_type = room_type;
    this.quantity = quantity;
    //create the INSERT SQL
    String sql = "INSERT INTO Has VALUES(?,?,?, ?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    //if SQL statement does not return any records
    try{
      //PreparedStatement pStmt = connection.prepareStatement(sql);
      //instantiate parameters with values
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setString(1, this.hotel_name);
      pStmt.setString(2, this.branch_id);
      pStmt.setString(3, this.room_type);
      pStmt.setInt(4, this.quantity);
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
  /*public void delete(String hotel_name, String branch_id, String room_type) throws SQLException{
    Connection connection = getConnection();
    String sql = "DELETE FROM Reservation WHERE hotel_name = ?";

    PreparedStatement pStmt = connection.prepareStatement(sql);
    try{
      pStmt.clearParameters();
      pStmt.setInt(1, r_num);
      pStmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally{
      pStmt.close();
      close(connection);
    }
  }*/

  /**
   * @param connection
   * #throws SQLException
   ***/
  private void close(Connection connection) throws SQLException{
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

  public String getRoomType(){
    return this.room_type;
  }

  public void setRoomType(String room_type){
    this.room_type = room_type;
  }
  public int getQuantity(){
    return this.quantity;
  }

  public void setQuantity(int quantity){
    this.quantity = quantity;
  }


  /************
  Main Method
  *************/
  /*public static void main(String [] args){
    System.out.println("Insert First Customer");
    CustomerDatabase cDB = new CustomerDatabase();

    try{
      cDB.insertData("C6", "Calvin", "Alvarez", "M", 24);
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

    //List the records
    CustomerDatabase[] customers;
    try{
      customers = cDB.loadAll();
      System.out.println("\nC_Id \t  First  \t  Last  \t  Gender  \t  Age");
      System.out.println("----- \t  ----- \t  ----  \t ------  \t ---");
      for(int i = 0; i < customers.length; i++){
        CustomerDatabase currentTuple = customers[i];
        System.out.println(currentTuple.getCID() + "\t" + currentTuple.getFName());
      }
    }
    catch (SQLException e){
      e.printStackTrace();
    }
  } //end main()*/
}
