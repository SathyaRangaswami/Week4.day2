package week4.day2.assignment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryUISelectable {
	public static void main(String[] args) throws IOException, InterruptedException {	
	
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	//select
	driver.get("https://jqueryui.com/selectable/");
	driver.switchTo().frame(0);
	  WebElement item1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
	  WebElement item5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
	Actions builder=new Actions(driver);
	//builder.clickAndHold(item5).perform();
	builder.dragAndDrop(item1, item5).perform();
	File src=driver.getScreenshotAs(OutputType.FILE);
	File dst=new File("./snaps/select.png");
	FileUtils.copyFile(src, dst);
	}

}