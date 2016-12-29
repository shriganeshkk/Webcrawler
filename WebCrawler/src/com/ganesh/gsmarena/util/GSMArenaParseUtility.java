package com.ganesh.gsmarena.util;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ganesh.gsmarena.model.GSMArenaCurrentStatus;
import com.ganesh.gsmarena.model.GSMArenaData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author shriganesh
 *
 */
public class GSMArenaParseUtility {
	private static GSMArenaParseUtility parseAttributes = new GSMArenaParseUtility();

	private GSMArenaParseUtility() {
	}

	public static GSMArenaParseUtility getInstance() {
		return parseAttributes;
	}

	protected GSMArenaData parseAttributes(GSMArenaData tempData, Elements attributeList) {
		GSMArenaData data = tempData;
		Elements nodes=null;
		
		for (Element table : attributeList) {
			// Extract launch details
			if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("launch")){
				nodes= table.children().first().children();
				for (Element element : nodes) {
					if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("launch"))
						data.setLaunchAnnounced(element.children().get(2).text());
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("status"))
						data.setLaunchStatus(element.children().get(1).text());
				}
			}
			//Extract body attributes 
			else if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("body")){
				nodes= table.children().first().children();
				for (Element element : nodes) {
					if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("body"))
						data.setDimensions(element.children().get(2).text().contains("(") ? element.children().get(2).text().substring(0, element.children().get(2).text().indexOf("(")):element.children().get(2).text());
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Weight"))
						data.setWeight(element.children().get(1).text().toLowerCase().contains("(")?element.children().get(1).text().substring(0,element.children().get(1).text().indexOf("(")) : element.children().get(1).text());
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("SIM"))
						data.setSimType(element.children().get(1).text().toLowerCase().contains("dual")? "dual" : "single");
				}
			}
			//Extract display
			else if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("DISPLAY")){
				nodes= table.children().first().children();
				for (Element element : nodes) {
					if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("size")){
						data.setDisplaySize(element.children().get(1).text().contains("(") ? element.children().get(1).text().substring(0, element.children().get(1).text().indexOf("(")):element.children().get(1).text());
					}
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Resolution"))
						data.setResolution(element.children().get(1).text().contains("(") ? element.children().get(1).text().substring(0, element.children().get(1).text().indexOf("(")):element.children().get(1).text());
				}
			}
			//Extract Platform
			else if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("Platform")){
				nodes= table.children().first().children();
				for (Element element : nodes) {
					if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Platform"))
						data.setOS(element.children().get(2).text());
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Chipset"))
						data.setChipset(element.children().get(1).text());
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("CPU")){
						boolean diffCores=false;
						diffCores = element.children().get(1).text().contains("(") ?  true : false;
						
						String [] temp=element.children().get(1).text().split(" ");
						//Extracting clock speed first
						for (int i = 0; i < temp.length; i++) {
							if(temp[i].equalsIgnoreCase("GHz")){
								data.setClockSpeed(temp[i-1]+" "+ temp[i]);
								break;
							}
							else if(temp[i].endsWith("core"))
								data.setCores(temp[i].replaceAll("-", " "));
						}
						
						//After extracting clock speed whatever is left must be CPU
						String cpu = diffCores == false ? element.children().get(1).text().replaceAll(data.getClockSpeed(), "").trim().replaceAll(" +", " ") : element.children().get(1).text();
						if(diffCores)
							data.setClockSpeed("");
						data.setProcessor(cpu);
					}
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("GPU"))
						data.setGpu(element.children().get(1).text());
				}
			}
			//Extract memory
			else if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("memory")){
				nodes= table.children().first().children();
				for (Element element : nodes) {
					if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("memory")){
						if(element.children().get(2).text().contains(",")){
							String [] temp=element.children().get(2).text().split(",");
							for (int i = 0; i < temp.length; i++) {
								if(temp[i].contains("GB"))
									data.setExternalMemory(splitAndReturnBasedon(temp[i],"GB"));
							}
						}
						else
							data.setExternalMemory(element.children().get(2).text());
					}
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Internal")){
						String [] temp=element.children().get(1).text().split(",");
						for (int i = 0; i < temp.length; i++) {
							if(temp[i].contains("RAM"))
								data.setRAM(temp[i].replaceAll("RAM", "").trim());
							else{
								String [] rom= temp[i].replaceAll("GB", "").split("/");
								String [] newROM= new String[rom.length];
								for (int j = 0; j < rom.length; j++) {
									newROM[j]=rom[j].trim()+" "+"GB";
								}
								data.setROM(newROM);
							}
						}
					}
				}
			}
			//Extract camera
			else if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("Camera")){
				nodes= table.children().first().children();
				for (Element element : nodes) {
					if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Camera")){
						String [] tokens= element.children().get(2).text().split(",");
						for (int i = 0; i < tokens.length; i++) {
							if(tokens[i].endsWith("MP"))
								data.setPrimaryCamera(tokens[i]);
						}
					}
					else if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Secondary")){
						String [] tokens= element.children().get(1).text().split(",");
						for (int i = 0; i < tokens.length; i++) {
							if(tokens[i].endsWith("MP"))
								data.setSecondaryCamera(tokens[i]);
						}
					}
				}
			}
			//Extract Battery capacity
			else if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("BATTERY")){
				nodes= table.children().first().children();
				Element e=nodes.first();
				data.setBatterCapacity(splitAndReturnBasedon(e.text(),"mAh"));
			}
			//Extract colors
			else if(table.childNodeSize()> 1 && table.children().first().children().first().children().first().text().equalsIgnoreCase("Misc")){
				nodes= table.children().first().children();
				for (Element element : nodes) {
					if(null!=element.children().get(0).text() && element.children().get(0).text().equalsIgnoreCase("Misc"))
						data.setColors(element.children().get(2).text().split(","));
				}
			}
		}

		return data;
	}
	
	public String splitAndReturnBasedon(String token,String comparator){
		String [] temp=token.split(" ");
		String ret="";
		for (int i = 0; i < temp.length; i++) {
			if(temp[i].equalsIgnoreCase(comparator))
				ret=temp[i-1]+" "+temp[i];
		}
		return ret;
	}

	public GSMArenaCurrentStatus iterateOverModelsList(String brand, Elements phoneModelsList,
			GSMArenaCurrentStatus currentStatus) throws IOException {
		GSMArenaCurrentStatus tempStatus = currentStatus;
		Gson gson = new GsonBuilder().create();

		for (Iterator<Element> iterator2 = phoneModelsList.iterator(); iterator2.hasNext();) {
			Element element = (Element) iterator2.next();
			String mPage = element.getElementsByAttribute("href").first().absUrl("href");

			// Getting model from attributes.
			String model = element.getElementsByAttribute("href").first().children().get(1).children().first().text();

			// Going to model page
			Document modelPage = Jsoup.connect(mPage).data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
					.timeout(3000).post();
			Elements attributeList = modelPage.select("div#specs-list").first().getElementsByTag("table");

			// Get Image URL
			Elements imageAttributes = modelPage.select("div.specs-photo-main");
			String imageURL = imageAttributes.first().getElementsByAttribute("src").first().absUrl("src");

			// All model data posting needs to be done.
			GSMArenaData data = new GSMArenaData();
			model = model.replaceAll(brand, "").trim();
			data.setBrand(brand);
			data.setModel(model);
			data.setImageURL(imageURL);
			data.setProductURL(mPage);

			System.out.println(brand + "  --  " + model);

			try {
				data = GSMArenaParseUtility.getInstance().parseAttributes(data, attributeList);
				tempStatus.setSuccessCount((tempStatus.getSuccessCount() + 1));
				System.out.println("Success Count " + tempStatus.getSuccessCount());
				// TODO Add code for posting this info.
				System.out.println(gson.toJson(data));

			} catch (Exception t) {
				t.printStackTrace();
				tempStatus.setFailureCount((tempStatus.getFailureCount() + 1));
				;
				System.out.println("Failed for " + model + " " + brand);
				System.out.println("Failure Count " + tempStatus.getFailureCount());
			}

			// TODO configure how much sleep is needed.
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return tempStatus;
	}

}
