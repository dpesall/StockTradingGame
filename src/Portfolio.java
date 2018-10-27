
public class Portfolio {
	private int amount;
	private String name;
	private String symbol;
	
	public Portfolio(String inName, String inSymbol, int inAmount) {
		amount = inAmount;
		name = inName;
		symbol = inSymbol;
	}
	public void setAmount(int addAmount) {
		amount = amount + addAmount;
	}
	public int getAmount() {
		return amount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return symbol;
	}
}
