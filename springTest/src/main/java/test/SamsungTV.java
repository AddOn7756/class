package test;

public class SamsungTV implements TV{

	Phone p;
	
	public SamsungTV(){
		System.out.println("삼성TV 기본생성자");
	}
	
	public SamsungTV(Phone p){
		this.p = p;
		System.out.println("삼성TV 생성자");
	}
	
	
	@Override
	public void chUP() {
		p.chUP();
		//System.out.println("samsung 채널업");
	}

	@Override
	public void chDown() {
		p.chDown();
		//System.out.println("samsung 채널다운");
	}

	@Override
	public void soundUp() {
		p.soundUp();
		//System.out.println("samsung 사운드업");
	}

	@Override
	public void soundDown() {
		p.soundDown();
		//System.out.println("samsung 사운드다운");
	}

	@Override
	public void powerOn() {
		p.powerOn();
		//System.out.println("samsung 파워온");
	}

	@Override
	public void powerOff() {
		p.powerOff();
		//System.out.println("samsung 파워오프");
		
	}

}
