package coupling;

public class LgTV implements TV{

	@Override
	public void chUP() {
		System.out.println("LG ä�ξ�");
	}

	@Override
	public void chDown() {
		System.out.println("LG ä�δٿ�");
	}

	@Override
	public void soundUp() {
		System.out.println("LG �����");
	}

	@Override
	public void soundDown() {
		System.out.println("LG ����ٿ�");
	}

	@Override
	public void powerOn() {
		System.out.println("LG �Ŀ���");
	}

	@Override
	public void powerOff() {
		System.out.println("LG �Ŀ�����");
		
	}

	
	
	
}
