import java.sql.*; //JDBC packages
import java.io.*;
import java.util.*;
import oracle.jdbc.driver.*;

public class CustomerDatabase{
  //instance variables
  private String c_id;
  private String f_name;
  private String l_name;
  private String gender;
  private int age;

  //DB Connection properties
  private String driver = "oracle.jdbc.driver.OracleDriver";
  private String jdbc_url = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";

  //login information
  private String username = "***";
  private String password = "***";

  //Default constructor
  public CustomerDatabase(){}

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
    ResultSet rs = dmd.getTables("Customer", null, null, null);
    while(rs.next()){
      exists = true;
    }
    rs.close();
    return exists;
  }

  private void createTable(Connection connection) throws SQLException{
    //create the SQL for the table
    StringBuffer sb = new StringBuffer();
    sb.append(" CREATE TABLE Customer(");
    sb.append("   c_id varchar(20),");
    sb.append("   first_name varchar2(30),");
    sb.append("   last_name varchar2(30),");
    sb.append("   gender char(1) check(gender='M or gender ='F),");
    sb.append("   age integer,");
    sb.append("   PRIMARY KEY(c_id));");

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

  public CustomerDatabase[] loadAll() throws SQLException{
    //get the connection
    Connection connection = getConnection();

    //create the SELECT SQL
    StringBuffer select = new StringBuffer();
    select.append("SELECT c_id, first_name, last_name, gender, age FROM Customer");

    Statement statement = null;
    ResultSet rs = null;
    ArrayList collection = new ArrayList();

    try{
      statement = connection.createStatement();
      rs = statement.executeQuery(select.toString());
      if(rs != null){
        //when the result set is not null, there are records returned
        while(rs.next()){
          CustomerDatabase customer = new CustomerDatabase();
          customer.setCID(rs.getString("c_id"));
          customer.setFName(rs.getString("first_name"));
          customer.setLName(rs.getString("last_name"));
          customer.setGender(rs.getString("gender"));
          customer.setAge(rs.getInt("age"));
          collection.add(customer);
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
    return (CustomerDatabase[])collection.toArray(new CustomerDatabase[0]);
  }

  //inserts new tuple into database
  public void insertData(String cid, String first, String last, String gender, int age) throws SQLException{
    //get the connection
    Connection connection = getConnection();
    System.out.println("Got Connection");
    //check if table exists
    if(!this.doesTableExist(connection)){
      //create the TABLE
      System.out.println("Customer Table doesn't exist. Creating it.....");
      createTable(connection);
    }

    c_id = cid;
    f_name = first;
    l_name = last;
    this.gender = gender;
    this.age = age;
    //create the INSERT SQL
    String sql = "INSERT INTO Customer VALUES(?,?,?,?,?)";
    PreparedStatement pStmt = connection.prepareStatement(sql);
    //if SQL statement does not return any records
    try{
      //PreparedStatement pStmt = connection.prepareStatement(sql);
      //instantiate parameters with values
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setString(1, this.c_id);
      pStmt.setString(2, this.f_name);
      pStmt.setString(3, this.l_name);
      pStmt.setString(4, this.gender);
      pStmt.setInt(5, this.age);
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
  public void delete(String c_id) throws SQLException{
    Connection connection = getConnection();
    String sql = "DELETE FROM Customer WHERE c_ID = ?";

    PreparedStatement pStmt = connection.prepareStatement(sql);
    try{
      pStmt.clearParameters();
      pStmt.setString(1, c_id);
      pStmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally{
      pStmt.close();
      close(connection);
    }
  }


  public void update(String c_id, String first, String last, String myGender, int myAge) throws SQLException{
    Connection connection = getConnection();
    System.out.println("Got Connection");
    String sql = "UPDATE Customer SET first_name = ?, last_name = ?, gender = ?, age = ? WHERE c_id = ?";

    this.f_name = first;
    this.c_id = c_id;
    this.l_name = last;
    this.gender = myGender;
    this.age = myAge;
    PreparedStatement pStmt = connection.prepareStatement(sql);
    try{
      System.out.println("Inserting data now");
      pStmt.clearParameters();
      pStmt.setString(1, this.f_name);
      pStmt.setString(2, this.l_name);
      pStmt.setString(3, this.gender);
      pStmt.setInt(4, this.age);
      pStmt.setString(5, this.c_id);
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

  public String getCID(){
    return this.c_id;
  }

  public void setCID(String c_id){
    this.c_id = c_id;
  }

  public String getFName(){
    return this.f_name;
  }

  public void setFName(String f_name){
    this.f_name = f_name;
  }

  public String getLName(){
    return this.l_name;
  }

  public void setLName(String l_name){
    this.l_name = l_name;
  }

  public String getGender(){
    return this.gender;
  }

  public void setGender(String gender){
    this.gender = gender;
  }

  public int getAge(){
    return this.age;
  }

  public void setAge(int age){
    this.age = age;
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
