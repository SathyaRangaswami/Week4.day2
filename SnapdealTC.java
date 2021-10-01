package week4.day2.assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealTC {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
				option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://www.snapdeal.com/");
		driver.findElement(By.xpath("(//span[text()=\"Men's Fashion\"])[2]")).click();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		String text = driver.findElement(By.xpath("//a[@class='child-cat-node dp-widget-link hashAdded']/div[2]")).getText();
		System.out.println("no of shoes"+text);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
	 driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
	 Thread.sleep(2000);
		List<WebElement> priceListSort = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		System.out.println("Size of Price list from low to high"+priceListSort.size());

	List<String> listPrice=new ArrayList<String>();
		for (WebElement eachitem : priceListSort) {
			String plist = eachitem.getText();
			listPrice.add(plist);
		}
		
			System.out.println("Price list from low to high: "+listPrice);
			
		
		WebElement fromval = driver.findElement(By.name("fromVal"));
		fromval.clear();
		fromval.sendKeys("900");
		WebElement toval = driver.findElement(By.name("toVal"));
		toval.clear();
		toval.sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
//driver.findElement(By.xpath("//label[@for='Color_s-White']")).click();
		List<WebElement> snapFilter = driver.findElements(By.xpath("//div[@class='filters']/div/a"));
		for (WebElement eachitem : snapFilter) {
			String fil = eachitem.getText();
			System.out.println("filters from snap"+fil);			
		}
		Thread.sleep(2000);
		WebElement shoe = driver.findElement(By.xpath("//p[@class='product-title']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(shoe).perform();
		
		driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]")).click();
		String text2 = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Price of the shoe : " + text2);

		String text3 = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discount Percentage of the shoe : " + text3);

		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snapDeal/snapdeal.png");
		FileUtils.copyFile(src, dst);
		
		driver.close();
		
		
	}
	

}
