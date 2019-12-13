package MovieCrawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PlaystoreCrawler {
	String URL="https://play.google.com/store/movies/top";
	
	int size;
	String[] title;
	String[] price;
	WebDriver driver;
	Actions action;
	
	public PlaystoreCrawler(int size,WebDriver driver) {
		this.size=size;
		this.title=new String[size];
		this.price=new String[size];
		this.driver =driver;
		action=new Actions(driver);
		
	}
	
	public void crawl() throws Exception{
		driver.get(URL);
		Thread.sleep(7000);
		for(int i=0;i<8;i++) {
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
		}
		List<WebElement> prices= driver.findElements(By.className("VfPpfd"));
		System.out.println(prices.get(0).getText());
		//driver.close();
	}
	
	public String[] getTitle() {
		return title;
	}

	public String[] getPrice() {
		return price;
	}
}
