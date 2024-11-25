package bank.bank;

public class Account {

  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, Double balance){

    setId(id);
    setType(type);
    setBalance(id);
    
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

}