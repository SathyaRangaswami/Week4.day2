package week4.day2.assignment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryUIDroppable {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//droppable
		driver.get("https://jqueryui.com/droppable/");
		driver.switchTo().frame(0);
		WebElement drag = driver.findElement(By.id("draggable"));
		Point location = drag.getLocation();
		System.out.println(location);
		WebElement drop = driver.findElement(By.id("droppable"));
		Actions builder=new Actions(driver);
		builder.dragAndDrop(drag, drop).perform();
		File src1=drag.getScreenshotAs(OutputType.FILE);
		File dst1=new File("./snaps/drag&drop.png");
		FileUtils.copyFile(src1, dst1);
		driver.switchTo().defaultContent();

}
}
