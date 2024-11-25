package bank.bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bank.bank.Account;
import bank.bank.Customer;

public class DataSource {

  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("Connection is successfull.");
    } catch (SQLException e) {
      e.getStackTrace();
    }
    return connection;
  }

  public static Account getAccount(int accountid) {
    String sql = "select * from accounts where id = ?";

    Account account = null;

    try (
      Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql)) {
      
        statement.setInt(1, accountid);
      
        try (ResultSet resultSet = statement.executeQuery()) {
        account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;
  }

  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username = ?";

    Customer customer = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, username);
      try (ResultSet resultset = statement.executeQuery()) {
        customer = new Customer(
            resultset.getInt("id"),
            resultset.getString("name"),
            resultset.getString("username"),
            resultset.getString("password"),
            resultset.getInt("account_id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  public static void main(String[] args) {
    Customer customer = getCustomer("zlewsy9x@webeden.co.uk");
    System.out.println("Username: " + customer.getUsername());
    System.out.println("Password: " + customer.getPassword());
    System.out.println("Account Id: " + customer.getAccount_id());

    Account account = getAccount(67231);
    System.out.println("Account Type: " + account.getType());
    System.out.println("Account Balance: " + account.getBalance());
  }
}