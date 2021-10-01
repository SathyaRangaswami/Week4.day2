package week4.day2.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaTC {
	
	@Test
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
				option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://www.nykaa.com/");
		WebElement brands =driver.findElement(By.xpath("//a[text()='brands']"));
			//	driver.findElement(By.linkText("brands")).click();
		Actions builder=new Actions(driver);
		builder.moveToElement(brands).perform();
		driver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]")).click();
		String title = driver.getTitle();
		System.out.println("Title of the page: "+title);
		if(title.contains("L'Oreal Paris"))
		{
			System.out.println("title is verified");
		}
		else
			System.out.println("title is not matching");

	//builder.sendKeys(Keys.PAGE_DOWN).perform();		
		driver.findElement(By.xpath("//span[@class='pull-left']")).click();
		driver.findElement(By.xpath("//div[@class='sort-container show-sort']/div[4]/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//label[@for='chk_Shampoo_undefined']")).click();
		driver.findElement(By.xpath("//div[text()='Concern']")).click();
		driver.findElement(By.xpath("//label[contains(@for,'chk_Color Protection')]")).click();
		List<WebElement> filter = driver.findElements(By.xpath("//ul[@class='pull-left applied-filter-lists']/li"));
		for (WebElement eachitem : filter) {
			String fil=eachitem.getText();
			System.out.println("filters applied :"+fil);
		}
			driver.findElement(By.xpath("//img[@alt=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
			Set<String> nykWin1 = driver.getWindowHandles();
			List<String> nykList=new ArrayList<String>(nykWin1);
			driver.switchTo().window(nykList.get(1));
			driver.findElement(By.xpath("//span[text()='175ml']")).click();
			String price = driver.findElement(By.xpath("//div[@class='price-info']/span[2]/span")).getText();
			System.out.println("price of the shampoo: "+price);
			driver.findElement(By.xpath("//button[text()='ADD TO BAG']")).click();
			driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
			Thread.sleep(2000);
			String grandShopBag = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
			System.out.println("Grand totla in shopping bag"+grandShopBag);	
			driver.findElement(By.xpath("//div[@class='second-col']/button")).click();
	/*	WebElement procButton = driver.findElement(By.xpath("//div[@class='second-col']/button"));
		
		builder.moveToElement(procButton).click(procButton).perform();*/
			System.out.println("Size if windows :" +filter.size());
			driver.findElement(By.xpath("//button[@class='btn full big']")).click();
			String finaltotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div/span")).getText();
			System.out.println("FInal grand total :"+finaltotal);
			if(grandShopBag.equals(finaltotal))
			{
				System.out.println("Amount is same in both total");
			}
			else
				
				System.out.println("Amount is different");
			driver.quit();
			
			
			
	
	}
}
