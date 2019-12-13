package MovieCrawler;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainApp {

	public static void main(String[] args) throws Exception {
		/*
		 * 드라이버설정
		 * 크롬 브라우저에 chrome://version/ 들어가서 버전에맞는 드라이버 설치후 경로 설정
		 */
		WebDriver driver;
		String driverPath="D:\\chromedriver.exe";
		//String driverPath="/Users/choijinhap/chromedriver";
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions chromeOptions = new ChromeOptions(); 
		chromeOptions.addArguments("--headless"); 
		chromeOptions.addArguments("--no-sandbox");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		
		
		//네이버는 인기순위가 100위까지 밖에없음
		NaverCrawler naver = new NaverCrawler(80);
		
		Yes24Crawler yes24 = new Yes24Crawler(100);
		
		//wavve의 경우 500위까지는 확인됨 그이상은 확인 안해봄.
		WavveCrawler wavve = new WavveCrawler(500);
		/* 
		 * String[] title; 
		 * String[] price; 
		 * 
		 * NaverCrawler만 가지고 있음
		 * String[] imgURI; 
		 * String[] linkURL; 
		 * String[] content; 
		 * String[] type;
		 */
		naver.crawl();
	//	yes24.crawl();
		//wavve.crawl();
		
		for(int i=0;i<80;i++) {
			System.out.println(i+" "+naver.getTitle()[i]);
		}
		/*
		for (int i = 0; i < 100; i++) {
			System.out.println(i + "번");
			System.out.println(naver.getTitle()[i] + " " + naver.getPrice()[i]);
			System.out.println(yes24.getTitle()[i] + " " + yes24.getPrice()[i]);
			System.out.println("======");
		}
		*/
	}

}
