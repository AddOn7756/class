package test;

public class SamsungTV implements TV{

	@Override
	public void chUP() {
		System.out.println("samsung ä�ξ�");
	}

	@Override
	public void chDown() {
		System.out.println("samsung ä�δٿ�");
	}

	@Override
	public void soundUp() {
		System.out.println("samsung �����");
	}

	@Override
	public void soundDown() {
		System.out.println("samsung ����ٿ�");
	}

	@Override
	public void powerOn() {
		System.out.println("samsung �Ŀ���");
	}

	@Override
	public void powerOff() {
		System.out.println("samsung �Ŀ�����");
		
	}

}
