package com.ganesh.gsmarena.model;
/**
 * @author shriganesh
 *
 */
public class GSMArenaData {
	private String brand=null;
	private String model=null;
	
	private String imageURL=null;
	private String productURL=null;
	
	//Network
	private String technology=null;
	private String twoGBands=null;
	private String threeGBands=null;
	private String fourGBands=null;
	private String networkSpeed=null;
	private String gprs=null;
	private String edge=null;
	
	//Launch Details
	private String launchAnnounced=null;
	private String launchStatus=null;
	
	//Body
	private String dimensions=null;
	private String weight=null;
	private String simType="";
	private String bodyAdditionalFeature=null;
	
	//Display
	private String displaySize=null;
	private String resolution=null;
	private String displayType=null;
	private String multiTouch=null;
	private String protection=null;
	private String displayAdditionalFeature=null;
	
	//Platform
	private String OS=null;
	private String chipset=null;
	private String gpu=null;
	private String processor=null;
//	private String clockSpeed=null;
	private String cores=null;
	
	//Memory
	private String RAM=null;
	private String [] ROM=null;
	private String externalMemory=null;
	private String internalMemory=null;
	
	//Camera
	private String primaryCamera=null;
	private String secondaryCamera=null;
	private String cameraFeatures=null;
	private String video=null;
	
	//Sound
	private String alertTypes=null;
	private String loudSpeaker=null;
	
	
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public String getTwoGBands() {
		return twoGBands;
	}
	public void setTwoGBands(String twoGBands) {
		this.twoGBands = twoGBands;
	}
	public String getThreeGBands() {
		return threeGBands;
	}
	public void setThreeGBands(String threeGBands) {
		this.threeGBands = threeGBands;
	}
	public String getFourGBands() {
		return fourGBands;
	}
	public void setFourGBands(String fourGBands) {
		this.fourGBands = fourGBands;
	}
	public String getNetworkSpeed() {
		return networkSpeed;
	}
	public void setNetworkSpeed(String networkSpeed) {
		this.networkSpeed = networkSpeed;
	}
	public String getGprs() {
		return gprs;
	}
	public void setGprs(String gprs) {
		this.gprs = gprs;
	}
	public String getEdge() {
		return edge;
	}
	public void setEdge(String edge) {
		this.edge = edge;
	}
	public String getBodyAdditionalFeature() {
		return bodyAdditionalFeature;
	}
	public void setBodyAdditionalFeature(String bodyAdditionalFeature) {
		this.bodyAdditionalFeature = bodyAdditionalFeature;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getMultiTouch() {
		return multiTouch;
	}
	public void setMultiTouch(String multiTouch) {
		this.multiTouch = multiTouch;
	}
	public String getProtection() {
		return protection;
	}
	public void setProtection(String protection) {
		this.protection = protection;
	}
	public String getDisplayAdditionalFeature() {
		return displayAdditionalFeature;
	}
	public void setDisplayAdditionalFeature(String displayAdditionalFeature) {
		this.displayAdditionalFeature = displayAdditionalFeature;
	}
	public String getInternalMemory() {
		return internalMemory;
	}
	public void setInternalMemory(String internalMemory) {
		this.internalMemory = internalMemory;
	}
	public String getCameraFeatures() {
		return cameraFeatures;
	}
	public void setCameraFeatures(String cameraFeatures) {
		this.cameraFeatures = cameraFeatures;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getAlertTypes() {
		return alertTypes;
	}
	public void setAlertTypes(String alertTypes) {
		this.alertTypes = alertTypes;
	}
	public String getLoudSpeaker() {
		return loudSpeaker;
	}
	public void setLoudSpeaker(String loudSpeaker) {
		this.loudSpeaker = loudSpeaker;
	}
	public String getAudioJack() {
		return audioJack;
	}
	public void setAudioJack(String audioJack) {
		this.audioJack = audioJack;
	}
	public String getSoundAdditionalFeature() {
		return soundAdditionalFeature;
	}
	public void setSoundAdditionalFeature(String soundAdditionalFeature) {
		this.soundAdditionalFeature = soundAdditionalFeature;
	}
	public String getWlan() {
		return wlan;
	}
	public void setWlan(String wlan) {
		this.wlan = wlan;
	}
	public String getBluetooth() {
		return bluetooth;
	}
	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getNFC() {
		return NFC;
	}
	public void setNFC(String nFC) {
		NFC = nFC;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getUSB() {
		return USB;
	}
	public void setUSB(String uSB) {
		USB = uSB;
	}
	public String getSensors() {
		return sensors;
	}
	public void setSensors(String sensors) {
		this.sensors = sensors;
	}
	public String getMessaging() {
		return messaging;
	}
	public void setMessaging(String messaging) {
		this.messaging = messaging;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getJava() {
		return java;
	}
	public void setJava(String java) {
		this.java = java;
	}
	public String getGeneralAdditionalFeature() {
		return generalAdditionalFeature;
	}
	public void setGeneralAdditionalFeature(String generalAdditionalFeature) {
		this.generalAdditionalFeature = generalAdditionalFeature;
	}
	private String audioJack=null;
	private String soundAdditionalFeature=null;
	
	//Communication
	private String wlan=null;
	private String bluetooth=null;
	private String gps=null;
	private String NFC=null;
	private String radio=null;
	private String USB=null;
	
	//Features
	private String sensors=null;
	private String messaging=null;
	private String browser=null;
	private String java=null;
	private String generalAdditionalFeature=null;
	
	//Battery
	private String batterCapacity=null;
	
	//Colors
	private String [] colors=null;
	
	
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	public String getProductURL() {
		return productURL;
	}
	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}
	public String getGpu() {
		return gpu;
	}
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public String getChipset() {
		return chipset;
	}
	public void setChipset(String chipset) {
		this.chipset = chipset;
	}
	public String getLaunchAnnounced() {
		return launchAnnounced;
	}
	public void setLaunchAnnounced(String launchAnnounced) {
		this.launchAnnounced = launchAnnounced;
	}
	public String getLaunchStatus() {
		return launchStatus;
	}
	public void setLaunchStatus(String launchStatus) {
		this.launchStatus = launchStatus;
	}
	
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getPrimaryCamera() {
		return primaryCamera;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
	}
	public String []getROM() {
		return ROM;
	}
	public void setROM(String [] rOM) {
		ROM = rOM;
	}
	public String getExternalMemory() {
		return externalMemory;
	}
	public void setExternalMemory(String externalMemory) {
		this.externalMemory = externalMemory;
	}
	public String getMainCamera() {
		return primaryCamera;
	}
	public void setPrimaryCamera(String primaryCamera) {
		this.primaryCamera = primaryCamera;
	}
	public String getSecondaryCamera() {
		return secondaryCamera;
	}
	public void setSecondaryCamera(String secondaryCamera) {
		this.secondaryCamera = secondaryCamera;
	}
//	public String getClockSpeed() {
//		return clockSpeed;
//	}
//	public void setClockSpeed(String processor) {
//		this.clockSpeed = processor;
//	}
	public String getBatterCapacity() {
		return batterCapacity;
	}
	public void setBatterCapacity(String batterCapacity) {
		this.batterCapacity = batterCapacity;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getSimType() {
		return simType;
	}
	public void setSimType(String simType) {
		this.simType = simType;
	}
	public String[] getColors() {
		return colors;
	}
	public void setColors(String[] colors) {
		this.colors = colors;
	}
}
