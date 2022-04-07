package lab5;

public class Stock {
	public static void main(String args[]) {
		Stock s1 = new Stock();
		System.out.println("Previous Closing Price: " + s1.getPreviousClosingPrice());
		System.out.println("Current Price: " + s1.getCurrentPrice());
		System.out.println("Price Change: " + s1.getChangePercent() + "%");
	}
	String symbol;
	String name;
	double previousClosingPrice;
	double currentPrice;
	
	Stock(){
		symbol = "ORCL";
		name = "Oracle Corporation";
		currentPrice = 34.35;
		previousClosingPrice = 34.5;
	}
	Stock(String newSymbol, String newName){
		symbol = newSymbol;
		name = newName;
	}
	double getChangePercent(){
		return (currentPrice - previousClosingPrice) / previousClosingPrice * 100;
	}
	double getPreviousClosingPrice(){
		return previousClosingPrice;
	}
	double getCurrentPrice(){
		return currentPrice;
	}
	void setCurrentPrice(double newCurrentPrice) {
		currentPrice = newCurrentPrice;
	}
	void setPreviousClosingPrice(double newPreviousClosingPrice) {
		previousClosingPrice = newPreviousClosingPrice;
	}
}
