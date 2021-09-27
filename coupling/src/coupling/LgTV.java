package coupling;

public class LgTV implements TV{

	@Override
	public void chUP() {
		System.out.println("LG 채널업");
	}

	@Override
	public void chDown() {
		System.out.println("LG 채널다운");
	}

	@Override
	public void soundUp() {
		System.out.println("LG 사운드업");
	}

	@Override
	public void soundDown() {
		System.out.println("LG 사운드다운");
	}

	@Override
	public void powerOn() {
		System.out.println("LG 파워온");
	}

	@Override
	public void powerOff() {
		System.out.println("LG 파워오프");
		
	}

	
	
	
}
