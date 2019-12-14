package MovieCrawler;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainApp {

	public static void main(String[] args) throws Exception {
		/*
		 * ����̹����� ũ�� �������� chrome://version/ ���� �������´� ũ�ҵ���̹� ��ġ�� ���
		 * ����
		 */

		WebDriver driver;
		// String driverPath="D:\\chromedriver.exe";
		String driverPath = "/Users/choijinhap/chromedriver";

		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--no-sandbox");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

		/*
		 * ������ ���ڴ� ũ�Ѹ��� ���� ���̹��� ����Ʈ ��ü�� 100������ �ۿ� ���� ����24,���̺��
		 * 500������ �׽�Ʈ�Ϸ�
		 */

		NaverCrawler naver = new NaverCrawler(100);

		Yes24Crawler yes24 = new Yes24Crawler(100);

		WavveCrawler wavve = new WavveCrawler(100);

		PlaystoreCrawler ps = new PlaystoreCrawler(100, driver);

		/*
		 * ũ�ѷ��� ���� ����
		 * 
		 * String[] title; String[] price;
		 * 
		 * --NaverCrawler�� ������ ����-- String[] imgURI; String[] linkURL; String[]
		 * content; String[] type;
		 */

		naver.crawl();
		yes24.crawl();
		wavve.crawl();
		ps.crawl();

		// �׽�Ʈ
		/*
		 * for (int i = 0; i < 100; i++) { System.out.println(i + "��");
		 * System.out.println(naver.getTitle()[i] + " " + naver.getPrice()[i]);
		 * System.out.println(yes24.getTitle()[i] + " " + yes24.getPrice()[i]);
		 * System.out.println(wavve.getTitle()[i] + " " + wavve.getPrice()[i]);
		 * System.out.println("======"); }
		 */
	}

}
