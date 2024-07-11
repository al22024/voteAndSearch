package display;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dbtest.ProjectInfo;

public class DisplayEventsBean implements Serializable{
	public List<Event> event;
	public String category;
	public String date;
	public String url;
	
	public List<Event> getList() {
		return event;
	}
	
	public void setList(List<Event> event) {
		this.event = event;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void makeList(int KEY) {
		ProjectInfo projectinfo = new ProjectInfo(KEY);
		setCategory(projectinfo.category);
		setDate(projectinfo.dateTime.toString());
//
//		
		SearchEvents sea = new SearchEvents();
		ArrayList<Event> events = sea.search(projectinfo.region, projectinfo.category, projectinfo.dateTime.toString());
		this.event = events;
	}
	
}