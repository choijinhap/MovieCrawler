package MovieCrawler;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainApp {

	public static void main(String[] args) throws Exception {
		/*
		 * ����̹�����
		 * ũ�� �������� chrome://version/ ���� �������´� ����̹� ��ġ�� ��� ����
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
		
		
		//���̹��� �α������ 100������ �ۿ�����
		NaverCrawler naver = new NaverCrawler(80);
		
		Yes24Crawler yes24 = new Yes24Crawler(100);
		
		//wavve�� ��� 500�������� Ȯ�ε� ���̻��� Ȯ�� ���غ�.
		WavveCrawler wavve = new WavveCrawler(500);
		/* 
		 * String[] title; 
		 * String[] price; 
		 * 
		 * NaverCrawler�� ������ ����
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
			System.out.println(i + "��");
			System.out.println(naver.getTitle()[i] + " " + naver.getPrice()[i]);
			System.out.println(yes24.getTitle()[i] + " " + yes24.getPrice()[i]);
			System.out.println("======");
		}
		*/
	}

}
