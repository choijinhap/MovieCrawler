package MovieCrawler;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NaverCrawler {
	String naverURLh = "https://series.naver.com/movie/top100List.nhn?page=";
	String naverURLf = "&rankingTypeCode=PC_D";

	int size;
	String[] title;
	String[] imgURI;
	String[] linkURL;
	String[] price;
	String[] content;
	String[] type;
	String[] genre;
	WebDriver driver;
	Actions action;

	
	public NaverCrawler(int size,WebDriver driver) {
		this.size = size;
		this.title = new String[size];
		this.imgURI = new String[size];
		this.linkURL = new String[size];
		this.price = new String[size];
		this.content = new String[size];
		this.type = new String[size];
		this.driver=driver;
		action = new Actions(driver);

	}
	
	public void cb_input(String xpath,String input) {
		String ctc=input;
		StringSelection stringSelection=new StringSelection(ctc);
		Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(stringSelection, null);
		driver.findElement(By.xpath(xpath)).click();
		//action.sendKeys(Keys.COMMAND+"v").perform();
		//action.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();
		//action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();

	}
	
	public Map<String,String> getCookies() throws Exception{
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com");
		//driver.findElement(By.xpath("//*[@id='account']/div/a/i")).click();
		Thread.sleep(500);
		
		cb_input("//*[@id='id']", "pppooo95");
		Thread.sleep(500);
		cb_input("//*[@id='pw']", "vlsemnf!@");
	
		Thread.sleep(1000);
		//WebElement button = driver.findElement(By.cssSelector("#login > form > fieldset > button"));
	    //button.submit();
	    WebElement btn_login=driver.findElement(By.xpath("//*[@id='frmNIDLogin']/fieldset/input"));
	    btn_login.click();
		Thread.sleep(3000);
	    Set<Cookie> cookies=driver.manage().getCookies();
	    Map<String, String> cookieMap = cookies.stream().collect(Collectors.toMap(Cookie::getName, Cookie::getValue));

		return cookieMap;
	}

	public Elements getMovies(int pagenum,Map<String,String> cookies) throws Exception {
		Document doc = Jsoup.connect(naverURLh + pagenum + naverURLf).cookies(cookies).get();
		Elements movies = doc.select(".lst_thum li");
		return movies;
	}

	public void crawl() throws Exception {
		Map<String,String> cookies=getCookies();
		int idx = 0;
		System.out.println("네이버 크롤링중");
		for (int pagenum = 1; pagenum < 6; pagenum++) {
			if(idx>=size) {return;}
			Elements movies = getMovies(pagenum,cookies);

			for (Element element : movies) {
				String tempTitle = element.select("a").attr("title");
				title[idx] = tempTitle;

				imgURI[idx] = element.select("a img").attr("src");

				type[idx] = element.select("p em").text();

				price[idx] = element.select("p span").first().text();

				linkURL[idx] = "https://series.naver.com" + element.select("a").attr("href");

				Document newdoc = Jsoup.connect(linkURL[idx]).get();

				Elements detail = newdoc.select(".end_dsc");
				String tempContent = detail.text();
				if (tempContent.length() > 2
						&& tempContent.substring(tempContent.length() - 2, tempContent.length()).equals("접기")) {
					tempContent = tempContent.substring(0, tempContent.length() - 2);
				}
				content[idx] = tempContent;
				
				Elements test=newdoc.select(".info_lst");
				System.out.println(idx+" "+test.select("a").first().text());
				
				
				idx++;
				if(idx%10==0) {
					System.out.print("-");
				}
			}
		}
		System.out.println("");
	}

	public String[] getTitle() {
		return title;
	}

	public String[] getImgURI() {
		return imgURI;
	}

	public String[] getLinkURL() {
		return linkURL;
	}

	public String[] getPrice() {
		return price;
	}

	public String[] getContent() {
		return content;
	}

	public String[] getType() {
		return type;
	}
}
