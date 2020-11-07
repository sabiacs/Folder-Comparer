package com.comparefolder.CompareApplication.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

//To get the properties mentioned in application.properties file
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
	
	private String sourcelocation;
	private String targetlocation;
	private String comparelocation;
	
	public String getSourcelocation() {
		return sourcelocation;
	}
	public void setSourcelocation(String sourcelocation) {
		this.sourcelocation = sourcelocation;
	}
	public String getTargetlocation() {
		return targetlocation;
	}
	public void setTargetlocation(String targetlocation) {
		this.targetlocation = targetlocation;
	}
	public String getComparelocation() {
		return comparelocation;
	}
	
	public void setComparelocation(String comparelocation) {
		this.comparelocation = comparelocation;
	}

}
