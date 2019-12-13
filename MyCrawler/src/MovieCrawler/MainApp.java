package MovieCrawler;

public class MainApp {

	public static void main(String[] args) throws Exception {

		NaverCrawler naver = new NaverCrawler(100);
		Yes24Crawler yes24 = new Yes24Crawler(100);

		naver.crawl();
		yes24.crawl();

		for (int i = 0; i < 100; i++) {
			System.out.println(i+"¹ø");
			System.out.println(naver.getTitle()[i] +" "+ naver.getPrice()[i]);
			System.out.println(yes24.getTitle()[i] +" "+ yes24.getPrice()[i]);
			System.out.println("======");
		}
	}

}
