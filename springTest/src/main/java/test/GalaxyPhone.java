package test;

public class GalaxyPhone implements Phone{

	@Override
	public void chUP() {
		System.out.println("Galaxy�� TV ä��UP");
	}

	@Override
	public void chDown() {
		System.out.println("Galaxy�� TV ä��DOWN");
	}

	@Override
	public void soundUp() {
		System.out.println("Galaxy�� TV �Ҹ�UP");
	}

	@Override
	public void soundDown() {
		System.out.println("Galaxy�� TV �Ҹ�DOWN");
	}

	@Override
	public void powerOn() {
		System.out.println("Galaxy�� TV ����ON");
	}

	@Override
	public void powerOff() {
		System.out.println("Galaxy�� TV ����OFF");
	}
	
	
}
