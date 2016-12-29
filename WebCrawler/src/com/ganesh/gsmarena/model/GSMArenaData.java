package com.ganesh.gsmarena.model;
/**
 * @author shriganesh
 *
 */
public class GSMArenaData {
	private String brand=null;
	private String model=null;
	private String dimensions=null;
	private String weight=null;
	private String displaySize=null;
	private String resolution=null;
	private String RAM=null;
	private String [] ROM=null;
	private String externalMemory=null;
	private String primaryCamera=null;
	private String secondaryCamera=null;
	private String clockSpeed=null;
	private String batterCapacity=null;
	private String OS=null;
	private String simType="single";
	private String [] colors=null;
	private String imageURL=null;
	private String processor=null;
	private String launchAnnounced=null;
	private String chipset=null;
	private String gpu=null;
	private String productURL=null;
	private String cores=null;
	
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
	private String launchStatus=null;
	
	
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
	public String getClockSpeed() {
		return clockSpeed;
	}
	public void setClockSpeed(String processor) {
		this.clockSpeed = processor;
	}
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
