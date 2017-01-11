package com.ganesh.gsmarena.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ganesh.gsmarena.model.GSMArenaCurrentStatus;
import com.ganesh.gsmarena.util.GSMArenaParseUtility;

/**
 * @author shriganesh
 *
 */
public class GSMArenaCrawler {
	public static void main(String[] args) {
		String website = "http://www.gsmarena.com/";

		Document fullPage = null;
		try {
			fullPage = Jsoup.connect(website).data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
					.timeout(3000).post();

			GSMArenaCurrentStatus currentStatus = new GSMArenaCurrentStatus();

			// parsing path from div id body--> div "sidebar col left" --> div
			// "brandmenu-v2 light l-box clearfix" --> 2nd element which is list
			
			Elements initial = fullPage.select("div#body");
			Elements brandList = initial.first().children().get(1).children().first().child(1).children();
			for (Element el : brandList) {
				String bPage = el.children().first().absUrl("href");

				// Extracting brand
				String brand = el.children().first().text();

				// Visit each individual webpage of brand for all models.
				Document brandPage = Jsoup.connect(bPage).data("query", "Java").userAgent("Mozilla")
						.cookie("auth", "token").timeout(3000).post();

				// For each individual model page, visit details page.
				Elements phoneModelsList = brandPage.select("div.makers").first().children().first().children();

				// Getting all the pagination links. Check if page links exist
				// for more pages.
				Elements tempList = brandPage.select("div.nav-pages");
				if (null != tempList) {
					Elements pagesList = tempList.first().getElementsByAttribute("href");

					// For all links.. get models.
					for (Element element : pagesList) {
						currentStatus = GSMArenaParseUtility.getInstance().iterateOverModelsList(brand, phoneModelsList,
								currentStatus);

						// Sleeping for 30 seconds after one list page of a
						// brand
						// TODO Configure how much sleep is needed.
						try {
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						brandPage = Jsoup.connect(element.attr("href")).data("query", "Java").userAgent("Mozilla")
								.cookie("auth", "token").timeout(3000).post();
						phoneModelsList = brandPage.select("div.makers").first().children().first().children();
					}
				} else {
					currentStatus = GSMArenaParseUtility.getInstance().iterateOverModelsList(brand, phoneModelsList,
							currentStatus);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
