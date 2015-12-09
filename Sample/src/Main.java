import com.ralen.ApplicationContext;

public class Main {
	public static void main(String[] args){
		ApplicationContext context = new ApplicationContext("");
		Sample sample = context.getBean("sample");
		sample.printMe();
	}
}
