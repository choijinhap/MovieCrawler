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
		/*
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
		*/
		
		
		/*
		 * 생성자 인자는 크롤링할 개수
		 * 네이버는 사이트 자체에 100위까지 밖에 없음
		 * yes24는 현재 100위까지만 구하도록 구현
		 * 웨이브는 500위 까지는 테스트 완료
		 */
	
		
		NaverCrawler naver = new NaverCrawler(100);
		
		Yes24Crawler yes24 = new Yes24Crawler(100);
		
		WavveCrawler wavve = new WavveCrawler(100);
		
		/* 
		 * 크롤러들 공통 변수
		 * 
		 * String[] title; 
		 * String[] price; 
		 * 
		 * --NaverCrawler만 가지고 있음--
		 * String[] imgURI; 
		 * String[] linkURL; 
		 * String[] content; 
		 * String[] type;
		 */
		
		naver.crawl();
		yes24.crawl();
		wavve.crawl();
		
		//테스트
		for (int i = 0; i < 100; i++) {
			System.out.println(i + "번");
			System.out.println(naver.getTitle()[i] + " " + naver.getPrice()[i]);
			System.out.println(yes24.getTitle()[i] + " " + yes24.getPrice()[i]);
			System.out.println(wavve.getTitle()[i] + " " + wavve.getPrice()[i]);
			System.out.println("======");
		}
		
	}

}
