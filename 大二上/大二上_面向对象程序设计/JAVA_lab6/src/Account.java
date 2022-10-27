import java.util.*;
public class Account {
	public static void main(String[] args) {
		Account account = new Account(1122, 20000);
        account.setAnnualInterestRate(4.5);
        account.withdraw(2500.0);
        account.deposit(3000.0);
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("Monthly Interest: " + account.getMonthlyInterest());
        System.out.println("Date Created: " + account.getDateCreated());
	}
	private int id;
	private double balance;
	private double annualInterestRate;
	private Date dateCreated;
	
	Account(){
		dateCreated = new Date();
		id = 0;
		balance = 0;
		annualInterestRate = 0;
	}
	Account(int Id, double Balance){
		dateCreated = new Date();
		id = Id;
		balance = Balance;
		annualInterestRate = 0;
	}
	void setId(int newid) {
		id = newid;
	}
	void setBalance(int newbalance) {
		balance = newbalance;
	}
	void setAnnualInterestRate(double newannualInterestRate) {
		annualInterestRate = newannualInterestRate;
	}
	int getId() {
		return id;
	}
	double getBalance() {
		return balance;
	}
	double getAnnualInterestRate() {
		return annualInterestRate;
	}
	double getMonthlyInterestRate() {
		return annualInterestRate / 12;
	}
	double getMonthlyInterest() {
		return balance * (annualInterestRate / 12);
	}
	String getDateCreated() {
        return dateCreated.toString();
    }
	void withdraw(double amount) {
		balance -= amount;
	}
	void deposit(double amount) {
		balance += amount;
	}
}
