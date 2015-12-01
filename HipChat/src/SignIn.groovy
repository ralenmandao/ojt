import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;


class SignIn {
	public static void main(String[] args) throws InterruptedException{
		WebDriver driver = new FirefoxDriver();

		//Launch website
		driver.navigate().to("https://toro-io.hipchat.com/chat/room/2141750");
		
		//Maximize the browser
		driver.manage().window().maximize();
		
		By email = By.id("email");
		waitFor(email, driver);
		driver.findElement(email).sendKeys("toro.ralen.mandap@yahoo.com");
		driver.findElement(By.id("password")).sendKeys("ralen123");
		driver.findElement(By.xpath(".//*[@id='signin']")).click();
		By input = By.id("hc-message-input");
		waitFor(input, driver);
		WebElement temp;
		def chats = driver.findElements(By.className("hc-chat-msg"))
		println chats.last().text
		
//		// Click on Math Calculators
//		waitFor(By.xpath(".//*[@id='user_name']"), driver);
//		driver.findElement(By.xpath(".//*[@id='user_name']")).sendKeys("ralen.mandap");
//		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("P@ssword10");
//		driver.findElement(By.xpath(".//*[@id='login_btn']")).click();
//		waitFor(By.xpath(".//*[@id='inOutIcon']"), driver);
//		driver.findElement(By.xpath(".//*[@id='inOutIcon']")).click();
//		waitFor(By.xpath(".//*[@id='saveIcon']"), driver);
//		driver.findElement(By.xpath(".//*[@id='saveIcon']")).click();
		
		
		
		//driver.close();
	}
	
	private static void waitFor(By by, WebDriver driver) throws InterruptedException{
		Thread.sleep(1000);	
		try{
				System.out.println("TRY");
				driver.findElement(by);
				Thread.sleep(500);	
			}catch(Exception e){
				waitFor(by,driver);
			}

	}
}
