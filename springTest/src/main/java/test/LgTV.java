package test;

public class LgTV implements TV{

	Phone p;
	
	public LgTV(){
		//System.out.println("�ＺTV �⺻������");
	}
	
	public LgTV(Phone p){
		this.p = p;
		//System.out.println("�ＺTV ������");
	}
	
	@Override
	public void chUP() {
		p.chUP();
		//System.out.println("LG ä�ξ�");
	}

	@Override
	public void chDown() {
		p.chDown();
		//System.out.println("LG ä�δٿ�");
	}

	@Override
	public void soundUp() {
		p.soundUp();
		//System.out.println("LG �����");
	}

	@Override
	public void soundDown() {
		p.soundDown();
		//System.out.println("LG ����ٿ�");
	}

	@Override
	public void powerOn() {
		p.powerOn();
		//System.out.println("LG �Ŀ���");
	}

	@Override
	public void powerOff() {
		p.powerOff();
		//System.out.println("LG �Ŀ�����");
		
	}

	
	
	
}
