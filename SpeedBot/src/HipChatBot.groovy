import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver


class HipChatBot {
	
	static final WebDriver driver = new FirefoxDriver();
	
	static void main(arg){
		String last = ""
		driver.navigate().to("http://typing-speed-test.aoeu.eu/?lang=en");
		driver.manage().window().maximize();
		
		By input = By.id('input')
		By current = By.className('currentword')
		By timer = By.id('timer')
		WebElement timerElement = driver.findElement(timer)
		WebElement inputElement = driver.findElement(input)
		
		waitFor(input, driver)
		
		while(!timerElement.text.equals('0')){
			current = By.className('currentword')
			def text = driver.findElement(current).text
			inputElement.sendKeys("${text} ")	
		}
	}
	
	static def waitFor(def b, def d){
		Thread.sleep(1000);
		try{
			System.out.println("TRY");
			d.findElement(b);
			Thread.sleep(500);
		}catch(Exception e){
			waitFor(b,d);
		}

	}
	static def waitForClass(def b, def d){
		Thread.sleep(1000);
		def e = d.findElements(b);
		if(e.size() == 0){
			waitForClass(b,d)
		}
	}
}
