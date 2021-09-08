package model.coffee;

public class CoffeeVO {

	private int num;
	private String name;
	private int price;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CoffeeVO [num=" + num + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
	
}
