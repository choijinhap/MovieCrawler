package MovieCrawler;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class MainApp {

	public static void main(String[] args) throws Exception {
		/*
		 * 드라이버설정
		 * 크롬 브라우저에 chrome://version/ 들어가서 버전에맞는 크롬드라이버 설치후 경로 설정
		 */

		WebDriver driver;
		// String driverPath="D:\\chromedriver.exe";
	//	String driverPath = "/Users/choijinhap/chromedriver";
		String driverPath = "/Users/choijinhap/geckodriver";
	//	System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.gecko.driver", driverPath);
		
		ChromeOptions chromeOptions = new ChromeOptions();
	//	chromeOptions.addArguments("--headless");
	//	chromeOptions.addArguments("--no-sandbox");
	//	chromeOptions.addArguments("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
	
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		//firefoxOptions.addArguments("--headless");
		firefoxOptions.addArguments("--no-sandbox");//	driver = new ChromeDriver(chromeOptions);
		driver = new FirefoxDriver(firefoxOptions);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);


		/*
		 * 네이버시리즈 청불 컨텐츠 떄문에 아이디와 패스워드필요 
		 * 
		 * */
		String naverID="";
		String naverPW="";
		
		/*
		 * 생성자 인자는 크롤링할 개수
		 * 네이버는 사이트 자체에 100위까지 밖에 없음
		 * 예스24,웨이브는 500위까지 테스트완료
		 * 플레이스토어는 200위까지 밖에 없
		 */
		NaverCrawler naver = new NaverCrawler(100,driver,naverID,naverPW);

		Yes24Crawler yes24 = new Yes24Crawler(200);

		WavveCrawler wavve = new WavveCrawler(200);

		PlaystoreCrawler ps = new PlaystoreCrawler(200, driver);

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
		ps.crawl();
		
		// 테스트 
		/*
		  for (int i = 0; i < 100; i++) { System.out.println(i);
		  System.out.println(naver.getTitle()[i] + " " + naver.getPrice()[i]);
		  System.out.println(yes24.getTitle()[i] + " " + yes24.getPrice()[i]);
		  System.out.println(wavve.getTitle()[i] + " " + wavve.getPrice()[i]);
		  System.out.println(ps.getTitle()[i] + " " + ps.getPrice()[i]);
		  System.out.println("======"); }
		*/
	}

}
