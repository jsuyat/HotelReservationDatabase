import java.sql.*; //JDBC packages
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class RoomDatabase{
  //instance variables
  private String room_type;
  private int capacity;

  //DB Connection properties
  private String driver = "oracle.jdbc.driver.OracleDriver";
  private String jdbc_url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";

  //login information
  private String username = "***";
  private String password = "***";

  //Default constructor
  public RoomDatabase(){}

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
    ResultSet rs = dmd.getTables(null, null, "Room", null);
    while(rs.next()){
      exists = true;
    }
    rs.close();
    return exists;
  }

  private void createTable(Connection connection) throws SQLException{
    //create the SQL for the table
    StringBuffer sb = new StringBuffer();
    sb.append(" CREATE TABLE Room(");
    sb.append("   room_type varchar2(20),");
    sb.append("   capacity integer,");
    sb.append("   PRIMARY KEY(room_type));");

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

  public RoomDatabase[] loadAll() throws SQLException{
    //get the connection
    Connection connection = getConnection();

    //create the SELECT SQL
    StringBuffer select = new StringBuffer();
    select.append("SELECT room_type, capacity FROM Room");

    Statement statement = null;
    ResultSet rs = null;
    ArrayList collection = new ArrayList();

    try{
      statement = connection.createStatement();
      rs = statement.executeQuery(select.toString());
      if(rs != null){
        //when the result set is not null, there are records returned
        while(rs.next()){
          RoomDatabase rooms = new RoomDatabase();
          rooms.setRoomType(rs.getString("room_type"));
          rooms.setCapacity(rs.getInt("capacity"));
          collection.add(rooms);
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
    return (RoomDatabase[])collection.toArray(new RoomDatabase[0]);
  }

  //inserts new tuple into database
  public void insertData(String room_type, int capacity) throws SQLException{
    //get the connection
    Connection connection = getConnection();
    System.out.println("Got Connection");
    //check if table exists
    if(!this.doesTableExist(connection)){
      //create the TABLE
      System.out.println("Room Table doesn't exist. Creating it.....");
      createTable(connection);
    }

    this.room_type = room_type;
    this.capacity = capacity;
    //create the INSERT SQL
    String sql = "INSERT INTO Room VALUES(?,?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    //if SQL statement does not return any records
    try{
      //PreparedStatement pStmt = connection.prepareStatement(sql);
      //instantiate parameters with values
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setString(1, this.room_type);
      pStmt.setInt(2, this.capacity);
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
  /*public void delete(int r_num) throws SQLException{
    Connection connection = getConnection();
    String sql = "DELETE FROM Reservation WHERE r_num = ?";

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

  public String getRoomType(){
    return this.room_type;
  }

  public void setRoomType(String room_type){
    this.room_type = room_type;
  }

  public int getCapacity(){
    return this.capacity;
  }

  public void setCapacity(int capacity){
    this.capacity = capacity;
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
  } //end main()

  public void update(String HOTELNAME, int BID, String ROOMTYPE, int QUANTITY) throws SQLException
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
	}
  */
}
