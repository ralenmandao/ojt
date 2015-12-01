import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;


class SignIn {
	public static void main(String[] args) throws InterruptedException{
		WebDriver driver = new FirefoxDriver();

		//Launch website
		driver.navigate().to("http://192.168.21.203:8085/");
		
		//Maximize the browser
		driver.manage().window().maximize();
		
//		// Click on Math Calculators
		waitFor(By.xpath(".//*[@id='user_name']"), driver);
		driver.findElement(By.xpath(".//*[@id='user_name']")).sendKeys("ralen.mandap");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("P@ssword10");
		driver.findElement(By.xpath(".//*[@id='login_btn']")).click();
		waitFor(By.xpath(".//*[@id='inOutIcon']"), driver);
		driver.findElement(By.xpath(".//*[@id='inOutIcon']")).click();
		waitFor(By.xpath(".//*[@id='saveIcon']"), driver);
		driver.findElement(By.xpath(".//*[@id='saveIcon']")).click();
		
//		//*[@id='login_btn']
//		// Click on Percent Calculators
//		driver.findElement(By.xpath(".//*[@id='menu']/div[4]/div[3]/a")).click();
//		
//		// Enter value 10 in the first number of the percent Calculator
//		driver.findElement(By.id("cpar1")).sendKeys("10");
//		
//		// Enter value 50 in the second number of the percent Calculator
//		driver.findElement(By.id("cpar2")).sendKeys("50");
//		
//		// Click Calculate Button
//		driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr/td[2]/input")).click();
//		
//		// Get the Result Text based on its xpath
//		String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/span/font/b")).getText();
//		
//		// Print a Log In message to the screen
//		System.out.println(" The Result is " + result);
//		
//		//Close the Browser.
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
