import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author shriganesh
 *
 */
public class ParseUtility {
	private static ParseUtility parseAttributes = new ParseUtility();

	private ParseUtility() {
	}

	public static ParseUtility getInstance() {
		return parseAttributes;
	}

	protected OnlyPhonesData parseAttributes(OnlyPhonesData tempData, Elements attributeList) {
		OnlyPhonesData data = tempData;
		for (Iterator<Element> iterator3 = attributeList.iterator(); iterator3.hasNext();) {
			Element attribute = (Element) iterator3.next();
			if (2 == attribute.children().size()) {

				switch (attribute.children().first().text().toLowerCase().trim()) {
				case "dimensions":
					// Remove mm from the end. The format is
					// length X width X thickness
					data.setDimensions(attribute.children().get(1).text().substring(0,
							attribute.children().get(1).text().indexOf("mm")));
					break;
				case "weight":
					data.setWeight(attribute.children().get(1).text().substring(0,
							attribute.children().get(1).text().indexOf("g")));
					break;
				case "internal":
					String[] memory = attribute.children().get(1).text().split(",");
					for (int i = 0; i < memory.length; i++) {
						if (null != memory[i] && memory[i].endsWith("RAM")) {
							data.setRAM(memory[i].substring(0, memory[i].indexOf("RAM")));
						} else if (null != memory[i] && memory[i].endsWith("built-in")) {
							// Can be in 2 formats 128GB or
							// 32/64/128GB GB . Eliminate
							// all GB so that easy to form
							// an array at the end
							String[] rom = memory[i].substring(0, memory[i].indexOf("built-in")).replaceAll("GB", "")
									.split(",");
							String[] newROM = new String[rom.length];
							for (int j = 0; j < rom.length; j++) {
								newROM[j] = rom[j].replaceAll("GB", "").trim() + " GB";
							}
							data.setROM(newROM);
						} else if (null != memory[i] && memory[i].startsWith("microSD")) {
							data.setExternalMemory(
									memory[i].substring(memory[i].indexOf("GB") - 4, memory[i].indexOf(")")));
						}
					}
					if (null == data.getExternalMemory() && attribute.children().get(1).text().contains("+")) {
						System.out.println(attribute.children().get(1).text());
						memory = attribute.children().get(1).text().split("\\+");
						data.setExternalMemory(memory[1].substring(0, memory[1].indexOf("GB") + 2));
					}
					break;
				case "primary":
					String[] camera = attribute.children().get(1).text().split(",");
					data.setPrimaryCamera(camera[0]);
					for (int i = 0; i < camera.length; i++) {
						if (camera[i].startsWith("2ndry"))
							data.setSecondaryCamera(camera[i].substring(camera[i].indexOf("2ndry") + 5));
					}
					break;
				case "os":
					data.setOS(attribute.children().get(1).text());
					break;
				case "other features":
					if (null != attribute.children().get(1).text()
							&& attribute.children().get(1).text().contains("Dual-Sim"))
						data.setSimType("dual");
					break;
				case "colors":
					if (null != attribute.children().get(1).text()
							&& attribute.children().get(1).text().contains("various"))
						data.setColors(new String[0]);
					else {
						if (null != attribute.children().get(1).text())
							data.setColors(attribute.children().get(1).text().split(","));
					}
					break;
				case "battery":
					if (null != attribute.children().get(1).text()
							&& attribute.children().get(1).text().contains("mAh")) {
						String[] battery = attribute.children().get(1).text().split(" ");
						for (int i = 0; i < battery.length; i++) {
							if (battery[i].equalsIgnoreCase("mAh"))
								data.setBatterCapacity(battery[i - 1] + " " + battery[i]);
						}
					}
					break;
				case "size":
					if (null != attribute.children().get(1).text()
							&& attribute.children().get(1).text().contains("pixels")) {
						//Replace continuously occuring duplicate words and retain the first one
						String result = attribute.children().get(1).text().replaceAll("(?i)\\b([a-z]+)\\b(?:\\s+\\1\\b)+", "$1");
						String[] sizeList = result.split(" ");
						for (int i = 0; i < sizeList.length; i++) {
							if (sizeList[i].contains("pixels")) {
								data.setResolution(sizeList[i-3]+sizeList[i-2]+sizeList[i-1]+" pixels");
							} else if (sizeList[i].contains("inches")) {
								data.setDisplaySize(sizeList[i-1]+" inches");
							}
						}
					}
				case "processor":
					if (null != attribute.children().get(1).text()
							&& attribute.children().get(1).text().contains("GHz")) {
						String [] tokens=attribute.children().get(1).text().split(" ");
						for (int i = 0; i < tokens.length; i++) {
							if(tokens[i].equalsIgnoreCase("GHz"))
								data.setClockSpeed(tokens[i-1]+" GHz");
						}
						data.setProcessor(attribute.children().get(1).text());
					}
					break;
				}
			}
		}
		return data;
	}

	protected CurrentStatus iterateOverModelsList(String website, String brand, Elements phoneModelsList,
			CurrentStatus currentStatus) throws IOException {
		CurrentStatus tempStatus = currentStatus;
		Gson gson = new GsonBuilder().create();

		for (Iterator<Element> iterator2 = phoneModelsList.iterator(); iterator2.hasNext();) {
			Element element = (Element) iterator2.next();
			String mPage = website + element.getElementsByAttribute("href").attr("href");

			// Getting model from attributes.
			String model = element.getElementsByAttribute("href").text().substring(0,
					element.getElementsByAttribute("href").text().indexOf("View Detail"));

			// Going to model page
			Document modelPage = Jsoup.connect(mPage).data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
					.timeout(3000).post();
			Elements attributeList = modelPage.select("div.border_bottom");

			// Get Image URL
			Elements imageAttributes = modelPage.select("div.specs_image_holder");
			String imageURL = imageAttributes.first().getElementsByAttribute("src").first().absUrl("src");

			// All model data posting needs to be done.
			OnlyPhonesData data = new OnlyPhonesData();
			model = model.replaceAll(brand, "").trim();
			data.setBrand(brand);
			data.setModel(model);
			data.setImageURL(imageURL);

			System.out.println(brand + "  --  " + model);

			try {
				data = ParseUtility.getInstance().parseAttributes(data, attributeList);
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
