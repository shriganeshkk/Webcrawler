 package com.ganesh.consumercomplaints;
 
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteCrawler {

	public static void main(String[] args) {
		String website ="http://www.consumercomplaints.in/snapdeal-com-b100038";
		String profileWebsite="http://www.consumercomplaints.in";
		
		Document fullPage = null;
	    try {
	    	fullPage=Jsoup.connect(website).data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(3000)
					  .post();
			Elements td = fullPage.select("td.complaint");
			
			//For all elements on first page go to linked pages.
			for(Element el : td){
			   Element temp=el.select("a[href]").first();
			   String partial=temp.attr("href");
			   String linkedPage=website+partial;
			   Document linkedPageDoc=Jsoup.connect(linkedPage).data("query", "Java")
						  .userAgent("Mozilla")
						  .cookie("auth", "token")
						  .timeout(3000)
						  .post();

			   //Getting Ticket Properties.
			   String ticketDescription=linkedPageDoc.select("td.complaint").first().text();
			   String starDescription=linkedPageDoc.select("div.stars-big").first().attr("content");
			   String ticketLongDescription=linkedPageDoc.select("td.compl-text").first().child(0).text();
			   String tempString=linkedPageDoc.select("td.small").first().toString();
			   String resourcePublishDate =tempString.substring(tempString.indexOf("a> on ")+6, tempString.indexOf("<div class=\"add-a-comment\">"));
			   
			   //Get Images if any
			   
			   //Get User Properties
			   String customerName=linkedPageDoc.select("td.small").first().child(1).text();
			   
			   //Goto customer profile page to get reputation points
			   String customerURL=linkedPageDoc.select("td.small").first().child(1).attr("href");
			   Document profilePageDoc=Jsoup.connect(profileWebsite+customerURL).data("query", "Java")
						  .userAgent("Mozilla")
						  .cookie("auth", "token")
						  .timeout(3000)
						  .post();
			   String reputationPoints=profilePageDoc.select("div.reputation").first().child(0).text();
			   
			   System.out.println(ticketDescription);
			   System.out.println(resourcePublishDate);
			   System.out.println(ticketLongDescription);
			   System.out.println(starDescription);
			   
			   System.out.println(customerName);
			   
			   System.out.println(reputationPoints);
			   
			   System.out.println("-------------------------------------------------------------");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
	