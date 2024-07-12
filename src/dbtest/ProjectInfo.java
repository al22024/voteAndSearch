package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

/*
 * File Name	:ProjectInfo.java
 * Version		:Ver1.0
 * Designer		:荻野新
 * Date			:2024.06.16
 * Purpose		:企画の情報を扱うためのクラス
 * 
 * set
 * ProjectInfo project = new ProjectInfo();
 * project.projectID = ....
 * .
 * .
 * .
 * .
 * .
 * project.setProjectInfo();
 * 
 * 
 * get
 * ProjectInfo cons = new ProjectInfo();
 * ProjectInfo project = cons.getProjectInfo(projectID);
 * 
 */

public class ProjectInfo {
    public int projectID = 111;
    public String projectName = "";
    public Timestamp dateTime;
    public String category = "";
    public String destination = "";
    public int managerID;
    public String region = "";
    public String progressStatus = "";

    // DB接続のためのアドレスなど
//    String server = "//postgres:5432/"; 
//	String dataBase = "test1";
//	String user = "oops";
//	String passWord = "pass";
//	String url = "jdbc:postgresql:" + server + dataBase;
    String server = "//172.21.40.30:5432/"; // seserverのIPアドレス
	String dataBase = "firstdb";
	String user = "shibaura";
	String passWord = "toyosu";
	String url = "jdbc:postgresql:" + server + dataBase;
	
	public ProjectInfo(int projectID){
        // DB接続
        try {
        
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(url, user, passWord);
            Statement stmt = con.createStatement();
            // 検索の実施と結果の格納
            String sql = "SELECT * FROM ProjectsTableNinth WHERE ProjectID = " + projectID;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            this.projectID = rs.getInt("projectID");
            this.projectName = rs.getString("Name");
            this.dateTime = rs.getTimestamp("DateTime");
            this.category = rs.getString("Category");
            this.destination = rs.getString("Destination");
            this.managerID = rs.getInt("ManagerID");
            this.region = rs.getString("Region");
            this.progressStatus = rs.getString("ProgressStatus");
            System.out.println("constractor");
            System.out.println(this.projectID);
            System.out.println(this.projectName);
            System.out.println(this.dateTime);
            System.out.println(this.region);

            stmt.close();
            con.close();
            System.out.println("return data.");
        } catch (Exception e) {
            System.out.println("Failed to fetch data.");
            e.printStackTrace();
        }
	}

    public ProjectInfo() {
		// TODO Auto-generated constructor stub
	}

	// 企画IDから企画情報を取得、projectinfoクラスのフィールドに保存するメソッド
    public ProjectInfo getProjectInfo(int projectID) {

        ProjectInfo ret = new ProjectInfo();
        // DB接続
        try {
        
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(url, user, passWord);
            Statement stmt = con.createStatement();
            // 検索の実施と結果の格納
            String sql = "SELECT * FROM ProjectsTableNinth WHERE ProjectID = " + projectID;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            ret.projectID = rs.getInt("projectID");
            ret.projectName = rs.getString("Name");
            ret.dateTime = rs.getTimestamp("DateTime");
            ret.category = rs.getString("Category");
            ret.destination = rs.getString("Destination");
            ret.managerID = rs.getInt("ManagerID");
            ret.region = rs.getString("Region");
            ret.progressStatus = rs.getString("ProgressStatus");
            System.out.println(ret.projectID);
            System.out.println(ret.projectName);
            System.out.println(ret.dateTime);
            System.out.println(ret.region);

            stmt.close();
            con.close();
            System.out.println("return data.");
            return ret;
        } catch (Exception e) {
            System.out.println("Failed to fetch data.");
            e.printStackTrace();
            return ret;
        }
    }

  //ProjectInfoクラスのフィールドに格納されたデータをデータベースに登録するメソッド
    public void setProjectInfo() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, passWord);

            String sql = "INSERT INTO ProjectsTableNinth VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prestmt = con.prepareStatement(sql);

            prestmt.setInt(1, projectID);
            prestmt.setString(2, projectName);
            prestmt.setTimestamp(3, dateTime);
            prestmt.setString(4, category);
            prestmt.setString(5, destination);
            prestmt.setInt(6, managerID);
            prestmt.setString(7, region);
            prestmt.setString(8, progressStatus);

            prestmt.executeUpdate();
            prestmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void updateProjectInfo() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, passWord);

            String sql = "UPDATE ProjectsTableNinth SET projectName = ?, dateTime = ?, category = ?, destination = ?, managerID = ?, region = ?, progressstatus = ? WHERE projectID = ?";
            PreparedStatement prestmt = con.prepareStatement(sql);

            prestmt.setString(1, projectName);
            prestmt.setTimestamp(2, dateTime);
            prestmt.setString(3, category);
            prestmt.setString(4, destination);
            prestmt.setInt(5, managerID);
            prestmt.setString(6, region);
            prestmt.setString(7, progressStatus);
            prestmt.setInt(8, projectID);

            prestmt.executeUpdate();
            prestmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
