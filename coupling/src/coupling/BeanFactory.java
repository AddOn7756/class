package coupling;

public class BeanFactory {
	public Object getBean(String beanName) {
		if(beanName.equals("samsung")) {
			return new SamsungTV();
		}
		else if(beanName.equals("Lg")) {
			return new LgTV();
		}
		return null;
	}
}
