package usedb;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import dbtest.*;

public class VoteBean implements Serializable {
	private String genre = "";
	private String budget1 = "";
	private String budget2 = "";
	private String review = "";
	private String dateTime ="";
	private List<List<String>> voterList = new ArrayList<>();
	
	public void setGenre(int userID, int projectID){
		UserAndProjectInfo upinfo = new UserAndProjectInfo();
		this.genre = upinfo.getVoteInfo(projectID, userID).genre;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public void setBudget1(int userID, int projectID){
		UserAndProjectInfo upinfo = new UserAndProjectInfo();
		this.budget1 = upinfo.getVoteInfo(projectID, userID).budget1;
	}
	
	public String getBudget1(){
		return budget1;
	}
	
	public void setBudget2(int userID, int projectID){
		UserAndProjectInfo upinfo = new UserAndProjectInfo();
		this.budget2 = upinfo.getVoteInfo(projectID, userID).budget2;
	}
	
	public String getBudget2(){
		return budget2;
	}
	
	public void setReview(int userID, int projectID){
		UserAndProjectInfo upinfo = new UserAndProjectInfo();
		this.review = upinfo.getVoteInfo(projectID, userID).review;
	}
	
	public String getReview(){
		return review;
	}
	
	public void setDateTime(int projectID){
		ProjectInfo pinfo = new ProjectInfo();
        Timestamp tim = new Timestamp(System.currentTimeMillis() + 32400000);//pinfo.getProjectInfo(projectID).dateTime;
        SimpleDateFormat dateTimeString = new SimpleDateFormat("yyyy/mm/dd");
		this.dateTime = dateTimeString.format(tim);
	}
	
	public String getDateTime(){
		return dateTime;
	}
	
	public void setVoterList(int projectID) {
		UserInfo uinfo = new UserInfo();
        this.voterList = uinfo.getVoterList(projectID);
	}
	
	public List<List<String>> getVoterList(){
		return voterList;
	}
	
}
