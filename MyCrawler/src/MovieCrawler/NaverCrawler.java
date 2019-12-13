package MovieCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

	public NaverCrawler(int size) {
		this.size = size;
		this.title = new String[size];
		this.imgURI = new String[size];
		this.linkURL = new String[size];
		this.price = new String[size];
		this.content = new String[size];
		this.type = new String[size];
	}

	public Elements getMovies(int pagenum) throws Exception {
		Document doc = Jsoup.connect(naverURLh + pagenum + naverURLf).get();
		Elements movies = doc.select(".lst_thum li");
		return movies;
	}

	public void crawl() throws Exception {
		int idx = 0;
		for (int pagenum = 1; pagenum < 6; pagenum++) {
			if(idx>=size) {break;}
			Elements movies = getMovies(pagenum);
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
				idx++;
			}
		}
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
