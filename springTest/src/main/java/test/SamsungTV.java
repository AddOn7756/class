package test;

public class SamsungTV implements TV{

	Phone p;
	
	public SamsungTV(){
		System.out.println("�ＺTV �⺻������");
	}
	
	public SamsungTV(Phone p){
		this.p = p;
		System.out.println("�ＺTV ������");
	}
	
	
	@Override
	public void chUP() {
		p.chUP();
		//System.out.println("samsung ä�ξ�");
	}

	@Override
	public void chDown() {
		p.chDown();
		//System.out.println("samsung ä�δٿ�");
	}

	@Override
	public void soundUp() {
		p.soundUp();
		//System.out.println("samsung �����");
	}

	@Override
	public void soundDown() {
		p.soundDown();
		//System.out.println("samsung ����ٿ�");
	}

	@Override
	public void powerOn() {
		p.powerOn();
		//System.out.println("samsung �Ŀ���");
	}

	@Override
	public void powerOff() {
		p.powerOff();
		//System.out.println("samsung �Ŀ�����");
		
	}

}
