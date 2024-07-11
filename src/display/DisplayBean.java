package display;

import java.io.*;
import java.security.*;
import java.util.*;

public class DisplayBean implements Serializable {
	public List<String[]> list;
	public String name;	//イベント名
	public String date;
	public String query;
	
	public List<String[]> getList() {
		return list;
	}
	
	public void setList(List<String[]> list) {
		this.list = list;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public void makeList(int KEY){
		
		list = new ArrayList<String[]>();
		
		RequestVotingContent rvc = new RequestVotingContent();
		String[] temp = rvc.request(KEY);	//KEYにデータベース用KEY値入れろ
		this.query = temp[0];
		this.name = temp[1];
		this.date = temp[2];
		
		try {
			list = rvc.search(query);
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
