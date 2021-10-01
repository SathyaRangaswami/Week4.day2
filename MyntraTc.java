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

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyntraTc {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("https://www.myntra.com/");
		WebElement menfas = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		
		Actions builder=new Actions(driver);
		builder.moveToElement(menfas).perform();
		
		WebElement jackets = driver.findElement(By.linkText("Jackets"));
		builder.click(jackets).perform();
		String jacketCount = driver.findElement(By.xpath("//span[@class='categories-num']")).getText();
		int jacketCount1 = Integer.parseInt(jacketCount.replaceAll("\\D", ""));
		System.out.println("count of jackets from category: "+ jacketCount1);
		
		String jackCou = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		int jackCou1 = Integer.parseInt(jackCou.replaceAll("\\D", ""));
		System.out.println("jacket count from header: "+jackCou1);
		if(jacketCount1==jackCou1)
		{
			System.out.println("jacket count matched");
		}
		else
			System.out.println("jacket count not matched");
		WebElement more = driver.findElement(By.xpath("//div[@class='brand-more']"));
		builder.moveToElement(more).click().perform();
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
		driver.findElement(By.xpath("//label[@class=' common-customCheckbox']")).click();
		WebElement closeBrand = driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']"));
		builder.click(closeBrand).perform();
	
		List<WebElement> brandList = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		List<String> brName=new ArrayList<String>();
		
		for (WebElement eachitem : brandList) {
			String brandName = eachitem.getText();
			brName.add(brandName);			
			if(!brName.equals("Duke"))
			{
				System.out.println("Brand name same" );		
			}
			System.out.println("Brand Name :"+brName);
		}
			System.out.println("Size: "+brName.size());
		driver.findElement(By.className("sort-sortBy")).click();
		driver.findElement(By.xpath("//ul[@class='sort-list']/li[3]")).click();

		String price = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Price of the highest discount product"+price);
		driver.findElement(By.xpath("//picture[@class='img-responsive']/img")).click();
		Thread.sleep(2000);
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dst=new File("./snaps/myntra.png");
		FileUtils.copyFile(src, dst);
		
	 Set<String> windowHandles = driver.getWindowHandles();
		List<String> winHan=new ArrayList<String>(windowHandles);
		driver.switchTo().window(winHan.get(1));
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		driver.quit();
		
	}
 
}
