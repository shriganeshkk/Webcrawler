package com.ganesh.onlyphones.crawler;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ganesh.onlyphones.model.CurrentStatus;
import com.ganesh.onlyphones.util.ParseUtility;

/**
 * @author shriganesh
 *
 */
public class OnlyPhonesCrawler {

	public static void main(String[] args) {
		String website = "http://id.onlyphones.com/";

		Document fullPage = null;
		try {
			fullPage = Jsoup.connect(website).data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
					.timeout(3000).post();
			Elements initial = fullPage.select("ul.menu_top_shadow");
			Elements brandList = initial.first().children();
			// Skip first invalid element
			boolean skipFirst = true;
			CurrentStatus currentStatus=new CurrentStatus();

			// For all brands on the left hand side of the page
			for (Iterator<Element> iterator = brandList.iterator(); iterator.hasNext();) {
				Element brandLink = (Element) iterator.next();
				if (!skipFirst) {

					String bPage = website + brandLink.getElementsByAttribute("href").attr("href");
					

					// Extracting brand
					String brand = brandLink.getElementsByAttribute("href").attr("title");

					// Visit each individual webpage of brand for all models.
					Document brandPage = Jsoup.connect(bPage).data("query", "Java").userAgent("Mozilla")
							.cookie("auth", "token").timeout(3000).post();

					// For each individual model page, visit details page.
					Elements phoneModelsList = brandPage.select("div.list_cellphone").first().children().first()
							.children();
					
					//Getting all the pagination links
					Elements pagesList=brandPage.select("div#pages").first().getElementsByAttribute("href");
					
					//For all links except the next page link.. get models.
					for (Element element : pagesList) {
						if(!element.text().contains("Next")){
							currentStatus=ParseUtility.getInstance().iterateOverModelsList(website,brand,phoneModelsList,currentStatus);
							
							//Sleeping for 30 seconds after one list page of a brand
							//TODO Configure how much sleep is needed.
							try {
								Thread.sleep(30000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							 brandPage = Jsoup.connect(element.attr("href")).data("query", "Java").userAgent("Mozilla")
									.cookie("auth", "token").timeout(3000).post();
							 phoneModelsList=brandPage.select("div.list_cellphone").first().children().first()
										.children();
						}
					}
				} 
				else {
					//Skipping the header "Browse by Brand"
					skipFirst = false;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
