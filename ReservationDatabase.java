import java.sql.*; //JDBC packages
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class ReservationDatabase{
  //instance variables
  private int r_num;
  private int party_size;
  private int total;

  //DB Connection properties
  private String driver = "oracle.jdbc.driver.OracleDriver";
  private String jdbc_url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";

  //login information
  private String username = "jsuyat";
  private String password = "gubohu";

  //Default constructor
  public ReservationDatabase(){}

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
    ResultSet rs = dmd.getTables("Reservation", null, null, null);
    while(rs.next()){
      exists = true;
    }
    rs.close();
    return exists;
  }

  private void createTable(Connection connection) throws SQLException{
    //create the SQL for the table
    StringBuffer sb = new StringBuffer();
    sb.append(" CREATE TABLE Reservation(");
    sb.append("   r_num integer,");
    sb.append("   party_size integer,");
    sb.append("   total integer,");
    sb.append("   PRIMARY KEY(r_num));");

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

  public ReservationDatabase[] loadAll() throws SQLException{
    //get the connection
    Connection connection = getConnection();

    //create the SELECT SQL
    StringBuffer select = new StringBuffer();
    select.append("SELECT r_num, party_size, total FROM Reservation");

    Statement statement = null;
    ResultSet rs = null;
    ArrayList collection = new ArrayList();

    try{
      statement = connection.createStatement();
      rs = statement.executeQuery(select.toString());
      if(rs != null){
        //when the result set is not null, there are records returned
        while(rs.next()){
          ReservationDatabase reservation = new ReservationDatabase();
          reservation.setRNum(rs.getInt("r_num"));
          reservation.setPartySize(rs.getInt("party_size"));
          reservation.setTotal(rs.getInt("total"));
          collection.add(reservation);
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
    return (ReservationDatabase[])collection.toArray(new ReservationDatabase[0]);
  }

  //inserts new tuple into database
  public void insertData(int r_num, int party_size, int total) throws SQLException{
    //get the connection
    Connection connection = getConnection();
    System.out.println("Got Connection");
    //check if table exists
    if(!this.doesTableExist(connection)){
      //create the TABLE
      System.out.println("Reservation Table doesn't exist. Creating it.....");
      createTable(connection);
    }

    this.r_num = r_num;
    this.party_size = party_size;
    this.total = total;
    //create the INSERT SQL
    String sql = "INSERT INTO Reservation VALUES(?,?,?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    //if SQL statement does not return any records
    try{
      //PreparedStatement pStmt = connection.prepareStatement(sql);
      //instantiate parameters with values
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setInt(1, this.r_num);
      pStmt.setInt(2, this.party_size);
      pStmt.setInt(3, this.total);
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
  public void delete(int r_num) throws SQLException{
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
  }

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

  public int getRNum(){
    return this.r_num;
  }

  public void setRNum(int r_num){
    this.r_num = r_num;
  }

  public int getPartySize(){
    return this.party_size;
  }

  public void setPartySize(int party_size){
    this.party_size = party_size;
  }

  public int getTotal(){
    return this.total;
  }

  public void setTotal(int total){
    this.total = total;
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
