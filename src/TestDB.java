import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import dbtest.ProjectInfo;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.Timestamp;
//
//import dbtest.ProjectInfo;
//
public class TestDB {
//public static void main(String[] args) { 
//         UserInfo test = new UserInfo(); 
//         test.userID = 1; 
//         test.name = "テスト"; 
//         test.email = "test@gmail.com"; 
//         test.setUserInfo(); 
//}
	public static void main(String[] args) {
		ProjectInfo pre = new ProjectInfo();
		ProjectInfo PInfo = new ProjectInfo();
		PInfo.dateTime = new Timestamp(System.currentTimeMillis());
		PInfo.category = "スポーツ";
		PInfo.destination = "test dest";
		PInfo.managerID = 1;
		PInfo.progressStatus = "test progress";
		PInfo.projectID = 1;
		PInfo.projectName = "let's play sports!";
		PInfo.region = "東京都";
		String server = "//172.21.40.30:5432/"; // seserverのIPアドレス
		String dataBase = "firstdb";
		String user = "shibaura";
		String passWord = "toyosu";
		String url = "jdbc:postgresql:" + server + dataBase;
//		String server = "//postgresql:5432/"; 
//		String dataBase = "test1";
//		String user = "oops";
//		String passWord = "pass";
//		String url = "jdbc:postgresql:" + server + dataBase;
		try {//プロジェクトアップデート
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, passWord);

            String sql = "UPDATE ProjectsTableNinth SET Name = ?, DateTime = ?, Category = ?, Destination = ?, ManagerID = ?, Region = ?, ProgressStatus = ? WHERE ProjectID = ?";
            PreparedStatement prestmt = con.prepareStatement(sql);

            prestmt.setString(1, PInfo.projectName);
            prestmt.setTimestamp(2, PInfo.dateTime);
            prestmt.setString(3, PInfo.category);
            prestmt.setString(4, PInfo.destination);
            prestmt.setInt(5, PInfo.managerID);
            prestmt.setString(6, PInfo.region);
            prestmt.setString(7, PInfo.progressStatus);
            prestmt.setInt(8, PInfo.projectID);

            prestmt.executeUpdate();
            prestmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//		PInfo.setProjectInfo();//新規プロジェクト設定
		PInfo = pre.getProjectInfo(1);
		System.out.println("dateTime:" + PInfo.dateTime.toString());
		System.out.println("category:" + PInfo.category);
		System.out.println("dest:" + PInfo.destination);
		System.out.println("maneger:" + PInfo.managerID);
		System.out.println("progress:" + PInfo.progressStatus);
		System.out.println("projectID:" + PInfo.projectID);
		System.out.println("projectName:" + PInfo.projectName);
		System.out.println("region:" + PInfo.region);
	}
}

