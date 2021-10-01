package week4.day2.assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTC {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro " + Keys.ENTER);
		String price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("price of mobile:" + price);

		WebElement rating = driver
				.findElement(By.xpath("//i[@class='a-icon a-icon-star-small a-star-small-4-5 aok-align-bottom']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(rating).click(rating).perform();
		String customerRating = driver.findElement(By.xpath("//span[@data-hook='acr-average-stars-rating-text']"))
				.getText();
		System.out.println("Cutomer rating :" + customerRating);
		String StarPer = driver.findElement(By.xpath("//td[@class='a-text-right a-nowrap']/span/a")).getText();
		System.out.println("5star percentage rating :" + StarPer);
		driver.findElement(By.xpath("//a[@class='a-link-normal a-text-normal']/span")).click();
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dst1 = new File("./snaps/amazonmobile.png");
		FileUtils.copyFile(src1, dst1);
		Set<String> mobile = driver.getWindowHandles();

		List<String> mobile1 = new ArrayList<String>(mobile);
		driver.switchTo().window(mobile1.get(1));
		driver.findElement(By.id("add-to-cart-button")).click();
		 //driver.findElement(By.id("nav-cart-count")).click();
		//	String total = driver.findElement(By.id("sc-subtotal-label-buybox")).getText();
		
		String total = driver.findElement(By.xpath("//*[@id=\"attach-accessory-cart-subtotal\"]")).getText();
		System.out.println("total in the cart" + total);
		if (price.equals(total)) {
			System.out.println("price is matching"+total);
		} else {
			System.out.println("price is not matching");
		}

		driver.quit();

	}
}
