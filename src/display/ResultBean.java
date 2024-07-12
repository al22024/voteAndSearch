package display;

import java.io.Serializable;

public class ResultBean implements Serializable {
	String eventName;	//イベント名
	String rating;
	String name;
	String address;
	String date;
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEvent() {
		return eventName;
	}
	
	public void setEvent(String eventName) {
		this.eventName = eventName;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
		
		
}
