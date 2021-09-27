package coupling;

public class Client {

	public static void main(String[] args) {
		BeanFactory factory=new BeanFactory();
		TV tv=(TV)factory.getBean(args[0]);
		tv.chUP();
		tv.chDown();
		tv.soundUp();
		tv.soundDown();
		tv.powerOn();
		tv.powerOff();
		
	}
}
