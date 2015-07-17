package com.cisco.webex.sikpeng;

/*
// an example of a bug
{
"Found":"dev-test",
"Headline":"Task#17227-Office2013 support:Shape shadow display incorrect in slide13",
"id":"CSCud38140",
"Component":"ucf",
"DE-manager":"pilan",
"Age":"962",
"Priority":"NA",
"Severity":"6",
"Status":"O"
}
*/
public class Bug {
	
	public Bug(){
		
	}
	
	public Bug(String found, String headline, String id, String component, String dEManager, String age,
			String priority, String severity, String status) {
		super();
		this.found = found;
		this.headline = headline;
		this.id = id;
		this.component = component;
		this.dEManager = dEManager;
		this.age = age;
		this.priority = priority;
		this.severity = severity;
		this.status = status;
	}
	
	private String found;
	private String headline;
	private String id;
	private String component;
	private String dEManager;
	private String age;
	private String priority;
	private String severity;
	private String status;
	
	public String getFound() {
		return found;
	}
	public void setFound(String found) {
		this.found = found;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getdEManager() {
		return dEManager;
	}
	public void setdEManager(String dEManager) {
		this.dEManager = dEManager;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
