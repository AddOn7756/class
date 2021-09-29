package test;

public class GalaxyPhone implements Phone{

	@Override
	public void chUP() {
		System.out.println("Galaxy로 TV 채널UP");
	}

	@Override
	public void chDown() {
		System.out.println("Galaxy로 TV 채널DOWN");
	}

	@Override
	public void soundUp() {
		System.out.println("Galaxy로 TV 소리UP");
	}

	@Override
	public void soundDown() {
		System.out.println("Galaxy로 TV 소리DOWN");
	}

	@Override
	public void powerOn() {
		System.out.println("Galaxy로 TV 전워ON");
	}

	@Override
	public void powerOff() {
		System.out.println("Galaxy로 TV 전원OFF");
	}
	
	
}
