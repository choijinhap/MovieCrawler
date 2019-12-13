package MovieCrawler;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WavveCrawler {
	String URLh = "https://apis.pooq.co.kr/cf/movie/contents?WeekDay=all&adult=n&broadcastid=161966&came=movie&contenttype=movie&genre=all&limit=";
	String URLf="&offset=0&orderby=viewtime&page=1&price=all&sptheme=svod&uiparent=FN0&uirank=0&uitype=MN85&apikey=E5F3E0D30947AA5440556471321BB6D9&credential=none&device=pc&drm=wm&partner=pooq&pooqzone=none&region=kor&targetage=auto";
	
	
	int size;
	String[] title;
	String[] price;
	WebDriver driver;
	public WavveCrawler(int size, WebDriver driver) {
		
		this.driver=driver;
		this.size = size;
		this.title = new String[size];
		this.price = new String[size];
	}

	public void crawl() throws Exception {
		driver.get(URLh+size+URLf);
		System.out.println(driver.getPageSource());
		
		driver.close();
	}

	public String[] getTitle() {
		return title;
	}

	public String[] getPrice() {
		return price;
	}
}
