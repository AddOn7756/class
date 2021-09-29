package test;


public class IPhone implements Phone{

	@Override
	public void chUP() {
		System.out.println("IPhon으로 TV 채널UP");
	}

	@Override
	public void chDown() {
		System.out.println("IPhon으로 TV 채널DOWN");
	}

	@Override
	public void soundUp() {
		System.out.println("IPhon으로 TV 소리UP");
	}

	@Override
	public void soundDown() {
		System.out.println("IPhon으로 TV 소리DOWN");
	}

	@Override
	public void powerOn() {
		System.out.println("IPhon으로 TV 전워ON");
	}

	@Override
	public void powerOff() {
		System.out.println("IPhon으로 TV 전원OFF");
	}

	
	
}
