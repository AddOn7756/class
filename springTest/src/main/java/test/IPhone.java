package test;


public class IPhone implements Phone{

	@Override
	public void chUP() {
		System.out.println("IPhon���� TV ä��UP");
	}

	@Override
	public void chDown() {
		System.out.println("IPhon���� TV ä��DOWN");
	}

	@Override
	public void soundUp() {
		System.out.println("IPhon���� TV �Ҹ�UP");
	}

	@Override
	public void soundDown() {
		System.out.println("IPhon���� TV �Ҹ�DOWN");
	}

	@Override
	public void powerOn() {
		System.out.println("IPhon���� TV ����ON");
	}

	@Override
	public void powerOff() {
		System.out.println("IPhon���� TV ����OFF");
	}

	
	
}
