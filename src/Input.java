import java.util.Scanner;
public class Input {
	private static Scanner sc = new Scanner(System.in);
	public static String getLine() {
		boolean isValid = false;
		String input = null;
		while(!isValid) {
			input = sc.nextLine();
			if(!input.isEmpty()) {
				isValid = !isValid;
			} else {
				System.out.print("You entered nothing. Try again: ");
			}
		}
		return input;
	}

	public static String getString() {
		boolean isValid = false;
		String input = null;
		while(!isValid){
			input = getLine();
			input = input.trim();
			if(!input.isEmpty()) {
				isValid = !isValid;
			} else {
				System.out.print("You entered nothing. Try again: ");
			}
		}
		return input;
	}

	public static String getStringSingle() {
		boolean isValid = false;
		String input = null;
		while(!isValid) {
			input = getString();
			if(!input.contains(" ")) {
				isValid = !isValid;
			} else {
				System.out.print("You entered multiple variables. Try again: ");
			}
		}
		return input;
	}

	public static int getInt() {
		int input = 0;
		String inputS = null;
		boolean isValid = false;
		while(!isValid) {
			try{
				inputS = getStringSingle();
				if(!inputS.contains(".")) {
					input = Integer.parseInt(inputS);
					isValid = !isValid;
				} else {
					System.out.print("You did not enter an integer. Try again: ");
				}
			} catch(Exception e) {
				System.out.print("You did not enter an integer. Try again: ");
			}
		}
		return input;
	}

	public static float getFloat() {
		float input = 0;
		String inputS = null;
		boolean isValid = false;
		while(!isValid) {
			try{
				inputS = getStringSingle();
				input = Float.parseFloat(inputS);
				isValid = !isValid;
			}
				catch(Exception e) {
				System.out.print("You did not enter a float. Try again: ");
			}
		}
		return input;
	}

	public static double getDouble() {
		double input = 0;
		String inputS = null;
		boolean isValid = false;
		while(!isValid) {
			try{
				inputS = getStringSingle();
				input = Float.parseFloat(inputS);
				isValid = !isValid;
			}
				catch(Exception e) {
				System.out.print("You did not enter a float. Try again: ");
			}
		}
		return input;
	}

	public static int getIntRange(int minimum, int max) {
		int input = 0;
		String inputS = null;
		boolean isValid = false;
		boolean inRange = false;
		while(!inRange) {
			while(!isValid) {
				try{
					inputS = getStringSingle();
					if(!inputS.contains(".")) {
						input = Integer.parseInt(inputS);
						isValid = !isValid;
					} else {
						System.out.print("You did not enter an integer. Try again: ");
					}
				} catch(Exception e) {
					System.out.print("You did not enter an integer. Try again: ");
				}
			}
			if(input >= minimum && input <= max) {
				inRange = !inRange;
			} else {
				System.out.print("You did not enter a number within the range. Try again: ");
				isValid = !isValid;
			}
		}
		return input;
	}

	public static float getFloatRange(float minimum, float max) {
		float input = 0;
		String inputS = null;
		boolean isValid = false;
		boolean inRange = false;
		while(!inRange) {
			while(!isValid) {
				try{
					inputS = getStringSingle();
					input = Float.parseFloat(inputS);
					isValid = !isValid;
				}
					catch(Exception e) {
					System.out.print("You did not enter a float. Try again: ");
				}
			}
			if(input >= minimum && input <= max) {
				inRange = !inRange;
			} else {
				System.out.print("You did not enter a number within the range. Try again: ");
				isValid = !isValid;
			}
		}
		return input;
	}

	public static double getDoubleRange(double minimum, double max) {
		double input = 0;
		String inputS = null;
		boolean isValid = false;
		boolean inRange = false;
		while(!inRange) {
			while(!isValid) {
				try{
					inputS = getStringSingle();
					input = Float.parseFloat(inputS);
					isValid = !isValid;
				}
					catch(Exception e) {
					System.out.print("You did not enter a float. Try again: ");
				}
			}
			if(input >= minimum && input <= max) {
				inRange = !inRange;
			} else {
				System.out.print("You did not enter a number within the range. Try again: ");
				isValid = !isValid;
			}
		}
		return input;
	}

	public static int getIntPositive() {
		int input = 0;
		String inputS = null;
		boolean isValid = false;
		boolean inRange = false;
		while(!inRange) {
			while(!isValid) {
				try{
					inputS = getStringSingle();
					if(!inputS.contains(".")) {
						input = Integer.parseInt(inputS);
						isValid = !isValid;
					} else {
						System.out.print("You did not enter a positive number. Try again: ");
					} 
				} catch (Exception e) {
					System.out.print("You did not enter a positive number. Try again: ");
				}
			}
			if(input >= 0) {
				inRange = !inRange;
			} else {
				System.out.print("You did not enter a positive number. Try again: ");
				isValid = !isValid;
			}
		}
		
		return input;
	}
	
	public static int getIntNegative() {
		int input = 0;
		String inputS = null;
		boolean isValid = false;
		boolean inRange = false;
		while(!inRange) {
			while(!isValid) {
				try{
					inputS = getStringSingle();
					if(!inputS.contains(".")) {
						input = Integer.parseInt(inputS);
						isValid = !isValid;
					} else {
						System.out.print("You did not enter a positive number. Try again: ");
					} 
				} catch (Exception e) {
					System.out.print("You did not enter a positive number. Try again: ");
				}
			}
			if(input <= 0) {
				inRange = !inRange;
			} else {
				System.out.print("You did not enter a positive number. Try again: ");
				isValid = !isValid;
			}
		}
		
		return input;
	}
	
	public static int getIntAboveZero() {
		int input = 0;
		String inputS = null;
		boolean isValid = false;
		boolean inRange = false;
		while(!inRange) {
			while(!isValid) {
				try{
					inputS = getStringSingle();
					if(!inputS.contains(".")) {
						input = Integer.parseInt(inputS);
						isValid = !isValid;
					} else {
						System.out.print("You did not enter a number above zero. Try again: ");
					}
				} catch(Exception e) {
					System.out.print("You did not enter a number above zero. Try again: ");
				}
			}
			if(input >= 1) {
				inRange = !inRange;
			} else {
				System.out.print("You did not enter a number above zero. Try again: ");
				isValid = !isValid;
			}
		}
		return input;
	}
}