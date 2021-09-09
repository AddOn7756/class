package model.bank;

public class ABankVO {

	private int num;
	private String name;
	private int money;
	
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
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "ABankVO [num=" + num + ", name=" + name + ", money=" + money + "]";
	}
	
}
