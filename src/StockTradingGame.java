import java.util.*;
import java.io.*;
import java.math.*;
public class StockTradingGame {
	final static int MAX_INT = 2147483647;
	final static float MAX_FLOAT = Float.MAX_VALUE;
	public static void main(String[] args) throws FileNotFoundException {
		int day = 1;
		Random rand = new Random();
		File ipo = new File("IPO.txt");
		Scanner reader1 = new Scanner(ipo);
		Scanner reader2 = new Scanner(ipo);
		float balance = 100000;
		boolean playGame = true;
		Wallet wallet = new Wallet();
		wallet.setBalance(balance);
		ArrayList<Stocks> StocksList = new ArrayList<Stocks>();
		ArrayList<Portfolio> stockPortfolio = new ArrayList<Portfolio>();
		int lineCount = 0;
		while(reader1.hasNext()) {
			reader1.nextLine();
			lineCount++;
		}
		Portfolio portfolioTemp[] = new Portfolio[lineCount];
		Stocks stocksTemp[] = new Stocks[lineCount];
		for(int i = 0; i < 10; i++) {
			String line = reader2.nextLine();
			String[] info = line.split("/");
			String name = info[0];
			String symbol = info[1];
			float value = Integer.parseInt(info[2]);
			int var = Integer.parseInt(info[3]);
			stocksTemp[i] = new Stocks(name, symbol, value, var);
			StocksList.add(stocksTemp[i]);
			stockPortfolio.add(new Portfolio(name, symbol, 0));
		}
		nextDay(StocksList);
		while(playGame) {
			System.out.printf("Day: %d - Balance: $%.2f\n1. Next Day\n2. View Accounts\n3. Buy/Sell\n4. Exit\n5. Admin Settings\nEnter an option: ", day, wallet.getBalance());
			int option = Input.getIntRange(1,5);
			switch(option) {
				case 1:
					nextDay(StocksList);
					day++;
					break;
				case 2:
					viewAccounts(StocksList, wallet, stockPortfolio);
					break;
				case 3:
					buyAndSelll(StocksList, wallet, stockPortfolio);
					break;
				case 4:
					playGame = !playGame;
					break;
				case 5:
					adminSettings(StocksList, wallet, stockPortfolio);
					break;
				default:
					break;
			}
		}
	}

	public static void buyAndSelll(ArrayList<Stocks> StocksList, Wallet wallet, ArrayList<Portfolio> stockPortfolio) {
		Stocks temp = new Stocks(null, null, 0, 0);
		System.out.print("\n1. Buy\n2. Sell\n3. Back\nEnter an option: ");
		int option = Input.getIntRange(1, 3);
		boolean buySell = true;
		while(buySell) {
			switch(option) {
			case 1:
				System.out.println();
				for(Stocks a : StocksList) {
					System.out.printf("%-20s %-5s: $%8.2f    %5.2f%s \n",a.getName(), a.getSymbol(), a.getValue(), a.getVar() * 100,"%");
				}
				boolean validBuy = false;
				while(!validBuy) {
					System.out.print("Please enter the symbol of the stock you would like to buy: ");
					String buy = Input.getStringSingle();
					for(Stocks a : StocksList) {
						if(a.getSymbol().equalsIgnoreCase(buy)) {
							validBuy = !validBuy;
							temp = a;
						}
					}
				}
				float stockValue = temp.getValue();
				float totalCost = 0;
				int maxPurchase = (int) ( wallet.getBalance() / stockValue);
				int buyAmount = 0;
				boolean canAfford = false;
				while(!canAfford) {
					System.out.print("You can afford " + maxPurchase + " stocks of " + temp.getName() + "\nHow many stocks would you like to buy: ");
					buyAmount = Input.getIntPositive();
					totalCost = stockValue * buyAmount;
					if(wallet.getBalance() - totalCost >= 0) {
						canAfford = !canAfford;
						buySell = !buySell;
						wallet.setBalance(-totalCost);
						for(Portfolio b : stockPortfolio) {
							if(temp.getSymbol().equalsIgnoreCase(b.getSymbol())) {
								b.setAmount(buyAmount);
							}
						}
					} else {
						System.out.println("You cannot afford that many!\n");
					}
				}
				System.out.printf("\nYou purchased %d stocks of %s for $%.2f.\n\n", buyAmount, temp.getName(), totalCost);
				break;
			case 2:
				int sellAmount = 0;
				System.out.println();
				for(Portfolio a : stockPortfolio) {
					for(Stocks b : StocksList) {
						if(a.getSymbol().equalsIgnoreCase(b.getSymbol())) {
							temp = b;
						}
					}
					System.out.printf("%-20s %-5s   %-5d owned    value: $%10.2f\n",a.getName(), a.getSymbol(), a.getAmount(),(Float) (a.getAmount() * temp.getValue()));
				}
				boolean validSell = false;
				while(!validSell) {
					System.out.print("\nPlease enter the symbol of the stock you would like to sell: ");
					String sell = Input.getStringSingle();
					for(Stocks a : StocksList) {
						if(a.getSymbol().equalsIgnoreCase(sell)) {
							validSell = !validSell;
							temp = a;
						}
					}
				}
				validSell = !validSell;
				System.out.printf("Enter the number of %s stocks would you like to sell: ", temp.getName());
				boolean stocksOwned = false;
				while(!validSell) {
					while(!stocksOwned) {
						sellAmount = Input.getIntPositive();
						for(Portfolio c : stockPortfolio) {
							if(temp.getSymbol().equalsIgnoreCase(c.getSymbol())) {
								if(c.getAmount() >= sellAmount) {
									stocksOwned = !stocksOwned;
									}
								}
							}
						if(!stocksOwned) {
							System.out.printf("\nYou don't own that many %s stocks, try again: ", temp.getName());
						}
					}
					
					for(Portfolio b : stockPortfolio) {
						if(temp.getSymbol().equalsIgnoreCase(b.getSymbol())) {
							b.setAmount(-sellAmount);
							validSell = !validSell;
							wallet.setBalance(sellAmount * temp.getValue());
						}
					}
				}
				System.out.printf("You sold %d %s stocks for $%.2f.\n\n", sellAmount, temp.getName(), sellAmount * temp.getValue());
				buySell = !buySell;
				break;
			case 3:
				buySell = !buySell;
				break;
			}
		}
	}

	public static void viewAccounts(ArrayList<Stocks> StocksList, Wallet wallet, ArrayList<Portfolio> stockPortfolio) {
		boolean inAccounts = true;
		float balance = wallet.getBalance();
		while(inAccounts) {
			System.out.printf("\nBalance: $%.2f \n1. View Stocks\n2.Check Portfolio Value\n3.Back\nEnter an option: ", balance);
			int option = Input.getIntRange(1, 3);
			switch(option) {
			case 1:
				System.out.println();
				Stocks temp = new Stocks(null,null,0,0);
				for(Portfolio a : stockPortfolio) {
					for(Stocks b : StocksList) {
						if(a.getSymbol().equalsIgnoreCase(b.getSymbol())) {
							temp = b;
						}
					}
					System.out.printf("%-20s %-5s   %-5d owned    value: $%10.2f\n",a.getName(), a.getSymbol(), a.getAmount(),(Float) (a.getAmount() * temp.getValue()));
				}
				break;
			case 2:
				float portfolioValue = 0;
				for(Portfolio a : stockPortfolio) {
					for(Stocks b : StocksList) {
						if(a.getSymbol().equalsIgnoreCase(b.getSymbol())) {
							portfolioValue += a.getAmount() * b.getValue();
						}
					}
				}
				System.out.printf("\nYour portfolio is valued at $%.2f.\n", portfolioValue);
				break;
			case 3:
				inAccounts = !inAccounts;
				break;
			default:
				break;
			}
		}
	}

	public static void nextDay(ArrayList<Stocks> StocksList) {
		for(Stocks a : StocksList) {
			a.setVar();
			float newValue = (1 + a.getVar()) * a.getValue();
			a.setValue(newValue);
		}
	}
	
	private static void adminSettings(ArrayList<Stocks> StocksList, Wallet wallet, ArrayList<Portfolio> stockPortfolio){
		boolean access = false;
		boolean granted = false;
		while(!access) {
			System.out.print("Please enter the password, or type \'back\' to return: ");
			String password = Input.getString();
			if(password.equals("Drew")) {
				granted = !granted;
				access = !access;
			} else if(password.equalsIgnoreCase("back")) {
				access = !access;
			} else {
				System.out.println("Password incorrect!");
			}
		}
		
		if(granted) {
			boolean editting = true;
			while(editting) {
				System.out.print("1. Empty\n2. Edit Balance\n3. Edit Portfolio\n4. Exit\nEnter your choice: ");
				int choice = Input.getIntRange(1, 4);
				
				switch(choice) {
					case 1:
						break;
					case 2:
						float currentBalance = wallet.getBalance();
						System.out.printf("\nYour current balance is: $%.2f.\nEnter your desired balance: ", currentBalance);
						float newBalance = Input.getFloatRange(0, MAX_INT);
						wallet.setBalance(-currentBalance);
						wallet.setBalance(newBalance);
						break;
					case 3:
						System.out.println();
						Stocks temp = new Stocks(null,null,0,0);
						for(Portfolio a : stockPortfolio) {
							for(Stocks b : StocksList) {
								if(a.getSymbol().equalsIgnoreCase(b.getSymbol())) {
									temp = b;
								}
							}
							System.out.printf("%-20s %-5s   %-5d owned    value: $%10.2f\n",a.getName(), a.getSymbol(), a.getAmount(),(Float) (a.getAmount() * temp.getValue()));
						}
						System.out.print("Enter the symbol of the stock quantity you would like to modify: ");
						boolean foundSymbol = false;
						while(!foundSymbol) {
							String editStock = Input.getString();
							for(Stocks c : StocksList) {
								if(c.getSymbol().equalsIgnoreCase(editStock)) {
									temp = c;
									foundSymbol = !foundSymbol;
								}
							}
							if(!foundSymbol) {
								System.out.print("Stock not found, please try again: ");
							}
						}
						for(Portfolio d : stockPortfolio) {
							if(temp.getSymbol().equalsIgnoreCase(d.getSymbol())) {
								System.out.printf("\n%-20s %-5s   %-5d owned    value: $%10.2f\n",d.getName(), d.getSymbol(), d.getAmount(),(Float) (d.getAmount() * temp.getValue()));
							}
						}
						System.out.printf("Enter your desired number of %s stocks: ", temp.getName());
						int ownedStocks = Input.getIntRange(0, MAX_INT);
						for(Portfolio e : stockPortfolio) {
							if(temp.getSymbol().equalsIgnoreCase(e.getSymbol())) {
								e.setAmount(ownedStocks);
							}
						}
						System.out.println();
						break;
					case 4:
						editting = !editting;
						break;
				}
 			}
			
		}
		
		System.out.println();
	}
	
}
