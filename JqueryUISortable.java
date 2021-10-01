package week4.day2.assignment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryUISortable {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//sortable
		driver.get("https://jqueryui.com/sortable/");
		driver.switchTo().frame(0);
	WebElement sort1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[1]"));
	WebElement sort2 = driver.findElement(By.xpath("//ul[@id='sortable']/li[5]"));
	WebElement sort3 = driver.findElement(By.xpath("//ul[@id='sortable']/li[3]"));
	WebElement sort4 = driver.findElement(By.xpath("//ul[@id='sortable']/li[6]"));
	 Actions builder=new Actions(driver);	
	 
	//  int x = sort1.getLocation().getX();
	 //  int y = sort1.getLocation().getY();	
	//  
	 builder.dragAndDrop(sort2, sort1).perform();
	// int x1 = sort3.getLocation().getX();
	 //  int y1 = sort3.getLocation().getY();	
	// builder.dragAndDrop(sort4, sort3).perform();
	
	builder.clickAndHold(sort4).moveToElement(sort3).release(sort3).perform();
	  driver.switchTo().defaultContent();
		//driver.close();
	}

}
