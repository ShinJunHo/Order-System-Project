package kr.co.SilSoft.obj;

import java.util.HashMap;
import java.util.Set;

public class Stock {
	private HashMap<Product, Integer> stocks;
	public Stock(){
		stocks = new HashMap<Product, Integer>();
	}
	
	public void setAmount(Product product, int amount){
		stocks.put(product, amount);
	}
	
	public int getAmount(Product product){
		int result = 0;
		if(stocks.containsKey(product)){
			result = stocks.get(product);
		}
		return result;
	}
	
	public Set<Product> getKeyset(){
		return stocks.keySet();
	}
}
