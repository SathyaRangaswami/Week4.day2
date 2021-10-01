package week4.day2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResizableExample {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/resizable/");
		WebElement framesize = driver.findElement(By.xpath("//iframe[@src='/resources/demos/resizable/default.html']"));
		driver.switchTo().frame(framesize);
		WebElement drag = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		File src1=drag.getScreenshotAs(OutputType.FILE);
		File dst=new File("./snaps/seat1.png");
		FileUtils.copyFile(src1, dst);
		Actions builder=new Actions(driver);
		builder.clickAndHold(drag).perform();
		Point location = drag.getLocation();
		System.out.println("location"+location);
		builder.moveToElement(drag, -100,-100).perform();
		File src2=drag.getScreenshotAs(OutputType.FILE);
		File dst2=new File("./snaps/seat2.png");
		FileUtils.copyFile(src2, dst2);
		
	}

}
