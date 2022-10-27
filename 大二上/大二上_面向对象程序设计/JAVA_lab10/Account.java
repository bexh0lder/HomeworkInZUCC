public class Account {
    public static void main(String[] argus) {
        System.out.println(new Account().toString());
        System.out.println(new Savings().toString());
        System.out.println(new Checkings().toString());
    }
    public int id;
    public double balance;
    public double annualInterestRate;
    public java.util.Date dateCreated;

    Account(){
    }
    Account(int newId, double newBalance){
        this.id = newId;
        this.balance = newBalance;
    }
    public int getId() {
        return this.id;
    }
    public double getBalance() {
        return this.balance;
    }
    public double getAnnualInterestRate() {
        return this.annualInterestRate;
    }
    public void setId(int newId) {
        this.id = newId;
    }
    public void setBalance(int newBalance) {
        this.balance = newBalance;
    }
    public void setannualInterestRate(int newAnnualInterestRate) {
        this.annualInterestRate = newAnnualInterestRate;
    }
    public double getMonthlyInterest() {
        return this.annualInterestRate / 12.0;
    }
    public java.util.Date getDateCreated(){
        return this.dateCreated;
    }
    public void withdraw(double amount) {
        this.balance -= amount;
    }
    public void deposit(double amount) {
        this.balance += amount;
    }
    public String toString() {
        return "Account@2a139a55";
    }
}
class Checkings extends Account{
    public int overdraftLimit;
    public String toString() {
        return "Checkings";
    }
}
class Savings extends Account{
    public int overdraftLimit;
    public String toString() {
        return "Savings";
    }
}
