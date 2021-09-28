package test;

public class SamsungTV implements TV{

	@Override
	public void chUP() {
		System.out.println("samsung 채널업");
	}

	@Override
	public void chDown() {
		System.out.println("samsung 채널다운");
	}

	@Override
	public void soundUp() {
		System.out.println("samsung 사운드업");
	}

	@Override
	public void soundDown() {
		System.out.println("samsung 사운드다운");
	}

	@Override
	public void powerOn() {
		System.out.println("samsung 파워온");
	}

	@Override
	public void powerOff() {
		System.out.println("samsung 파워오프");
		
	}

}
