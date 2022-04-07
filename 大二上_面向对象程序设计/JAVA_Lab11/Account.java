
public class Account{
	public static void main(String[] argus) {
		Account a1 = new Account("George", 1122, 1000);
		a1.setAnnualInterestRate(1.5 / 100);
		
		a1.deposit(30.0);
		Transaction t1 = new Transaction('D', 30.0, a1.getBalance(),"");
		a1.transactions.add(t1);
		a1.deposit(40.0);
		Transaction t2 = new Transaction('D', 40.0, a1.getBalance(),"");
		a1.transactions.add(t2);
		a1.deposit(50.0);
		Transaction t3 = new Transaction('D', 50.0, a1.getBalance(),"");
		a1.transactions.add(t3);
		a1.withdraw(5.0);
		Transaction t4 = new Transaction('W', 5.0, a1.getBalance(),"");
		a1.transactions.add(t4);
		a1.withdraw(4.0);
		Transaction t5 = new Transaction('W', 4.0, a1.getBalance(),"");
		a1.transactions.add(t5);
		a1.withdraw(2.0);
		Transaction t6 = new Transaction('W', 2.0, a1.getBalance(),"");
		a1.transactions.add(t6);
		
		System.out.println("Name: " + a1.getName());
		System.out.println("Annual interest rate: " + a1.getAnnualInterestRate() * 100 + "%");
		System.out.println("Balance: " + a1.getBalance());
		System.out.println("Date                         Type     Amount     Balance");
		for(int i = 0; i < 3; ++i)
			System.out.println(a1.transactions.get(i).getDate() + "    " + a1.transactions.get(i).getType() + "     " + a1.transactions.get(i).getAmount() + "     " + a1.transactions.get(i).getBalance());
		for(int i = 4; i < 6; ++i)
			System.out.println(a1.transactions.get(i).getDate() + "    " + a1.transactions.get(i).getType() + "     " + a1.transactions.get(i).getAmount() + "      " + a1.transactions.get(i).getBalance());
	}
	private int id;
	private String name;
	private double balance;
	private double annualInterestRate;
	private java.util.Date dateCreated;
	private java.util.ArrayList<Transaction> transactions;
	public Account() {
	}
	public Account(String name, int id, double balance) {
		this.name = name;
		this.id = id;
		this.balance = balance;
		this.transactions = new java.util.ArrayList<Transaction>();
	}
	public int getId() {
		return this.id;
	}
	public double getBalance() {
		return this.balance;
	}
	public java.util.ArrayList<Transaction> getTransaction(){
		return this.transactions;
	}
	public String getName() {
		return this.name;
	}
	public double getAnnualInterestRate() {
		return this.annualInterestRate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public double getMonthlyInterest() {
		return this.annualInterestRate / 12;
	}
	public java.util.Date getDateCreated(){
		return this.dateCreated;
	}
	public void withdraw(double amount) {
		this.balance -=amount;
	}
	public void deposit(double amount) {
		this.balance +=amount;
	}
}
class Transaction {
	private java.util.Date date;
	private char type;
	private double amount;
	private double balance;
	private String description;
	
	public Transaction(char type, double amount, double balance, String description) {
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.description = description;
		this.date = new java.util.Date();
	}
	public java.util.Date getDate(){
		return this.date;
	}
	public char getType() {
		return this.type;
	}
	public double getAmount() {
		return this.amount;
	}
	public double getBalance() {
		return this.balance;
	}
	public String getDescription() {
		return this.description;
	}
}

