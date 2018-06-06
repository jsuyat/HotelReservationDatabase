import java.sql.*; //JDBC packages
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class MakesDatabase{
  //instance variables
  private int r_num;
  private String c_id;

  //DB Connection properties
  private String driver = "oracle.jdbc.driver.OracleDriver";
  private String jdbc_url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";

  //login information
  private String username = "jsuyat";
  private String password = "gubohu";

  //Default constructor
  public MakesDatabase(){}

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
    ResultSet rs = dmd.getTables("Makes", null, null, null);
    while(rs.next()){
      exists = true;
    }
    rs.close();
    return exists;
  }

  private void createTable(Connection connection) throws SQLException{
    //create the SQL for the table
    StringBuffer sb = new StringBuffer();
    sb.append(" CREATE TABLE Makes(");
    sb.append("   r_num integer,");
    sb.append("   c_id varchar2(10),");
    sb.append("   PRIMARY KEY(r_num)");
    sb.append("   FOREIGN KEY(r_num) REFERENCES Reservation(r_num),");
    sb.append("   FOREIGN KEY (c_id) REFERENCES Customer(c_id));");

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

  public MakesDatabase[] loadAll() throws SQLException{
    //get the connection
    Connection connection = getConnection();

    //create the SELECT SQL
    StringBuffer select = new StringBuffer();
    select.append("SELECT r_num, c_id FROM Makes");

    Statement statement = null;
    ResultSet rs = null;
    ArrayList collection = new ArrayList();

    try{
      statement = connection.createStatement();
      rs = statement.executeQuery(select.toString());
      if(rs != null){
        //when the result set is not null, there are records returned
        while(rs.next()){
          MakesDatabase makes = new MakesDatabase();
          makes.setRNum(rs.getInt("r_num"));
          makes.setCID(rs.getString("c_id"));
          collection.add(makes);
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
    return (MakesDatabase[])collection.toArray(new MakesDatabase[0]);
  }

  //inserts new tuple into database
  public void insertData(int r_num, String c_id) throws SQLException{
    //get the connection
    Connection connection = getConnection();
    System.out.println("Got Connection");
    //check if table exists
    if(!this.doesTableExist(connection)){
      //create the TABLE
      System.out.println("Makes Table doesn't exist. Creating it.....");
      createTable(connection);
    }

    this.r_num = r_num;
    this.c_id = c_id;
    //create the INSERT SQL
    String sql = "INSERT INTO Makes VALUES(?,?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    //if SQL statement does not return any records
    try{
      //PreparedStatement pStmt = connection.prepareStatement(sql);
      //instantiate parameters with values
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setInt(1, this.r_num);
      pStmt.setString(2, this.c_id);
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
    String sql = "DELETE FROM Makes WHERE r_num = ?";

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

  public String getCID(){
    return this.c_id;
  }

  public void setCID(String c_id){
    this.c_id = c_id;
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
