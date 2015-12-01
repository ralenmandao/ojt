import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver


class HipChatBot {
	
	static final WebDriver driver = new FirefoxDriver();
	
	static void main(arg){
		String last = ""
		driver.navigate().to("https://toro-io.hipchat.com/chat/room/2141750");
		driver.manage().window().maximize();

		By email = By.id("email");
		waitFor(email, driver);
		driver.findElement(email).sendKeys("toro.ralen.mandap@gmail.com");
		driver.findElement(By.id("password")).sendKeys("ralen123");
		driver.findElement(By.xpath(".//*[@id='signin']")).click();

		last = getNextInput().getAttribute('data-reactid');
		def sent = ""
		while(true){
			Thread.sleep(10)
			def next = getNextInput()
			def id = next.getAttribute('data-reactid')
			if(!last.equals(id) && !sent.equals(next.text)){
				last = id;
				def s = next.text
				sent = s
				send(s)
			}
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
	static def send(string){
		WebElement input = driver.findElement(By.id('hc-message-input'))
		input.sendKeys(string);
		input.sendKeys(Keys.RETURN)
	}
	static def getNextInput(){
		By by = By.cssSelector(".hc-messages .date-block:last-of-type .hc-chat-row:last-of-type")
		waitFor(by,driver)
		def lastChat = driver.findElement(by)
		def textChat = lastChat.findElement(By.className('msg-line'))
		return textChat
	}
}
