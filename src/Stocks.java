import java.util.*;
public class Stocks {
	Random rand = new Random();
	private String name;
	private String symbol;
	private float value;
	private int varWidth;
	private float var;
	
	public Stocks(String inName, String inSymbol, float inValue, int inVarWidth) {
		name = inName;
		symbol = inSymbol;
		value = inValue;
		varWidth = inVarWidth;
	}
	
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public float getValue() {
		return value;
	}
	public float getVarWidth() {
		return varWidth;
	}
	public float getVar() {
		return var;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public void setVarWidth(int width) {
		varWidth = width;
	}
	public void setVar() {
		float newVarBase = rand.nextInt(varWidth) - (varWidth / 2);
		float newVarDecimal = rand.nextInt(100);
		newVarDecimal = newVarDecimal / 100;
		var = (newVarBase + newVarDecimal) / 100;
	}
}
